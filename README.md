# CrowdFlow AI - Smart Physical Event Experience Platform

An enterprise-grade, full-stack application designed to optimize the physical event experience at large venues by predicting crowd density, estimating queue wait times, and providing an AI assistant for dynamic routing.

---

## 1. Project Overview
CrowdFlow AI is designed to make large-scale physical events (concerts, sports matches, conferences) significantly safer and more enjoyable. By simulating and managing crowds at critical bottlenecks like gates, food courts, and exits, event organizers can preemptively resolve congestions.

## 2. Problem Statement
In large venues, attendees often cluster in certain zones while others remain completely empty. This causes severe wait times at specific gates or stalls and poses a major security hazard. Attendees lack real-time visibility into the venue's crowd distribution.

## 3. Solution Design
CrowdFlow AI provides three primary features:
1. **Live Density Metrics:** Continuously simulated crowd density and AI predictions for future crowding, categorized into standard risk levels.
2. **Queue Wait-Time Estimator:** A statistical processor that translates current queue lengths, service speeds, and active counters into precise wait times.
3. **Smart Assistant:** A conversational bot that gives immediate, accurate recommendations based on real-time crowd and wait-time data.

## 4. Architecture
The project strictly adheres to Enterprise Spring Boot Clean Architecture:
```text
├── Controller Layer      (REST endpoints, Input validation, DTO mapping)
├── Exception Layer       (Global @ControllerAdvice for standardized error response)
├── Service Interfaces    (Business logic contracts)
├── Service Impl Layer    (Implementation of core business rules, SLF4J logging)
├── Repository Layer      (Spring Data JPA for database access)
├── Model/Entity Layer    (Database schema representation)
├── Constants Layer       (Application-wide configurable values)
└── DTO Layer             (Data transfer objects and standard ApiResponse<T>)
```

## 5. API Documentation

All APIs wrap their return payloads in a standard `ApiResponse<T>`:
```json
{
  "status": "SUCCESS",
  "message": "...",
  "data": { ... },
  "timestamp": "2026-04-17T12:00:00.000"
}
```

### Endpoints
- `GET /api/crowd` - Retrieves the current and predicted crowd density.
- `POST /api/wait-time` - Calculates wait times. Body: `{"people": Integer, "serviceTime": Integer, "counters": Integer}`.
- `POST /api/assistant` - Queries the AI. Body: `{"query": String}`.

## 6. Deployment Steps
This project is configured for direct deployment to Google Cloud Run:

1. Enable Cloud Run & Cloud Build.
2. Navigate to `backend` and submit your build:
   `gcloud builds submit --tag gcr.io/YOUR_PROJECT_ID/crowdflow-ai`
3. Deploy the managed container:
   `gcloud run deploy crowdflow-ai --image gcr.io/YOUR_PROJECT_ID/crowdflow-ai --platform managed --allow-unauthenticated --port 8080`

## 7. Future Scope
- **Real AI Integration:** Connect the mocked Assistant logic to Google Gemini or OpenAI APIs for genuine NLP processing.
- **Hardware Integration:** Replace randomized simulation logic with data ingested from physical turnstiles, IoT cameras, or ticket scanners via Kafka streams.
- **Push Notifications:** Alert users automatically via WebSockets when their preferred queue becomes fast.
