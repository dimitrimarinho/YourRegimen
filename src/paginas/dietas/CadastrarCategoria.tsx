import { Button, Container, TextField, Typography } from "@material-ui/core";
import { ChangeEvent, useEffect, useState } from "react";
import { buscaId, post, put } from "../../service/Service";
import { useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { TokenState } from "../../store/tokens/tokensReducer";
import { toast } from "react-toastify";
import Categoria from "../../model/Categoria";
import "./CadastrarCategoria.css"

function CadastrarCategoria() {
    let navigate = useNavigate()
    const { id } = useParams<{ id: string }>()
    const token = useSelector<TokenState, TokenState["tokens"]>(
        (state) => state.tokens
    )
    const [categoria, setCategoria] = useState<Categoria>({
        idCategoria: '',
        objetivoDieta: '',
        restricaoSaude: '',
        restricaoFinanceira: true
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
            buscaId(`/service-diet/categoria/getById/${id}`, setCategoria, {
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
        setCategoria({
            ...categoria,
            [e.target.name]: e.target.value,
        })
    }

    async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
        e.preventDefault()
        console.log("categoria" + JSON.stringify(categoria))

        if (id !== undefined) {
            console.log(categoria)
            put(`/service-diet/categoria/alterarcategoria`, categoria, setCategoria, {
                headers: {
                    'Authorization': token
                }
            })
            toast.success('Categoria atualizada com sucesso!', {
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
            post(`/service-diet/categoria/postarcategoria`, categoria, setCategoria, {
                headers: {
                    'Authorization': token
                }
            })
            toast.success('Categoria cadastrada com sucesso!', {
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
                <TextField value={categoria.objetivoDieta} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedTema(e)} id="objetivoDieta" label="Objetivo da dieta" variant="outlined" name="objetivoDieta" margin="normal" fullWidth />
                <TextField value={categoria.restricaoFinanceira} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedTema(e)} id="restricaoFinanceira" label="Há restrição financeira?" variant="outlined" name="restricaoFinanceira" margin="normal" fullWidth />
                <TextField value={categoria.restricaoSaude} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedTema(e)} id="restricaoSaude" label="Há restrição de saúde?" variant="outlined" name="restricaoSaude" margin="normal" fullWidth />
                <Button type="submit" variant="contained" className='botao1'>
                    Finalizar
                </Button>
            </form>
        </Container>
    )
}

export default CadastrarCategoria;