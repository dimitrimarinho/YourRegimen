import Categoria from "./Categoria";

interface Regimen {
    idRegimen: string;
    categoria?: Categoria | null;
    regimenName: string;
    foodList: string;
}

export default Regimen;