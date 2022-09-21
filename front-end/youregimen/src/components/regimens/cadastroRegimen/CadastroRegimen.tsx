import React, {ChangeEvent, useState, useEffect} from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './CadastroRegimen.css';
import Regimen from '../../../model/Regimen';
import { useSelector } from 'react-redux';
import { TokenState } from '../../../store/tokens/tokensReducer';
import {toast} from "react-toastify";
import Categoria from '../../../model/Categoria';
import { Button, Container, FormControl, FormHelperText, InputLabel, MenuItem, Select, TextField, Typography } from '@material-ui/core';
import { busca, buscaId, post, put } from '../../../service/Service';


function CadastroRegimen(){
    let navigate = useNavigate();
    const {idRegimen} = useParams<{idRegimen: string}>();
    const [categorias, setCategorias] = useState<Categoria[]>([])
    const token = useSelector<TokenState, TokenState["tokens"]>(
        (state) => state.tokens
    );

    useEffect(() => {
        if (token === "") {
            toast.error("Você precisa estar logado", {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined
            })
            navigate("/login")
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [token])

    const [categoria, setCategoria] = useState<Categoria>(
        {
            idCategoria: '',
            objetivoDieta: '',
            restricaoSaude: '',
            restricaoFinanceira: false
        }
    );

    const [regimen, setRegimen] = useState<Regimen>({
        idRegimen: '',
        categoria: categoria,
        regimenName: '',
        foodList: ''
    })

    useEffect(() => { 
        setRegimen({
            ...regimen,
            categoria: categoria
        })
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [categoria])

    useEffect(() => {
        getCategorias()
        if (idRegimen !== '') {
            findByIdRegimen(idRegimen)
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [idRegimen])

    async function getCategorias() {
        await busca("/categoria", setCategorias, {
            headers: {
                'Authorization': token
            }
        })
    }

    async function findByIdRegimen(idRegimen: string | undefined) {
        await buscaId(`regimens/${idRegimen}`, setRegimen, {
            headers: {
                'Authorization': token
            }
        })
    }

    function updatedRegimen(e: ChangeEvent<HTMLInputElement>) {

        setRegimen({
            ...regimen,
            [e.target.name]: e.target.value,
            categoria: categoria
        })

    }

    async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
        e.preventDefault()

        if (idRegimen !== "") {
            put(`/regimens`, regimen, setRegimen, {
                headers: {
                    'Authorization': token
                }
            })
            
            toast.success("Regimen atualizada com sucesso", {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined
                
    
            })
        } else {
            post(`/regimens`, regimen, setRegimen, {
                headers: {
                    'Authorization': token
                }
            })
            
            toast.success("Dieta cadastrada com sucesso", {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined
                
    
            })
        }
        back()

    }

    function back() {
        navigate('/regimens')
    }
    
    return(
        <Container maxWidth="sm" className="topo">
        <form onSubmit={onSubmit}>
            <Typography variant="h3" color="textSecondary" component="h1" align="center" >Formulário de cadastro Regimen</Typography>
            <TextField value={regimen.regimenName} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedRegimen(e)} id="regimenName" label="Nome da dieta" name="texto" variant="outlined" margin="normal" fullWidth />
            <TextField value={regimen.foodList} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedRegimen(e)} id="foodList" label="Lista de alimentos" variant="outlined" name="titulo" margin="normal" fullWidth />
           
            <FormControl >
                <InputLabel id="demo-simple-select-helper-label">Categoria </InputLabel>
                <Select
                    labelId="demo-simple-select-helper-label"
                    id="demo-simple-select-helper"
                    onChange={(e) => buscaId(`/categoria/${e.target.value}`, setCategoria, {
                        headers: {
                            'Authorization': token
                        }
                    })}>
                    {
                        categorias.map(categoria => (
                            <MenuItem value={categoria.idCategoria}>{categoria.objetivoDieta}</MenuItem>
                        ))
                    }
                </Select>
                <FormHelperText>Escolha uma categoria para a dieta</FormHelperText>
                <Button type="submit" variant="contained" color="primary">
                    Finalizar
                </Button>
            </FormControl>
        </form>
    </Container>

    );

}

export default CadastroRegimen;



