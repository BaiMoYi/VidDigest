# 快速上手指南

## 环境准备
1. 安装 JDK 21、Maven、Redis。
2. 复制 `.env.sample` 为 `.env`，填写 `GEMINI_API_KEY`。

## 运行
```bash
./mvnw spring-boot:run
```
访问 `http://localhost:8080/swagger-ui.html` 查看接口文档。

### 插件管理
使用 REST 接口在线加载或卸载插件：

```bash
curl -X POST "http://localhost:8080/api/v1/plugins/load?path=/path/to/plugin.jar" -H 'X-API-KEY: YOUR_KEY'
```

## 常见问题
- **依赖下载失败**：检查网络代理。
- **401 未授权**：确认 `X-API-KEY` 是否正确。
