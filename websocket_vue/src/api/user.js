import axios from '@/plugins/axios'

export function login() {
    return axios({
        url: 'login',
        method: 'POST'
    })
}
