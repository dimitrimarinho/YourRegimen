import { Box, Button, Grid, TextField, Typography, Paper } from "@material-ui/core";
import { Link } from "react-router-dom";
import React from "react";
import "./Login.css";

function Login() {
    return (
        <Grid container direction="row" justifyContent="center" alignItems="center">
            <Grid alignItems="center" xs={12} >
                <Box width='100%' height='100vh' display={'flex'} alignItems='center' paddingLeft={'10%'} className='imagem' >
                    <Paper style={{ padding: '32px 48px', borderRadius: '16px' }} className="login-c">
                        <Box maxWidth={'420px'}>
                            <form >
                                <Typography variant="h3" gutterBottom align="center" component="h3" className="texto">
                                    Bem vindo!
                                </Typography>
                                <TextField id='usuario' label='Usuário de acesso' variant='outlined' name='usuario' margin='normal' fullWidth required />
                                <TextField id='senha' label='Senha' variant='outlined' name='senha' margin='normal' type='password' fullWidth required />
                                <Box marginTop={2} textAlign="center">
                                    <Link to="/home"></Link>
                                    <Button type='submit' className="botao">
                                        Logar
                                    </Button>
                                </Box>
                            </form >
                            <Box display="flex" justifyContent="center" marginTop={2}>
                                <Box marginRight={1}>
                                    <Typography variant="subtitle1" gutterBottom align="center">
                                        Não tem uma conta?
                                    </Typography>
                                </Box>
                                <Link to='/cadastroUsuario' style={{ textDecoration: 'none', color: '#40352C' }}>
                                    <Typography variant="subtitle1" gutterBottom align="center" className="cursor">
                                        Cadastre-se
                                    </Typography>
                                </Link>
                            </Box>
                        </Box>
                    </Paper>
                </Box>
            </Grid>
        </Grid>
    );
}

export default Login;