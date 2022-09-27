import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import { ToastContainer } from 'react-toastify';
import { Provider } from 'react-redux';
import CadastroCategoria from './components/categorias/cadastroCategoria/CadastroCategoria';
import DeletarCategoria from './components/categorias/deletarCategoria/DeletarCategoria';
import ListarCategoria from './components/categorias/listarCategoria/ListarCategoria';
import CadastroRegimen from './components/regimens/cadastroRegimen/CadastroRegimen';
import ListaRegimen from './components/regimens/listarRegimen/ListaRegimen';
import CadastroUsuario from './paginas/cadastroUsuario/CadastroUsuario';
import CadastrarCategoria from './paginas/dietas/CadastrarCategoria';
import CadastroAdmin from './paginas/cadastroUsuario/CadastroAdmin';
import HomeNutricionista from './paginas/home/HomeNutricionista';
import DietasPaciente from './paginas/dietas/DietasPaciente';
import CadastrarDieta from './paginas/dietas/CadastrarDieta';
import Navbar from './components/estaticos/navbar/Navbar';
import Footer from './components/estaticos/footer/Footer';
import LoginAdmin from './paginas/Login/LoginAdmin';
import Login from './paginas/Login/Login';
import Home from './paginas/home/Home';
import store from './store/store';
import 'react-toastify/dist/ReactToastify.css';
import './App.css';

function App() {
  return (
    <Provider store={store}>
      <ToastContainer />
      <Router>
        <Navbar />
        <div style={{ minHeight: '100vh' }}>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/home" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/cadastroUsuario" element={<CadastroUsuario />} />
            <Route path="/categorias" element={<ListarCategoria />} />
            <Route path="/formularioCategoria" element={<CadastroCategoria />} />
            <Route path="/formularioCategoria/:idCategoria" element={<CadastroCategoria />} />
            <Route path="/deletarCategoria/:idCategoria" element={<DeletarCategoria />} />
            <Route path="/regimens" element={<ListaRegimen />} />
            <Route path="/formularioRegimen" element={<CadastroRegimen />} />
            <Route path="/formularioRegimen/:idRegimen" element={<CadastroRegimen />} />
            <Route path="/cadastroNutricionista" element={<CadastroAdmin />} />
            <Route path="/loginNutricionista" element={<LoginAdmin />} />
            <Route path="/minhadieta" element={<DietasPaciente />} />
            <Route path="/home-nutricionista" element={<HomeNutricionista />} />
            <Route path="/cadastrar-categoria" element={<CadastrarCategoria />} />
            <Route path="/cadastrar-dieta" element={<CadastrarDieta />} />
            <Route
              path="/*"
              element={<Navigate to="/" replace />}
            />
          </Routes>
        </div>
        <Footer />

      </Router>
    </Provider>
  );
}

export default App;