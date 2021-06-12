import {Component} from "react";
import AppNavbar from "./AppNavbar";
import {Button, Container} from "reactstrap";
import {Link} from "react-router-dom";

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <Button color="link">
                        <Link to="/counterparties">Контрагенты</Link>
                    </Button>
                </Container>
            </div>
        );
    }
}

export default Home;
