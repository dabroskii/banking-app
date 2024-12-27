import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Typography, Box } from '@mui/material';

function LandingPage() {
  const navigate = useNavigate();

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100vh',
        textAlign: 'center',
      }}
    >
      <Typography variant="h3" gutterBottom>
        Welcome to Banking App
      </Typography>
      <Typography variant="h6" gutterBottom>
        Manage your accounts and transactions easily.
      </Typography>
      <Box sx={{ mt: 3 }}>
        <Button
          variant="contained"
          color="primary"
          sx={{ margin: 1 }}
          onClick={() => navigate('/login')}
        >
          Login
        </Button>
        <Button
          variant="outlined"
          color="secondary"
          sx={{ margin: 1 }}
          onClick={() => navigate('/register')}
        >
          Register
        </Button>
      </Box>
    </Box>
  );
}

export default LandingPage;
