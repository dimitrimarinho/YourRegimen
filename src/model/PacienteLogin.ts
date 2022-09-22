interface PacienteLogin {
    id: string;
    nome?: string | null;
    usuario: string;
    senha: string;
    token?: string | null;
    altura?: 0 | null,
    peso?: 0 | null,
    maxCalories?: 0 | null,
    minCalories?: 0 | null
}

export default PacienteLogin;