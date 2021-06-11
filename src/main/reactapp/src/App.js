import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Home from "./Home";
import CounterpartyList from "./CounterpartyList";
import CounterpartyEdit from "./CounterpartyEdit";

function App() {
    return (
        <Router>
            <Switch>
                <Route path='/' exact={true} component={Home}/>
                <Route path='/counterparties' exact={true} component={CounterpartyList}/>
                <Route path='/counterparties/:id' component={CounterpartyEdit}/>
            </Switch>
        </Router>
    );
}

export default App;
