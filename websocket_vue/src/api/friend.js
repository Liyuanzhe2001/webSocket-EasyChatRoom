import axios from "@/plugins/axios";

export function getFriends() {
    return axios({
        url: "friends",
        method: "GET",
    })
}
