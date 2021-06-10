import './css/App.css';
import {Component} from "react";
const client = require('./client');

class App extends Component{
  constructor(props) {
    super(props);
    this.state = {counterparties: []};
  }

  componentDidMount() {
    client({method: 'GET', path: '/api/counterparties'}).done(response => {
      this.setState({counterparties: response.entity._embedded.counterparties});
    });
  }

  render() {
    return (
        <div className="App">
          <header className="App-header">
            <div className="App-intro">
              <h2>Контрагенты</h2>
              <CounterpartyList counterparties={this.state.counterparties}/>
            </div>
          </header>
        </div>
    );
  }
}

class CounterpartyList extends Component{
  render() {
    const counterparties = this.props.counterparties.map(counterparty =>
        <Counterparty key={counterparty._links.self.href} counterparty={counterparty}/>
    );
    return (
        <table>
          <tbody>
          <tr>
            <th>Наименование</th>
            <th>ИНН</th>
            <th>КПП</th>
            <th>Номер счета</th>
            <th>БИК</th>
          </tr>
          {counterparties}
          </tbody>
        </table>
    )
  }
}

class Counterparty extends Component{
  render() {
    return (
        <tr>
          <td>{this.props.counterparty.name}</td>
          <td>{this.props.counterparty.tin}</td>
          <td>{this.props.counterparty.iec}</td>
          <td>{this.props.counterparty.accountNumber}</td>
          <td>{this.props.counterparty.bic}</td>
        </tr>
    )
  }
}

export default App;
