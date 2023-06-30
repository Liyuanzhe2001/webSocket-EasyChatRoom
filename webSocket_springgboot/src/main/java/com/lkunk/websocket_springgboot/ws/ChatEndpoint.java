package com.lkunk.websocket_springgboot.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lkunk.websocket_springgboot.config.GetHttpSessionConfigurator;
import com.lkunk.websocket_springgboot.pojo.Message;
import com.lkunk.websocket_springgboot.utils.MessageUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {

    //用来存储每一个客户端对象对应的ChatEndpoint对象
    private static Map<String, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    /**
     * 和某个客户端连接对象，需要通过他来给客户端发送数据
     */
    private Session session;

    /**
     * httpSession中存储着当前登录的用户名
     */
    private HttpSession httpSession;

    /**
     * 连接建立成功调用
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        //需要通知其他的客户端，将所有的用户的用户名发送给客户端
        this.session = session;
        //获取HttpSession对象
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        //将该httpSession赋值给成员httpSession
        this.httpSession = httpSession;
        //获取用户名
        String username = (String) httpSession.getAttribute("user");
        //存储该链接对象
        onlineUsers.put(username, this);
        //获取需要推送的消息
        String message = MessageUtils.getMessage(true, null, getNames());
        //广播给所有的用户
        broadcastAllUsers(message);
    }

    private void broadcastAllUsers(String message) {
        try {
            //遍历 onlineUsers 集合
            Set<String> names = onlineUsers.keySet();
            for (String name : names) {
                //获取该用户对应的ChatEndpoint对象
                ChatEndpoint chatEndpoint = onlineUsers.get(name);
                //发送消息
                chatEndpoint.session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<String> getNames() {
        return onlineUsers.keySet();
    }

    /**
     * 接收到消息时调用
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            //获取客户端发送来的数据  {"toName":"张三","message":"你好"}
            ObjectMapper mapper = new ObjectMapper();
            Message mess = mapper.readValue(message, Message.class);
            //获取当前登录的用户名
            String username = (String) httpSession.getAttribute("user");
            //拼接推送的消息
            String data = MessageUtils.getMessage(false, username, mess.getMessage());
            //将数据推送给指定的客户端
            ChatEndpoint chatEndpoint = onlineUsers.get(mess.getToName());
            chatEndpoint.session.getBasicRemote().sendText(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭时调用
     */
    @OnClose
    public void onClose(Session session) {
        //获取用户名
        String username = (String) httpSession.getAttribute("user");
        //移除连接对象
        onlineUsers.remove(username);
        //获取需要推送的消息
        String message = MessageUtils.getMessage(true, null, getNames());
        //广播给所有的用户
        broadcastAllUsers(message);
    }
}
