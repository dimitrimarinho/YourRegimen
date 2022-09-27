import { Button, Container, TextField, Typography } from "@material-ui/core";
import { ChangeEvent, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { buscaId, post, put } from "../../service/Service";
import { useSelector } from "react-redux";
import { TokenState } from "../../store/tokens/tokensReducer";
import { toast } from "react-toastify";
import Regimen from "../../model/Regimen";
import "./CadastrarDieta.css"

function CadastrarDieta() {
    let navigate = useNavigate()
    const { id } = useParams<{ id: string }>()
    const token = useSelector<TokenState, TokenState["tokens"]>(
        (state) => state.tokens
    )
    const [dieta, setDieta] = useState<Regimen>({
        idRegimen: '',
        categoria: null,
        regimenName: '',
        foodList: ''
    })

    useEffect(() => {
        if (token === "") {
            toast.error('Você precisa estar logado!', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined,
            });
            navigate('/loginNutricionista')
        };
    }, [token, navigate])

    useEffect(() => {
        async function findById(id: string) {
            buscaId(`/service-diet/regimen/${id}`, setDieta, {
                headers: {
                    'Authorization': token
                }
            })
        }

        if (id !== undefined) {
            findById(id)
        }
    }, [token, id])

    function updatedTema(e: ChangeEvent<HTMLInputElement>) {
        setDieta({
            ...dieta,
            [e.target.name]: e.target.value,
        })
    }

    async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
        e.preventDefault()
        console.log("dieta" + JSON.stringify(dieta))

        if (id !== undefined) {
            console.log(dieta)
            put(`/service-diet/regimen`, dieta, setDieta, {
                headers: {
                    'Authorization': token
                }
            })
            toast.success('Dieta atualizada com sucesso!', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined,
            });
        } else {
            post(`/service-diet/regimen`, dieta, setDieta, {
                headers: {
                    'Authorization': token
                }
            })
            toast.success('Dieta cadastrada com sucesso!', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined,
            });
        }
        back()
    }

    function back() {
        navigate('/home-nutricionista')
    }

    return (
        <Container maxWidth="sm" className="topo">
            <form onSubmit={onSubmit}>
                <Typography variant="h3" color="textSecondary" component="h1" align="center" >Formulário de cadastro categorias</Typography>
                <TextField value={dieta.regimenName} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedTema(e)} id="regimenName" label="Nome da dieta" variant="outlined" name="regimenName" margin="normal" fullWidth />
                <TextField value={dieta.foodList} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedTema(e)} id="foodList" label="Lista de alimentos" variant="outlined" name="foodList" margin="normal" fullWidth />
                <Button type="submit" variant="contained" className='botao1'>
                    Finalizar
                </Button>
            </form>
        </Container>
    )
}

export default CadastrarDieta;