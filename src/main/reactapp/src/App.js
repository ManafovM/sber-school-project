import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Home from "./Home";
import CounterpartyList from "./CounterpartyList";
import CounterpartyEdit from "./CounterpartyEdit";
import {useForm} from "react-hook-form";
import * as yup from "yup";
import {yupResolver} from "@hookform/resolvers/yup";

function App(props) {
    const schema = yup.object().shape({
        name: yup.string().required("Это обязательное поле.")
            .min(3, "Наименование должно быть длиной от 3 символов.")
            .max(20, "Наименование должно быть длиной до 20 символов."),
        tin: yup.string().required("Это обязательное поле.")
            .matches("[0-9]{10}", "ИНН должен состоять из 10 цифр.")
            .length(10, "ИНН должен состоять из 10 цифр."),
        iec: yup.string().required("Это обязательное поле.")
            .matches("[0-9]{9}", "КПП должен состоять из 9 цифр.")
            .length(9, "КПП должен состоять из 9 цифр."),
        accountNumber: yup.string().required("Это обязательное поле.")
            .matches("[0-9]{20}", "Номер счета должен состоять из 20 цифр.")
            .length(20, "Номер счета должен состоять из 20 цифр."),
        bic: yup.string().required("Это обязательное поле.")
            .matches("[0-9]{9}", "БИК должен состоять из 9 цифр.")
            .length(9, "БИК должен состоять из 9 цифр.")
    });
    const form = useForm({
        mode: "all",
        resolver: yupResolver(schema)
    });

    return (
        <Router>
            <Switch>
                <Route path='/' exact={true} component={Home}/>
                <Route path='/counterparties' exact={true} component={CounterpartyList}/>
                <Route path='/counterparties/:id'>
                    <CounterpartyEdit  {...props} {...form}/>
                </Route>
            </Switch>
        </Router>
    );
}

export default App;
