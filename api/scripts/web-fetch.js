fetch('/api/v1/gemini/chat', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'X-API-KEY': 'YOUR_KEY'
  },
  body: JSON.stringify({ prompt: 'Hello' })
}).then(r => r.json()).then(console.log);
