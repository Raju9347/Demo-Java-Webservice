# 📘 CI/CD Pipeline with GitOps, Kubernetes & Terraform

## Project Objective

Design and implement a **production-ready CI/CD workflow** with infrastructure automation, secure container image delivery, observability, and Kubernetes deployment using modern DevOps best practices.

This project demonstrates:
- Application containerization
- CI pipeline with testing, linting, scanning, and image publishing
- GitOps-based CD using Kubernetes
- Infrastructure provisioning using Terraform
- Observability, autoscaling, and resilience in Kubernetes

---

## 🏗️ Architecture Diagram (High-Level)
<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/478477aa-2a75-4720-b39d-622e2a52606d" />


---

## ⚙️ Application Overview

- **Application Type:** RESTful Web Application
- **Language/Framework:** Example web framework (any language)
- **APIs Implemented:**
  - `GET /health` – Health check endpoint
  - `GET /config` – Reads data from ConfigMap / file / database
  -  GET /items - Get items from app
- **Testing:** Simple unit tests included
- **Metrics:** Exposes Prometheus-compatible metrics endpoint

---

## 🐳 Dockerization Details

- **Multi-stage Docker build**  
  Used a builder stage (`maven:3.8.5-openjdk-17`) to compile the application and run unit tests, followed by a lightweight runtime stage (`openjdk:17-jdk-slim`) to reduce image size.

- **Maven build & JAR packaging**  
  The builder stage runs `mvn clean package` to generate the application JAR. The JAR is then copied into the runtime container for execution.

- **Non-root user for container security**  
  Configured a dedicated non-root user (`app`) in the runtime stage to follow container security best practices.

- **Docker HEALTHCHECK**  
  Implemented a healthcheck in the Dockerfile to periodically call the `/actuator/health` endpoint, ensuring the container is healthy and ready to serve traffic.

- **Production-ready Dockerfile**  
  Optimized for size, security, and maintainability:
  - Minimal base image
  - Explicit version pinning
  - Clear separation of build and runtime stages
  - Environment variables for configuration

**Image Tagging Strategy**
---

## 🔁 CI Pipeline Structure

**CI Tool:** GitHub Actions  

### CI Workflow Steps
1. Checkout source code
2. Run unit tests
4. Build Docker image
5. Scan Docker image using **Trivy**
6. Push image to **AWS ECR (private repository)**

### CI Benefits
- Automated and repeatable builds
- Early detection of vulnerabilities
- Secure container delivery

---

## 🚀 CD Pipeline & GitOps Flow

**CD Tool:** ArgoCD  
**Deployment Method:** Helm  

### GitOps Workflow
1. CI pipeline pushes a new container image
2. Kubernetes manifests / Helm values are updated in Git
3. ArgoCD continuously monitors Git
4. ArgoCD syncs desired state to the Kubernetes cluster
5. Application is deployed using **rolling updates**

### Kubernetes Deployment Features
- Deployment, Service, and Ingress (NGINX)
- ConfigMap mounted at runtime
- Secret for sensitive data
- Resource requests and limits
- Horizontal Pod Autoscaler (CPU-based)
- Liveness and readiness probes

---

## ☁️ Infrastructure as Code (Terraform)

**Cloud Provider:** AWS or Azure  

### Resources Provisioned
- VPC with subnets
- S3 bucket with versioning


### Terraform Best Practices
- Modular Terraform design
- Remote backend for state storage
- State locking (DynamoDB / native locking)
- Environment separation using Terragrunt (if applicable)

---

## ☸️ Kubernetes Deployment Requirements

- Deployment, Service, and Ingress
- ConfigMap and Secret
- Autoscaling using HPA
- Liveness and readiness probes
- Logging via Fluent Bit or cluster logs
- Monitoring via Prometheus metrics endpoint

---

Access API's

http://localhost:8080/health
http://localhost:8080/config
http://localhost:8080/items




