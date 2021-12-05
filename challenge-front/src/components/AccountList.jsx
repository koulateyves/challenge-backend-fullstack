import React, {useState, useEffect} from 'react';
import axios from 'axios';
import App from "./App";

export default function AccountList() {

    const [loading, setLoading] = useState(true);
    const [roundedValue, setRoundedValue] = useState();
    const [listAccounts, setListAccounts] = useState();

    useEffect(() => {
        // GET request using axios inside useEffect React hook
        axios.get('http://localhost:8080/accounts/all')
            .then(response => {
                setLoading(false);
                setListAccounts(response.data.accounts[0])
                setRoundedValue( response.data.roundedValue,)
            })
            .catch(err => console.log(err))
    }, []);

    if (loading) {
        return getLoadingTemplate();
    }

    function getLoadingTemplate() {
        return <div className="AccountList"> Loading ...</div>;
    }

    function accountData() {
        console.log("***** " + listAccounts);
            return (!listAccounts)? getLoadingTemplate() : listAccounts.map((data, index) => {
                return (
                    <tr key={index}>
                        <td>{data.id}</td>
                        <td>{data.name}</td>
                        <td>{data.balance}</td>
                        <td>{data.currency_code}</td>
                        <td>{data.type}</td>
                    </tr>
                )
            });
    }

    return (

        <div>
            <h1>Account List</h1>
            <br/> <label> Global Balance : <b>{roundedValue}</b></label>

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
                {accountData()}
                </tbody>
            </table>
        </div>
    );
}
