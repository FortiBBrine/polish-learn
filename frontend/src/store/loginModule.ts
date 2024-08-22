
interface LoginState {
    isAuth: boolean,
    token: string | null,
}

export const loginModule = {
    state: (): LoginState => ({
        token: localStorage.getItem("token"),
        isAuth: localStorage.getItem("token") != null
    }),
    getters: {

    },
    mutations: {
        setAuth(state: LoginState, token: string | null) {

            if (token == null) {
                state.isAuth = false
                localStorage.removeItem("token")
            } else {
                state.isAuth = true
                localStorage.setItem("token", token)
            }
        }
    },
    actions: {

    },
    namespaced: true
}

