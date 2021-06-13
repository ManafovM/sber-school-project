import {Component} from "react";
import AppNavbar from "./AppNavbar";
import {Container} from "reactstrap";
import CounterpartySearch from "./CounterpartySearch";

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <CounterpartySearch/>
                </Container>
            </div>
        );
    }
}

export default Home;
