import requests

resp = requests.post('http://localhost:8080/api/v1/gemini/chat',
                     headers={'X-API-KEY': 'YOUR_KEY'},
                     json={'prompt': 'Hello'})
print(resp.json())
