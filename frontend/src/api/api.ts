import axios, {AxiosError} from "axios";
import {StatusCodes} from "http-status-codes";
import {useRouter} from "vue-router";

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json',
    },
    // withCredentials: true,
})

api.interceptors.request.use((config) => {
    if (localStorage.getItem("token")) {
        config.headers["Authorization"] = "Bearer " + localStorage.getItem("token")
    }
    return config
})

api.interceptors.response.use(
    (config) => {
        return config
    },
    (error) => {
        if (error.isAxiosError) {
            if (error.response.status === 401) {
                localStorage.removeItem("token")
                useRouter().push("/login")
                    .then(() => {})
            }
        }
    }
)

export default api
