import React from 'react';
import { Typography, Grid } from '@material-ui/core';
import { Box } from '@mui/material';
import { GitHub } from '@material-ui/icons';
import './Footer.css'

function Footer() {
    return (
        <>
            <Grid container direction="row" justifyContent="center" alignItems="center">
                <Grid alignItems="center" item xs={12}>

                    <Box flexDirection="row" display="flex" justifyContent="center" alignItems="center" className='backF2'>
                        <Box>
                            <Typography variant='h1' className='text'> Acompanhe o nosso projeto:</Typography>
                        </Box>
                        <Box>
                            <a href="https://github.com/dimitrimarinho/YourRegimen" target="_blank" rel="noreferrer" className='text'>
                                <GitHub className='social' />
                            </a>
                        </Box>
                    </Box>

                    <Box className='back' paddingTop={1} paddingBottom={1}>
                        <Typography variant="subtitle2" align="center" gutterBottom style={{ color: "white" }} > YourRegimen© 2022 | Todos os direitos reservados</Typography>
                    </Box>
                </Grid>
            </Grid>
        </>
    )
}

export default Footer;