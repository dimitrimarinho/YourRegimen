interface UserLogin {
    id: string;
    nome: string;
    usuario: string;
    senha: string;
    token?: string | null;
}

export default UserLogin;