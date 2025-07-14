param([string]$Prompt)
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/gemini/chat" `
  -Headers @{ 'X-API-KEY' = 'YOUR_KEY' } `
  -Body (@{ prompt = $Prompt } | ConvertTo-Json) `
  -ContentType "application/json"
