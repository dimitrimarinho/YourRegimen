import react,  { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Categoria from '../../../model/Categoria';
import { TokenState } from '../../../store/tokens/tokensReducer';
import { useSelector } from 'react-redux';
import {toast} from 'react-toastify';
import { Card, CardActions, CardContent, Button, Typography } from '@material-ui/core';
import { Box } from '@mui/material';
import { Link } from 'react-router-dom';

import './ListarCategoria.css';
import { busca } from '../../../service/Service';

function ListarCategoria(){
const [categorias, setCategoria] = useState<Categoria[]>([])
let navigate = useNavigate();
const token = useSelector<TokenState, TokenState["tokens"]>(
    (state) => state.tokens
);

useEffect(() => {
    if (token === '') {
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
   
}, [token])

async function getCategoria() {
    await busca("/categorias", setCategoria, {
        headers: {
            'Authorization': token
        }
    })
}

useEffect(() => {
    getCategoria()
}, [categorias.length])

return (
    <>
    {
      categorias.map(categoria =>(
      <Box m={2} >
        <Card variant="outlined">
          <CardContent>
            <Typography color="textSecondary" gutterBottom>
              Categoria
            </Typography>
            <Typography variant="h5" component="h2">
             {categoria.objetivoDieta}
            </Typography>
            <Typography variant="h5" component="h2">
             {categoria.restricaoFinanceira}
            </Typography>
            <Typography variant="h5" component="h2">
             {categoria.restricaoSaude}
            </Typography>
          </CardContent>
          <CardActions>
            <Box display="flex" justifyContent="center" mb={1.5} >

              <Link to={`/formularioCategoria/${categoria.idCategoria}`} className="text-decorator-none">
                <Box mx={1}>
                  <Button variant="contained" className="marginLeft" size='small' color="primary" >
                    Atualizar
                  </Button>
                </Box>
              </Link>
              <Link to={`/deletarCategoria/${categoria.idCategoria}`} className="text-decorator-none">
                <Box mx={1}>
                  <Button variant="contained" size='small' color="secondary">
                    Deletar
                  </Button>
                </Box>
              </Link>
            </Box>
          </CardActions>
        </Card>
      </Box>
      ))
      }
    </>
)

}

export default ListarCategoria;