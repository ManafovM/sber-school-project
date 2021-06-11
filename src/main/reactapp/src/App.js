import logo from './logo.svg';
import './App.css';
import {Component} from "react";

class App extends Component{
  state = {
    counterparties: []
  };

  async componentDidMount() {
    const response = await fetch('/counterparties');
    const body = await response.json();
    this.setState({counterparties: body._embedded.counterparties});
  }

  render() {
    const {counterparties} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo"/>
            <div className="App-intro">
              <h2>Контрагенты</h2>
              {counterparties.map(counterparty =>
                  <div key={counterparty.id}>
                    {counterparty.name} {counterparty.tin} {counterparty.iec}
                    {counterparty.accountNumber} {counterparty.bic}
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}

export default App;
