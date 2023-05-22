import jwt_decode from "jwt-decode";

interface IUserTokens {
    access_token: string;
    refresh_token: string;
}

interface IDecodedToken {
    user_id: number;
    sub: string;
    iat: number;
    exp: number;
}

export function getUserTokens(): IUserTokens | null {
    const storedUserTokens = localStorage.getItem("tokens");
    return storedUserTokens ? JSON.parse(storedUserTokens) : null;
}

export function getDecodedToken(access_token: string): IDecodedToken {
    return jwt_decode(access_token);
}

export function getAccessToken(): string | null {
    const userTokens = getUserTokens();
    return userTokens ? userTokens.access_token : null;
}

export function getUserId(): number | null {
    const userTokens = getUserTokens();
    return userTokens ? getDecodedToken(userTokens.access_token).user_id : null;
}
