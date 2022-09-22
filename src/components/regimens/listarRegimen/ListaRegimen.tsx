import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Regimen from '../../../model/Regimen';
import { Card, CardActions, CardContent, Button, Typography } from '@material-ui/core';
import {Box} from "@mui/material";
import './ListaRegimen.css';
import { useNavigate } from 'react-router-dom'
import { useSelector } from 'react-redux';
import { TokenState } from '../../../store/tokens/tokensReducer';
import { toast } from 'react-toastify';
// import Categoria from '../../../model/Categoria';
import { busca } from '../../../service/Service';

function ListaRegimen(){
    
        let navigate = useNavigate();
        const [regimens, setRegimens] = useState<Regimen[]>([])
        const token = useSelector<TokenState, TokenState["tokens"]>(
            (state) => state.tokens
        );

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
            // eslint-disable-next-line react-hooks/exhaustive-deps
          }, [token])

          async function getRegimen() {
            await busca("/regimens", setRegimens, {
              headers: {
                'Authorization': token
              }
            })
          }
        
          useEffect(() => {           
            getRegimen()
            // eslint-disable-next-line react-hooks/exhaustive-deps
          }, [regimens.length])

          return(
          <>
            { regimens.map(regimen => (
                <Box m={2} >
                  <Card variant="outlined">
                    <CardContent>
                      <Typography color="textSecondary" gutterBottom>
                        Dietas
                      </Typography>
                      <Typography variant="h5" component="h2">
                        {regimen.regimenName}
                      </Typography>
                      <Typography variant="body2" component="p">
                        {regimen.foodList}
                      </Typography>
                      <Typography variant="body2" component="p">
                        {regimen.categoria?.objetivoDieta}
                      </Typography>
                      <Typography variant="body2" component="p">
                        {regimen.categoria?.restricaoSaude}
                      </Typography>
                      <Typography variant="body2" component="p">
                        {regimen.categoria?.restricaoFinanceira}
                      </Typography>
                    </CardContent>
                    <CardActions>
                      <Box display="flex" justifyContent="center" mb={1.5}>
      
                        <Link to={`/formularioRegimen/${regimen.idRegimen}`}  >
                          <Box mx={1}>
                            <Button variant="contained" className="marginLeft" size='small' color="primary" >
                              atualizar
                            </Button>
                          </Box>
                        </Link>
                        <Link to={`/deletarRegimen/${regimen.idRegimen}`} >
                          <Box mx={1}>
                            <Button variant="contained" size='small' color="secondary">
                              deletar
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
          );
}

export default ListaRegimen;