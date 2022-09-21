import React from 'react';
import Navbar from './components/estaticos/navbar/Navbar';
import Footer from './components/estaticos/footer/Footer';
import Home from './paginas/home/Home';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';
import Login from './paginas/Login/Login';
import CadastroUsuario from './paginas/cadastroUsuario/CadastroUsuario';
import CadastroCategoria from './components/categorias/cadastroCategoria/CadastroCategoria';
import DeletarCategoria from './components/categorias/deletarCategoria/DeletarCategoria';
import ListarCategoria from './components/categorias/listarCategoria/ListarCategoria';
import ListaRegimen from './components/regimens/listarRegimen/ListaRegimen';
import CadastroRegimen from './components/regimens/cadastroRegimen/CadastroRegimen';
import { Provider } from 'react-redux';
import { ToastContainer } from 'react-toastify';
import store from './store/store';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <Provider store={store}>
      <ToastContainer />
      <Router>
        <Navbar />
        <div style={{ minHeight: '100vh' }}>
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/home" element={<Home />} />
            <Route path="/cadastroUsuario" element={<CadastroUsuario />} />
            <Route path="/categorias" element={<ListarCategoria />} />
            <Route path="/formularioCategoria" element={<CadastroCategoria />} />
            <Route path="/formularioCategoria/:idCategoria" element={<CadastroCategoria />} />
            <Route path="/deletarCategoria/:idCategoria" element={<DeletarCategoria />} />
            <Route path="/regimens" element={<ListaRegimen />} />
            <Route path="/formularioRegimen" element={<CadastroRegimen />} />
            <Route path="/formularioRegimen/:idRegimen" element={<CadastroRegimen />} />

          </Routes>
        </div>
        <Footer />

      </Router>
    </Provider>
  );
}

export default App;
