import React, {useEffect, useState}from "react";
import { useNavigate, useParams } from "react-router-dom";
import { TokenState } from "../../../store/tokens/tokensReducer";
import {useSelector} from 'react-redux';
import Categoria from "../../../model/Categoria";
import {toast} from "react-toastify";
import { Box } from '@mui/material';
import {Card, CardActions, CardContent, Button, Typography} from '@material-ui/core';
import "./DeletarCategoria.css";
import { buscaId, deleteId } from "../../../service/Service";

function DeletarCategoria(){
let navigate = useNavigate();
const {idCategoria} = useParams<{idCategoria: string}>();
const token = useSelector<TokenState, TokenState["tokens"]>(
    (state) => state.tokens
);
    const [categoria, setCategoria] = useState<Categoria>()

    useEffect(() => {
        if (token == "") {
          
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

        function sim() {
            navigate('/categorias')
            deleteId(`/categoria/${idCategoria}`, {
              headers: {
                'Authorization': token
              }
            });
           
            toast.success("Categoria deletada com sucesso", {
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
        
          function nao() {
           navigate('/categorias')
          }
          
  return (
    <>
      <Box m={2}>
        <Card variant="outlined">
          <CardContent>
            <Box justifyContent="center">
              <Typography color="textSecondary" gutterBottom>
                Deseja deletar a Categoria {categoria?.objetivoDieta} ?
              </Typography>
            </Box>
          </CardContent>
          <CardActions>
            <Box display="flex" justifyContent="start" ml={1.0} mb={2} >
              <Box mx={2}>
                <Button onClick={sim} variant="contained" className="marginLeft" size='large' color="primary">
                  Sim
                </Button>
              </Box>
              <Box mx={2}>
                <Button  onClick={nao} variant="contained" size='large' color="secondary">
                  Não
                </Button>
              </Box>
            </Box>
          </CardActions>
        </Card>
      </Box>
    </>
  );
}
export default DeletarCategoria;