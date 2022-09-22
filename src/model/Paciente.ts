interface Paciente{
    idPaciente: string;
    nomePaciente: string;
    loginPaciente: string;
    senhaPaciente: string;
	altura: number | undefined;
    peso: number | undefined;
	maxCalories: number | undefined;
    minCalories: number | undefined;
}

export default Paciente;