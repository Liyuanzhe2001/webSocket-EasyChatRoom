import axios from "@/plugins/axios";

export function getRecord(chatUserId) {
    return axios({
        url: `record/${chatUserId}`,
        method: "GET",
    })
}
