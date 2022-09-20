import { Button, Container, TextField, Typography } from '@material-ui/core';
import { findByDisplayValue } from '@testing-library/react';
import React, { ChangeEvent, useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import Categoria from '../../../model/Categoria';
import { buscaId, post, put } from '../../../service/Service';
import { TokenState } from '../../../store/tokens/tokensReducer';
import './CadastroCategoria.css';

function CadastroCategoria(){
    let navigate = useNavigate();
    const {idCategoria} = useParams<{idCategoria: string}>();
    const token = useSelector<TokenState, TokenState["tokens"]>(
        (state) => state.tokens
    );

    const [categoria, setCategoria] = useState<Categoria>(
        {
            idCategoria: '',
            objetivoDieta: '',
            restricaoSaude: '',
            restricaoFinanceira: false
        }
    )

    useEffect(() => {
        if (token === "") {
            toast.error("Você precisa estar logado", {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined
            })
            navigate("/login")
        }
    }, [token])

    useEffect(() => {
        if(idCategoria !== undefined){
            findById(idCategoria)
        }
    }, [idCategoria])

    async function findById(idCategoria: string) {
        buscaId(`/categoria/${idCategoria}`, setCategoria, {
            headers: {
              'Authorization': token
            }
          })
        }

        function updatedCategoria(e: ChangeEvent<HTMLInputElement>) {

            setCategoria({
                ...categoria,
                [e.target.name]: e.target.value,
            })
    
        }
        
        async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
            e.preventDefault()
            console.log("categoria " + JSON.stringify(categoria))
    
            if (idCategoria !== undefined) {
                console.log(categoria)
                put(`/categoria`, categoria, setCategoria, {
                    headers: {
                        'Authorization': token
                    }
                })
               
                toast.success("Categoria atualizada com sucesso", {
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
                post(`/categoria`, categoria, setCategoria, {
                    headers: {
                        'Authorization': token
                    }
                })
               
                toast.success("Categoria cadastrada com sucesso", {
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
            navigate('/categorias')
        }
  
    return (
        <Container maxWidth="sm" className="topo">
            <form onSubmit={onSubmit}>
                <Typography variant="h3" color="textSecondary" component="h1" align="center" >Formulário de cadastro categoria</Typography>
                <TextField value={categoria.objetivoDieta} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCategoria(e)} id="objetivoDieta" label="objetivo da dieta" variant="outlined" name="objetivoDieta" margin="normal" fullWidth />
                <TextField value={categoria.restricaoFinanceira} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCategoria(e)} id="restricaoFinanceira" label="restrição financeira" variant="outlined" name="restricaoFinanceira" margin="normal" fullWidth />
                <TextField value={categoria.restricaoSaude} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedCategoria(e)} id="restricaoSaude" label="restrição de saúde" variant="outlined" name="restricaoSaude" margin="normal" fullWidth />
                <Button type="submit" variant="contained" color="primary">
                    Finalizar
                </Button>
            </form>
        </Container>
    )
}

export default CadastroCategoria;