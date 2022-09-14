import React from 'react';
import { AppBar, Toolbar, Typography, Grid } from '@material-ui/core';
import { Box } from '@mui/material';

function Navbar() {
    return (
        <>
            <AppBar position="sticky" className='back'>
                <Toolbar variant="dense">
                    <Grid container justifyContent="space-between">
                        <Box display="flex" justifyContent="flex-start" alignItems="center">
                            <img src="https://i.imgur.com/LwF0S1v.png" title="Logo YourRegimen" width="230px" />
                        </Box>

                        <Box display="flex" justifyContent="center" alignItems="center">
                            <Box paddingX={3}>
                                <Typography variant="h6" color="inherit">
                                    Home
                                </Typography>
                            </Box>
                            <Box paddingX={3}>
                                <Typography variant="h6" color="inherit">
                                    Sobre nós
                                </Typography>
                            </Box>
                            <Box paddingX={3}>
                                <Typography variant="h6" color="inherit">
                                    Solicite sua dieta
                                </Typography>
                            </Box>
                            <Box paddingX={3}>
                                <Typography variant="h6" color="inherit">
                                    Logout
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