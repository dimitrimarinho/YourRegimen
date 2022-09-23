import React from 'react';
import { AppBar, Toolbar, Typography, Grid } from '@material-ui/core';
import { Box } from '@mui/material';
import { useDispatch, useSelector } from "react-redux";
import { TokenState } from "../../../store/tokens/tokensReducer";
import { addToken } from "../../../store/tokens/actions";
import { toast } from "react-toastify";
import { Link, useNavigate } from "react-router-dom";
import './Navbar.css'

function Navbar() {
    const token = useSelector<TokenState, TokenState["tokens"]>(
        (state) => state.tokens
    );
    let navigate = useNavigate();
    const dispatch = useDispatch();

    function sair() {
        dispatch(addToken(''))
        toast.info("Usuario deslogado", {
            position: "top-right",
            autoClose: 2000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: false,
            theme: "colored",
            progress: undefined


        })
        navigate("/home")
    }

    return (
        <>
            <AppBar position="sticky" >
                <Toolbar variant="dense" className='backF2'>
                    <Grid container justifyContent="space-between" >
                        <Box display="flex" justifyContent="flex-start" alignItems="center" >
                            <img src="https://i.imgur.com/LwF0S1v.png" title="Logo YourRegimen" width="230px" alt="Logo do Site com o nome YourRegimen" />
                        </Box>

                        <Box display="flex" justifyContent="center" alignItems="center" >
                            <Box paddingX={3} >
                                <Typography className="padding-left-navbar" variant="h6" color="inherit" >
                                    Home
                                </Typography>
                            </Box>
                            <Box paddingX={3}>
                                <Typography className="padding-left-navbar" variant="h6" color="inherit">
                                    Sobre nós
                                </Typography>
                            </Box>
                            <Box paddingX={3}>
                                <Typography className="cursor-navbar" variant="h6" color="inherit" onClick={sair}>
                                    Sair
                                </Typography>
                            </Box>
                        </Box>
                    </Grid>
                </Toolbar>
            </AppBar>
        </>
    )
}

export default Navbar;