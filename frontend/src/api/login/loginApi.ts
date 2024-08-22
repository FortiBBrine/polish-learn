import api from "@/api/api";

type BindingResult = {
    [key: string]: string | null;
}

type LoginResponseDto = {
    result: BindingResult;
    token: string | null
}

const login = async (username: string, password: string): Promise<LoginResponseDto | null> => {
    try {
        const { data } = await api.post<LoginResponseDto>(
            "/login",
            {
                username: username,
                password: password
            }
        )

        return data
    } catch (err) {
        console.log(err)
        return null
    }
}

export {
    LoginResponseDto,
    login
}
