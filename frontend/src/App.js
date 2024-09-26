import "./App.css";
import CompraScreen from "./CompraScreen";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
 /* const [characters, setCharacters] = useState(null);

  const reqApi = async () => {
    const api = await fetch("http://localhost:8081/api/descuentos");

    const charactersApi = await api.json();
    console.log(charactersApi);

  };

  return (
    <div className="App">
      <header className="App-header">
        <h1 className="title">Rick & Morty </h1>

            <button onClick={reqApi} className="btn-search">
              Buscar personajes
            </button>
      </header>
    </div>
  );*/
  const api = process.env.REACT_APP_API_GW;

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={<CompraScreen api={api} />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
