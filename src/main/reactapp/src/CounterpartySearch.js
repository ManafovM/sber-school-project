import {Component} from "react";
import {Button, ButtonGroup, Container, Form, FormGroup, Input, Label, Table} from "reactstrap";
import {Link} from "react-router-dom";

export default class CounterpartySearch extends Component {
    constructor(props) {
        super(props);
        this.state = {
            counterparty: {},
            searchName: '',
            searchAccount: '',
            searchBic: '',
            submitted: '',
        };
        this.handleChange = this.handleChange.bind(this);
        this.searchByName = this.searchByName.bind(this);
        this.searchByAccountAndBic = this.searchByAccountAndBic.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const name = target.name;
        const value = target.value;
        let state = {...this.state};
        state[name] = value;
        this.setState(state);
    }

    async searchByName(event) {
        event.preventDefault();
        const searchName = this.state.searchName;
        await this.handleSubmit(`/counterparties/by-name?name=${searchName}`);
    }

    async searchByAccountAndBic(event) {
        event.preventDefault();
        const searchAccount = this.state.searchAccount;
        const searchBic = this.state.searchBic;
        await this.handleSubmit(`/counterparties/by-account-and-bic?account=${searchAccount}&bic=${searchBic}`);
    }

    async handleSubmit(URI) {
        const counterparty = await fetch(URI)
            .then(response => {
                return response.text()
            })
            .then((data) => {
                return (data ? JSON.parse(data) : {})
            });
        if (counterparty.name) {
            this.setState({counterparty: counterparty});
            this.setState({submitted: true})
        } else {
            this.setState({submitted: false});
        }
    }

    renderSearchResponse() {
        const counterparty = this.state.counterparty;
        const submitted = this.state.submitted;

        if (submitted) {
            return (
                <Table className={"mt-4"}>
                    <thead>
                    <tr>
                        <th width="15%">Наименование</th>
                        <th width="15%">ИНН</th>
                        <th width="15%">КПП</th>
                        <th width="15%">Номер счета</th>
                        <th width="15%">БИК банка</th>
                        <th width="15%">Действия</th>
                    </tr>
                    </thead>
                    <tbody>
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
                    </tbody>
                </Table>
            );
        } else if (submitted !== '') {
            return <p>Ничего не найдено.</p>;
        }
    }

    render() {
        return (
            <Container style={{marginTop: 20}}>
                <h4>Поиск контрагента по наименованию</h4>
                <Form onSubmit={this.searchByName}>
                    <FormGroup>
                        <Label for="name">Наименование</Label>
                        <Input type="text" name="searchName" id="name" onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Button style={{marginTop: 10}} color="primary" type="submit">Найти</Button>
                    </FormGroup>
                </Form>
                <h4 style={{marginTop: 20}}>Поиск контрагента по номеру счета и БИК банка</h4>
                <Form onSubmit={this.searchByAccountAndBic}>
                    <FormGroup>
                        <Label for="account">Номер счета</Label>
                        <Input type="text" name="searchAccount"
                               id="account" onChange={this.handleChange}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label for="bic">БИК банка</Label>
                        <Input type="text" name="searchBic" id="bic" onChange={this.handleChange}/>
                    </FormGroup>
                    <FormGroup>
                        <Button style={{marginTop: 10}} color="primary" type="submit">Найти</Button>
                    </FormGroup>
                </Form>
                {this.renderSearchResponse()}
            </Container>
        );
    }
}
