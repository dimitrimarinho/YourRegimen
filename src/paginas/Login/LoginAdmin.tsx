import React, { ChangeEvent, useEffect, useState } from "react";
import { Button, Grid, TextField, Typography, Paper } from "@material-ui/core";
import { Box } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addToken } from "../../store/tokens/actions";
import { login } from "../../service/Service";
import { toast } from "react-toastify";
import "./Login.css";
import AdminLogin from "../../model/AdminLogin";


function LoginAdmin() {

    let navigate = useNavigate();
    const dispatch = useDispatch();
    const [token, setToken] = useState('');
    const [adminLogin, setAdminLogin] = useState<AdminLogin>(
        {
            id: '',
            nome: '',
            usuario: '',
            senha: '',
            token: ''
        }
    )

    function updatedModel(e: ChangeEvent<HTMLInputElement>) {

        setAdminLogin({
            ...adminLogin,
            [e.target.name]: e.target.value
        })
    }

    useEffect(() => {
        if (token !== '') {
            dispatch(addToken(token));
            navigate('/home-nutricionista')
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [token])

    async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
        e.preventDefault();
        try {
            await login('/usuarios/logar', adminLogin, setToken)
            toast.success('Usuário logado com sucesso!', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined,
            });
        } catch (error) {
            toast.error('Dados do usuário inconsistentes. Erro ao logar!', {
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
    }

    return (
        <Grid container direction="row" justifyContent="center" alignItems="center">
            <Grid alignItems="center" xs={12} >
                <Box width='100%' height='100vh' display={'flex'} alignItems='center' paddingLeft={'10%'} className='imagem' >
                    <Paper style={{ padding: '32px 48px', borderRadius: '16px' }} className="login-c">
                        <Box maxWidth={'420px'}>
                            <form onSubmit={onSubmit}>
                                <Typography variant="h3" gutterBottom align="center" component="h3" className="texto">
                                    Bem vindo!
                                </Typography>
                                <TextField value={adminLogin.usuario} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='usuario' label='Usuário de acesso' variant='outlined' name='usuario' margin='normal' fullWidth required />
                                <TextField value={adminLogin.senha} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='senha' label='Senha' variant='outlined' name='senha' margin='normal' type='password' fullWidth required />
                                <Box marginTop={2} textAlign="center">
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
                                <Link to='/cadastroNutricionista' style={{ textDecoration: 'none', color: '#40352C' }}>
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

export default LoginAdmin;