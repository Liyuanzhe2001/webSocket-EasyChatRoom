import axios from '@/plugins/axios'

export function login(username, password) {
    return axios({
        url: '/login',
        method: 'POST',
        data: {
            username,
            password
        }
    })
}

export function getUsername() {
    return axios({
        url: '/getUsername',
        method: 'GET',
        async: false,
    })
}

export function getUserId() {
    return axios({
        url: '/getUserId',
        method: 'GET',
        async: false,
    })
}
