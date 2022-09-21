import React from 'react';
import { Grid, Button } from '@material-ui/core';
import { Box } from '@mui/material';
import './Home.css';
import { Link } from 'react-router-dom';

function Home() {
    return (
        <>
            <Grid xs={12} justifyContent="center" alignItems="center">
                <Box className='imgHome' flexDirection="column" justifyContent="center" alignItems="center" display='flex' paddingX={2}>
                    <Box flexDirection="column" justifyContent="center" alignItems="center" display='flex'>
                        <Box marginBottom={2}>
                            <Link to='/loginNutricionista'>
                                <Button className="botaoH" variant="outlined">
                                    Sou nutricionista
                                </Button>
                            </Link>
                        </Box>
                        <Box marginTop={2}>
                            <Button className="botaoH" variant="outlined">
                                Quero uma dieta
                            </Button>
                        </Box>
                    </Box>
                </Box>
            </Grid>
        </>
    );
}

export default Home;