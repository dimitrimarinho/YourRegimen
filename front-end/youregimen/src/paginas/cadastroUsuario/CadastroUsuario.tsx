import React, { ChangeEvent, useEffect, useState } from "react";
import { Button, Grid, Paper, TextField, Typography } from "@material-ui/core";
import { Box } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import './CadastroUsuario.css';
import Paciente from "../../model/Paciente";
import { cadastroUsuario } from "../../service/Service";
import { toast } from "react-toastify";

function CadastroUsuario() {

    let navigate = useNavigate();
    const [confirmarSenha, setConfirmarSenha] = useState<String>("")
    const [user, setUser] = useState<Paciente>(
        {
            idPaciente: '',
            nomePaciente: '',
            loginPaciente: '',
            senhaPaciente: '',
            altura: 0,
            peso: 0,
            maxCalories: 0,
            minCalories: 0
        })

    const [userResult, setUserResult] = useState<Paciente>(
        {
            idPaciente: '',
            nomePaciente: '',
            loginPaciente: '',
            senhaPaciente: '',
            altura: 0,
            peso: 0,
            maxCalories: 0,
            minCalories: 0
        })

    useEffect(() => {
        if (userResult.idPaciente != '') {
            navigate("/login")
        }
    }, [userResult])


    function confirmarSenhaHandle(e: ChangeEvent<HTMLInputElement>) {
        setConfirmarSenha(e.target.value)
    }


    function updatedModel(e: ChangeEvent<HTMLInputElement>) {

        setUser({
            ...user,
            [e.target.name]: e.target.value
        })

    }
    async function onSubmit(e: ChangeEvent<HTMLFormElement>) {
        e.preventDefault()
        if (confirmarSenha == user.senhaPaciente) {
            cadastroUsuario(`/pacientes/cadastrar`, user, setUserResult)
            toast.success('Usuario cadastrado com sucesso', {
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
            toast.error('Dados inconsistentes. Favor verificar as informações de cadastro.', {
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
        //falta terminar a estilização e dividir o formulário em 2 colunas, lado a lado.
        <Grid className="GridContainer" container direction="row" justifyContent="center" alignItems="center" >
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
                            <form onSubmit={onSubmit}>

                                <Typography variant='h3' gutterBottom color='primary' component='h3' align='center' className='paperCadastroTitulo'>Cadastrar</Typography>
                                <TextField value={user.nomePaciente} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='nome' label='Nome completo' variant='outlined' name='nomePaciente' margin='normal' fullWidth className="tfCadastro" required />
                                <TextField value={user.loginPaciente} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='usuario' label='E-mail' variant='outlined' name='loginPaciente' margin='normal' fullWidth className="tfCadastro" required />
                                <TextField value={user.senhaPaciente} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='senha' label='Senha ' variant='outlined' name='senhaPaciente' margin='normal' type='password' fullWidth className="tfCadastro" required />
                                <TextField value={confirmarSenha} onChange={(e: ChangeEvent<HTMLInputElement>) => confirmarSenhaHandle(e)} id='confirmarSenha' label='Confirme a sua senha' variant='outlined' name='confirmarSenha' margin='normal' type='password' fullWidth className="tfCadastro" required />


                                <TextField value={user.altura} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='altura' label='Altura' variant='outlined' name='altura' margin='normal' fullWidth className="tfCadastro" required />
                                <TextField value={user.peso} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='peso' label='Peso(kg)' variant='outlined' name='peso' margin='normal' fullWidth className="tfCadastro" required />
                                <TextField value={user.maxCalories} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='maxCalories' label='Quantidade máxima de calorias necessárias' variant='outlined' name='maxCalories' margin='normal' fullWidth className="tfCadastro" required />
                                <TextField value={user.minCalories} onChange={(e: ChangeEvent<HTMLInputElement>) => updatedModel(e)} id='minCalories' label='Quantidade mínima de calorias necessárias' variant='outlined' name='minCalories' margin='normal' fullWidth className="tfCadastro" required />

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