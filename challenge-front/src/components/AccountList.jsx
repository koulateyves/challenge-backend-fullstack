import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function AccountList() {

    const [appState, setAppState] = useState(
        {
            listAccounts : null,
            roundedValue : null,
            loading : true
        }
    ) ;

    useEffect(() => {
      // GET request using axios inside useEffect React hook
      axios.get('http://localhost:8080/accounts/all')
          .then(response => setAppState({listAccounts : response.data.accounts[0], roundedValue : response.data.roundedValue, loading : false}))
          .catch( err => console.log(err))
    }, []);


    return (

        <div>
            <h1>Account List</h1>
      <br /> <label> Global BalanceAccount : {appState.roundedValue}</label>

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Balance</th>
            <th>Currency</th>
            <th>Type</th>
          </tr>
        </thead>
        <tbody>

        {appState.listAccounts.map((data, index) => {
            return (
                <tr key={index}>
                    <td>{data.id}</td>
                    <td>{data.name}</td>
                    <td>{data.balance}</td>
                    <td>{data.type}</td>
                    <td>{data.currency_code}</td>
                </tr>
            )
        })}
        </tbody>
      </table>
    </div>
  );
}
