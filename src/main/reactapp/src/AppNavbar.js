import {Component} from "react";
import {Navbar, NavbarBrand} from "reactstrap";
import {Link} from "react-router-dom";

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <Navbar color="dark" dark expand="md">
                <NavbarBrand style={{marginLeft: 30}} tag={Link} to="/">На главную</NavbarBrand>
                <NavbarBrand tag={Link} to="/counterparties">Контрагенты</NavbarBrand>
            </Navbar>
        );
    }
}
