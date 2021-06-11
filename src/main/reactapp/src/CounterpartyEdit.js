import {Component} from "react";
import {Button, Container, Form, FormGroup, Input, Label} from "reactstrap";
import {Link, withRouter} from "react-router-dom";
import AppNavbar from "./AppNavbar";

class CounterpartyEdit extends Component {
    emptyItem = {
        name: '',
        tin: '',
        iec: '',
        accountNumber: '',
        bic: '',
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const counterparty = await (
                await fetch(`/counterparties/${this.props.match.params.id}`)).json();
            this.setState({item: counterparty});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch('/counterparties' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/counterparties');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Редактирование контрагента' : 'Добавление контрагента'}</h2>;

        return (
            <div>
                <AppNavbar/>
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="name">Наименование</Label>
                            <Input type="text" name="name" id="name" value={item.name || ''}
                                   onChange={this.handleChange} autoComplete="name"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="tin">ИНН</Label>
                            <Input type="text" name="tin" id="tin" value={item.tin || ''}
                                   onChange={this.handleChange} autoComplete="tin"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="iec">КПП</Label>
                            <Input type="text" name="iec" id="iec" value={item.iec || ''}
                                   onChange={this.handleChange} autoComplete="iec"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="accountNumber">Номер счета</Label>
                            <Input type="text" name="accountNumber" id="accountNumber"
                                   value={item.accountNumber || ''} onChange={this.handleChange}
                                   autoComplete="accountNumber"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="bic">БИК банка</Label>
                            <Input type="text" name="bic" id="bic" value={item.bic || ''}
                                   onChange={this.handleChange} autoComplete="bic"/>
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" type="submit">Сохранить</Button>{' '}
                            <Button color="secondary" tag={Link} to="/counterparties">Отмена</Button>
                        </FormGroup>
                    </Form>
                </Container>
            </div>
        );
    }
}

export default withRouter(CounterpartyEdit);
