import { Button, Card, CardActions, CardContent, Grid, TextField, Typography } from "@material-ui/core";
import { Box } from "@mui/material";
import React, { ChangeEvent, useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import Regimen from "../../model/Regimen";
import { busca } from "../../service/Service";
import { TokenState } from "../../store/tokens/tokensReducer";
import "./DietasPaciente.css"

function DietasPaciente() {

    let navigate = useNavigate()
    const token = useSelector<TokenState, TokenState["tokens"]>(
        (state) => state.tokens
    )
    const [posts, setPosts] = useState<Regimen[]>([])
    const [nomeProduto, setNomeProduto] = useState("");
    const [myProdutos, setMyProdutos] = useState<Regimen[]>([])

    useEffect(() => {
        if (token === '') {
            toast.error('Você precisa estar logado!', {
                position: "top-right",
                autoClose: 2000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: false,
                draggable: false,
                theme: "colored",
                progress: undefined,
            });
            navigate('/login')
        }
    }, [token])



    const updateNomeDieta = (e: ChangeEvent<HTMLInputElement>) => {
        setNomeProduto(e.target.value)
    }

    async function getDietasByName() {
        if (nomeProduto !== "")
            await busca(`/service-diet/regimen/regimenName/${nomeProduto}`, setPosts,
                {
                    headers: {
                        'Authorization': ""
                    }
                })
    }

    useEffect(() => {
        getDietasByName()
    }, [posts.length])

    return (
        <>
            <Grid xs={12}>
                <Box display="flex" alignItems="center" flexDirection="column" justifyContent="center" className="img1" gap="64px">
                    <Box display="flex" alignItems="center" flexDirection="column" justifyContent="center" >
                        <Typography variant="h1" align="center" className="tituloCnts">
                            Tenha uma vida mais saudável
                        </Typography>
                    </Box>
                </Box>
            </Grid>

            <Grid xs={12} container justifyContent="center" alignContent="center">
                <Box paddingLeft={0} className="introC1">
                    <Typography variant="h4" align="center" className="tituloC1">Selecione uma opção de dieta</Typography>
                </Box>
            </Grid>

            <Box className="margem">
                <Box display="flex" gap="24px" flexDirection="row" >
                    <Box flexDirection="column" justifyContent="center" alignItems="center" display='flex'>
                        <Box marginLeft={3} justifyContent="center" alignItems="center" display='flex' >
                            <TextField value={nomeProduto} label="Ex: Perda de peso" type="search" fullWidth variant='outlined' onChange={(e: ChangeEvent<HTMLInputElement>) => updateNomeDieta(e)} />

                            <Box marginLeft={3} justifyContent="center" alignItems="center" display='flex'>

                                <Button className="botaoH" variant="outlined" onClick={() => getDietasByName()}>
                                    <Typography> Buscar </Typography>
                                </Button>

                            </Box>
                        </Box>
                        <Box >
                        </Box>
                    </Box>
                </Box>
            </Box>

            {
                posts.map(post => (
                    <Box m={2} >
                        <Card variant="outlined">
                            <CardContent>
                                <Typography color="textSecondary" gutterBottom>
                                    Lista de alimentos
                                </Typography>
                                <Typography variant="h5" component="h2">
                                    {post.regimenName}
                                </Typography>
                                <Typography variant="body2" component="p">
                                    {post.foodList}
                                </Typography>
                            </CardContent>
                            <CardActions>
                            </CardActions>
                        </Card>
                    </Box>
                ))
            }
        </>
    )
}

export default DietasPaciente;