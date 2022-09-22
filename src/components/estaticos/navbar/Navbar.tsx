import React from 'react';
import { AppBar, Toolbar, Typography, Grid } from '@material-ui/core';
import { Box } from '@mui/material';
import './Navbar.css'

function Navbar() {
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
                                <Typography className="padding-left-navbar" variant="h6" color="inherit">
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