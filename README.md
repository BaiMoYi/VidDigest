# VidDigest

VidDigest integrates Google Gemini 2.5 Pro with Spring Boot to analyze videos and produce structured text. The project is a minimal example following the guidelines from `AGENTS.md`.

## Features

- Extract video links from Bilibili (placeholder implementation)
- Process both audio and video streams
- Convert speech to text
- Summarize the content with Gemini 2.5 Pro

## Getting Started

1. Copy `.env.sample` to `.env` and fill in your `GEMINI_API_KEY`.
2. Run the application:

```bash
./mvnw spring-boot:run
```

After startup, visit `http://localhost:8080/swagger-ui.html` to try the API.
The OpenAPI specification is available at `api/openapi.yaml` and includes API Key security scheme examples.

## Plugin Management

Plugins packaged as JARs can be loaded or unloaded at runtime using the `/api/v1/plugins` endpoints. Example:

```bash
curl -X POST "http://localhost:8080/api/v1/plugins/load?path=/path/to/plugin.jar" -H 'X-API-KEY: YOUR_KEY'
```

Loaded plugins expose metrics under the `plugin.process.time` Prometheus metric and are included in the health endpoint.

See `QUICKSTART.md` for more instructions.
