import React from "react";
import { Button, Grid, Paper, TextField, Typography } from "@material-ui/core";
import { Box } from '@mui/material';
import {Link} from 'react-router-dom';
import './CadastroUsuario.css';

function CadastroUsuario(){
   return(
    //falta terminar a estilização e dividir o formulário em 2 colunas, lado a lado.
    <Grid container direction="row" justifyContent="center" alignItems="center" >
    <Grid alignItems="center" xs={12} >
        <Box width='100vw' height='100vh' className="bgCadastroFoto" display={'flex'} alignItems='center' flexDirection="row">
            <Paper style={{ padding: '32px 48px', borderRadius: '16px', maxWidth: "480px" }} elevation={12} className="paperCadastro">
                <Box display="flex" justifyContent="center" className='boxJaTemConta' flexDirection='row'>
                    <Box marginRight={1}>
                        <Typography variant="subtitle1" gutterBottom align="center">
                            Já tem uma conta?
                        </Typography>
                    </Box>
                    <Link to='/login' style={{ textDecoration: 'none', color: '#40352C' }}>
                        <Typography variant="subtitle1" gutterBottom align="center" className="cursor">
                            Fazer login
                        </Typography>
                    </Link>
                </Box >
                <Box className="boxCadastro" flexDirection="row">
                    <form >
                        <Box>
                        <Typography variant='h3' gutterBottom color='primary' component='h3' align='center' className='paperCadastroTitulo'>Cadastrar</Typography>
                        <TextField  id='nome' label='Nome completo' variant='outlined' name='nomeUsuario' margin='normal' fullWidth className="tfCadastro" required/>
                        <TextField id='usuario' label='E-mail' variant='outlined' name='loginUsuario' margin='normal' fullWidth className="tfCadastro" required/>
                        <TextField  id='senha' label='Senha ' variant='outlined' name='senhaUsuario' margin='normal' type='password' fullWidth className="tfCadastro" required/>
                        <TextField  id='confirmarSenha' label='Confirme a sua senha' variant='outlined' name='confirmarSenha' margin='normal' type='password' fullWidth className="tfCadastro" required />
                        </Box>
                        <Box>
                        <TextField  id='altura' label='Altura' variant='outlined' name='nomeUsuario' margin='normal' fullWidth className="tfCadastro" required/>
                        <TextField  id='peso' label='Peso(kg)' variant='outlined' name='nomeUsuario' margin='normal' fullWidth className="tfCadastro" required/>
                        <TextField  id='maxCalorias' label='Quantidade máxima de calorias necessárias' variant='outlined' name='nomeUsuario' margin='normal' fullWidth className="tfCadastro" required/>
                        <TextField  id='minCalorias' label='Quantidade mínima de calorias necessárias' variant='outlined' name='nomeUsuario' margin='normal' fullWidth className="tfCadastro" required/>
                        </Box>
                        <Box marginTop={2} textAlign='center'>
                            <Button type='submit' variant='contained' color='primary' fullWidth className='btnCadastrar'>
                                Cadastrar
                            </Button>
                        </Box>
                    </form>
                </Box>
            </Paper>
        </Box>
    </Grid>
</Grid>

);
}

export default CadastroUsuario;