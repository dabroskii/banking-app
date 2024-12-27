import React, { useEffect, useState } from 'react';
import api from '../services/api';
import { Button, Card, CardContent, Typography, List, ListItem, ListItemText } from '@mui/material';

function Dashboard() {
  const [accounts, setAccounts] = useState([]);
  const [transactions, setTransactions] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchAccounts = async () => {
      try {
        const response = await api.get('/api/accounts');
        setAccounts(response.data);
      } catch (err) {
        setError('Failed to fetch accounts. Please try again later.');
      }
    };

    fetchAccounts();
  }, []);

  const fetchTransactions = async (accountId) => {
    try {
      const response = await api.get(`/api/transactions/${accountId}`);
      setTransactions(response.data);
    } catch (err) {
      setError('Failed to fetch transactions. Please try again later.');
    }
  };

  return (
    <div>
      <Typography variant="h4" gutterBottom>
        Your Bank Accounts
      </Typography>
      {error && <Typography color="error">{error}</Typography>}
      <List>
        {accounts.map((account) => (
          <Card key={account.accountId} variant="outlined" style={{ marginBottom: '1rem' }}>
            <CardContent>
              <Typography variant="h6">{account.accountType} Account</Typography>
              <Typography variant="body1">Balance: ${account.accountBalance.toFixed(2)}</Typography>
              <Button variant="contained" onClick={() => fetchTransactions(account.accountId)}>
                View Transactions
              </Button>
            </CardContent>
          </Card>
        ))}
      </List>

      {transactions.length > 0 && (
        <div>
          <Typography variant="h5" gutterBottom>
            Scheduled Transactions
          </Typography>
          <List>
            {transactions.map((transaction) => (
              <ListItem key={transaction.transactionId}>
                <ListItemText
                  primary={`$${transaction.transactionAmount.toFixed(2)} to Account ${transaction.receivingAccountId}`}
                  secondary={`Date: ${new Date(transaction.date).toLocaleDateString()}`}
                />
              </ListItem>
            ))}
          </List>
        </div>
      )}
    </div>
  );
}

export default Dashboard;
