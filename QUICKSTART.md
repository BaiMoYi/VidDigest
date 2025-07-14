# 快速上手指南

## 环境准备
1. 安装 JDK 21、Maven、Redis 与 Node.js（用于生成 TypeScript 客户端）。
2. 复制 `.env.sample` 为 `.env`，填写 `GEMINI_API_KEY`、`VAULT_URI` 等参数。
3. 可选：安装 Docker 以运行集成测试所需的 Redis、Vault 容器。

## 运行
```bash
./mvnw spring-boot:run
```
访问 `http://localhost:8080/swagger-ui.html` 查看接口文档。

### 示例调用

**Web Fetch**

```javascript
fetch('/api/v1/gemini/chat', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json', 'X-API-KEY': 'YOUR_KEY' },
  body: JSON.stringify({ prompt: 'Hello' })
}).then(r => r.json()).then(console.log);
```

**PowerShell**

```powershell
param([string]$Prompt)
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/gemini/chat" `
  -Headers @{ 'X-API-KEY' = 'YOUR_KEY' } `
  -Body (@{ prompt = $Prompt } | ConvertTo-Json) `
  -ContentType "application/json"
```

**Python**

```python
import requests
resp = requests.post('http://localhost:8080/api/v1/gemini/chat',
                     headers={'X-API-KEY': 'YOUR_KEY'},
                     json={'prompt': 'Hello'})
print(resp.json())
```

### 插件管理
使用 REST 接口在线加载或卸载插件：

```bash
curl -X POST "http://localhost:8080/api/v1/plugins/load?path=/path/to/plugin.jar" -H 'X-API-KEY: YOUR_KEY'
```

## 常见故障排查
- **依赖下载失败**：检查网络代理设置或使用国内镜像。
- **401 未授权**：确认 `X-API-KEY` 是否正确配置。
- **无法连接 Redis/Vault**：核对 `.env` 中的地址与端口是否可用。
