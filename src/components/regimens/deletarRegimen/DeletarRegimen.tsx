import React, {useState, useEffect}from 'react';
import { Button, Card, CardActions, CardContent, Typography } from "@material-ui/core";
import { Box } from "@mui/material";
import { useNavigate, useParams } from 'react-router-dom';
import './DeletarRegimen';
import {useSelector} from 'react-redux';
import { TokenState } from '../../../store/tokens/tokensReducer';
import Regimen from '../../../model/Regimen';
import {toast} from 'react-toastify';
import { buscaId, deleteId } from '../../../service/Service';

function DeletarRegimen(){
    let navigate = useNavigate();
    const {idRegimen} = useParams<{idRegimen: string}>();
    const token = useSelector<TokenState, TokenState["tokens"]>(
        (state) => state.tokens
      );

      const [regimen, setRegimens] = useState<Regimen>()

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

    useEffect(() =>{
        if(idRegimen !== undefined){
            findById(idRegimen)
        }
    }, [idRegimen])
  
    async function findById(idRegimen: string) {
        buscaId(`/regimens/${idRegimen}`, setRegimens, {
            headers: {
              'Authorization': token
            }
          })
        }
  
        function sim() {
            navigate('/regimens')
            deleteId(`/regimens/${idRegimen}`, {
              headers: {
                'Authorization': token
              }
            });
            
            toast.success("Dieta deletada com sucesso", {
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
           navigate('/posts')
          }
      return (
        <>
        
          <Box m={2}>
            <Card variant="outlined" >
              <CardContent>
                <Box justifyContent="center">
                  <Typography color="textSecondary" gutterBottom>
                    Deseja deletar a dieta {regimen?.regimenName} ?
                  </Typography>
                </Box>
    
              </CardContent>
              <CardActions>
                <Box display="flex" justifyContent="start" ml={1.0} mb={2} >
                  <Box mx={2}>
                  <Button  onClick={sim} variant="contained" className="marginLeft" size='large' color="primary">
                    Sim
                  </Button>
                  </Box>
                  <Box>
                  <Button  onClick={nao}  variant="contained" size='large' color="secondary">
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

export default DeletarRegimen;