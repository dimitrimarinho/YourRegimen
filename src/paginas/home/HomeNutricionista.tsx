import { Button, Grid } from "@material-ui/core";
import { Box } from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";
import "./HomeNutricionista.css"

function HomeNutricionista() {
    return (
        <Grid xs={12} justifyContent="center" alignItems="center">
            <Box className='imgHome' flexDirection="column" justifyContent="center" alignItems="center" display='flex' paddingX={2}>
                <Box flexDirection="column" justifyContent="center" alignItems="center" display='flex'>
                    <Box marginBottom={2}>
                        <Link to='/cadastrar-categoria' style={{ textDecoration: 'none' }}>
                            <Button className="botaoH" variant="outlined">
                                Cadastrar categoria
                            </Button>
                        </Link>
                    </Box>
                    <Box marginTop={2}>
                        <Link to='/cadastrar-dieta' style={{ textDecoration: 'none' }}>
                            <Button className="botaoH" variant="outlined">
                                Cadastrar dieta
                            </Button>
                        </Link>
                    </Box>
                    <Box marginTop={2}>
                        <Link to='/consultar-alimentos' style={{ textDecoration: 'none' }}>
                            <Button className="botaoH" variant="outlined">
                                Consultar informações nutricionais
                            </Button>
                        </Link>
                    </Box>
                </Box>
            </Box>
        </Grid>
    );
}

export default HomeNutricionista;