import {Component} from "react";
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import {Link} from "react-router-dom";
import AppNavbar from "./AppNavbar";

class CounterpartyList extends Component {
    constructor(props) {
        super(props);
        this.state = {counterparties: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/counterparties')
            .then(response => response.json())
            .then(data => this.setState({counterparties: data._embedded.counterparties}));
    }

    async remove(id) {
        await fetch('counterparties/${id}', {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedCounterparties = [...this.state.counterparties]
                .filter(item => item.id !== id);
            this.setState({counterparties: updatedCounterparties});
        })
    }

    render() {
        const {counterparties, isLoading} = this.state;

        if (isLoading) {
            return <p>Загрузка...</p>;
        }

        const counterpartyList = counterparties.map(counterparty => {
            return (
                <tr key={counterparty.id}>
                    <td style={{whiteSpace: 'nowrap'}}>{counterparty.name}</td>
                    <td>{counterparty.tin}</td>
                    <td>{counterparty.iec}</td>
                    <td>{counterparty.accountNumber}</td>
                    <td>{counterparty.bic}</td>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" tag={Link}
                                    to={"/counterparties/" + counterparty.id}>Редактировать</Button>
                            <Button size="sm" color="danger" onClick={() =>
                                this.remove(counterparty.id)}>Удалить</Button>
                        </ButtonGroup>
                    </td>
                </tr>
            );
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/counterparties/new">Добавить контрагента</Button>
                    </div>
                    <h3>Контрагенты</h3>
                    <Table className={"mt-4"}>
                        <thread>
                            <tr>
                                <th width="15%">Наименование</th>
                                <th width="15%">ИНН</th>
                                <th width="15%">КПП</th>
                                <th width="15%">Номер счета</th>
                                <th width="15%">БИК банка</th>
                                <th width="15%">Действия</th>
                            </tr>
                        </thread>
                        <tbody>
                        {counterpartyList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default CounterpartyList;
