import React from 'react';
import { Link, Route, Switch, useHistory } from 'react-router-dom';
import logo from '../assets/logo.svg';
import Home from './Home';
import About from './About';
import AccountList from './AccountList';

export default function App() {
  const history = useHistory();

  return (
    <div>
      <header>
        <img src={logo} alt="logo" onClick={() => history.push('/')} />
      </header>

      <nav className="container">
        <Link to="/">Home</Link>
        <Link to="/about">About</Link>
        <Link to="/accounts">Accounts</Link>
      </nav>

      <main className="container">
        <Switch>
          <Route exact path="/" component={Home} />
          <Route exact path="/about" component={About} />
          <Route exact path="/accounts" component={AccountList} />
        </Switch>
      </main>
    </div>
  );
}
