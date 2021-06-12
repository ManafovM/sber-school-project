import {Component} from "react";
import AppNavbar from "./AppNavbar";
import {Button, Container} from "reactstrap";
import {Link} from "react-router-dom";
import CounterpartySearch from "./CounterpartySearch";

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <Button color="link">
                        <Link to="/counterparties">Контрагенты</Link>
                    </Button>
                    <CounterpartySearch/>
                </Container>
            </div>
        );
    }
}

export default Home;
