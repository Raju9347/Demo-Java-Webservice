
# 📘 CI/CD Pipeline with GitOps, Kubernetes & Terraform

## Project Objective

Design and implement a **production-ready CI/CD workflow** with infrastructure automation, secure container image delivery, observability, and Kubernetes deployment using modern DevOps best practices.

This project demonstrates:
- Application containerization using Docker
- CI pipeline with testing, scanning, and image publishing
- GitOps-based CD using ArgoCD and Helm
- Infrastructure provisioning using Terraform
- Observability, autoscaling, and resilience in Kubernetes

---

## 🏗️ Architecture Diagram (High-Level)

<img width="1536" height="1024" alt="CI/CD Architecture" src="https://github.com/user-attachments/assets/478477aa-2a75-4720-b39d-622e2a52606d" />

---

## ⚙️ Application Overview

- **Application Type:** RESTful Web Application
- **Language / Framework:** Java – Spring Boot
- **APIs Implemented:**
  - `GET /health` – Application health check
  - `GET /config` – Reads values from ConfigMap / file
  - `GET /items` – Returns application data
- **Testing:** Unit tests executed during CI
- **Metrics:** Exposes Spring Boot Actuator metrics (Prometheus compatible)

---

## 🐳 Dockerization Details

- **Multi-stage Docker build**  
  - Builder stage: `maven:3.8.5-openjdk-17`  
  - Runtime stage: `openjdk:17-jdk-slim`

- **Maven build & JAR packaging**  
  The builder stage runs `mvn clean package` to compile and test the application.  
  The generated JAR is copied to the runtime image.

- **Non-root container user**  
  A dedicated non-root user (`app`) is configured to improve container security.

- **Docker HEALTHCHECK**  
  The Dockerfile includes a health check that calls: /actuator/health

- **Production-ready Dockerfile**  
- Optimized image size
- Explicit version pinning
- Clear separation of build and runtime stages
- Environment-based configuration

---

## 🔁 CI Pipeline Structure

**CI Tool:** GitHub Actions  

### CI Workflow Steps
1. Checkout source code
2. Run unit tests
3. Build Docker image
4. Scan Docker image using **Trivy**
5. Push image to **AWS ECR (private repository)**

### CI Benefits
- Automated and repeatable builds
- Early vulnerability detection
- Secure image delivery to private registry

---

## 🚀 CD Pipeline & GitOps Flow

**CD Tool:** ArgoCD  
**Deployment Method:** Helm  

### GitOps Workflow
1. CI builds and pushes a new image to ECR
2. Helm values / manifests are updated in Git
3. ArgoCD continuously monitors the Git repository
4. ArgoCD syncs the desired state to Kubernetes
5. Application is deployed using **rolling updates**

### Kubernetes Deployment Features
- Deployment, Service, and Ingress (NGINX)
- ConfigMap mounted at runtime
- Kubernetes Secrets for sensitive values
- Resource requests and limits
- Horizontal Pod Autoscaler (CPU-based)
- Liveness and readiness probes

---

## ☁️ Infrastructure as Code (Terraform)

**Cloud Provider:** AWS  

### Resources Provisioned
- VPC with public and private subnets
- S3 bucket with versioning enabled (Terraform backend)

### Terraform Best Practices
- Modular Terraform structure
- Remote backend using S3
- State locking using DynamoDB
- Environment separation using Terragrunt (optional)

---

## ☸️ Kubernetes Deployment Requirements

- Deployment, Service, and Ingress
- ConfigMap and Secret
- Horizontal Pod Autoscaler
- Liveness and readiness probes
- Logging via Fluent Bit or Kubernetes logs
- Monitoring via Prometheus metrics endpoint

---

## ▶️ Access APIs (Local / Cluster)

http://localhost:8080/health
http://localhost:8080/config
http://localhost:8080/items
http://localhost:8080/api/config/stress

## 🧾 Conclusion

This project demonstrates a complete **CI/CD and GitOps-based delivery pipeline**, from application development to secure, scalable Kubernetes deployment, following industry-standard DevOps and cloud-native best practices.


## 📸 Screenshots for Reference

### CI Pipeline – GitHub Actions
![CI Pipeline Success] 
<img width="752" height="678" alt="image" src="https://github.com/user-attachments/assets/0e2596c7-8954-45d4-8864-df439b92a0ca" />

### Image Security Scan (Trivy)
<img width="1904" height="976" alt="image" src="https://github.com/user-attachments/assets/21b01935-0f29-4b12-858a-615a1ec9ef0e" />

### Docker Image in AWS ECR
<img width="1920" height="876" alt="image" src="https://github.com/user-attachments/assets/5c93621d-62c9-4197-bd35-3bcff59164d5" />

### ArgoCD Application (Synced & Healthy)
<img width="1918" height="1030" alt="image" src="https://github.com/user-attachments/assets/87b8ba34-1730-4fcd-8ca1-e41dff64f1a6" />

### Kubernetes Pods & HPA
<img width="1498" height="583" alt="image" src="https://github.com/user-attachments/assets/5afaee7c-e4cf-416e-ad66-a828b4e26b58" />

### Application API Response
<img width="1051" height="386" alt="image" src="https://github.com/user-attachments/assets/584c9ccb-9931-4aad-957e-20fe38f4f5c8" />

<img width="784" height="256" alt="image" src="https://github.com/user-attachments/assets/117326d9-ca36-44c1-ac4b-169cbf76e8d0" />

<img width="810" height="347" alt="image" src="https://github.com/user-attachments/assets/d95d72fc-cc62-42f7-83a8-968597f815b1" />


### HPA Reached Target % 
![When-HPA-Reach-target-Persantage-Pods-Increased](https://github.com/user-attachments/assets/4f3928da-6033-4a82-a8bc-546bfce6c199)

![When-HPA-Reach-target-Persantage-Pods-Increased](https://github.com/user-attachments/assets/65497866-ab70-470d-9ab4-7683a5a85097)

![After-HPA-Performed-SS](https://github.com/user-attachments/assets/baacd438-e07d-4cb2-bfa4-c1a7babae842)

### CI Pipeline – GitHub Actions
*Successful CI pipeline showing build, test, scan, and image push to ECR.*

<img width="684" height="721" alt="image" src="https://github.com/user-attachments/assets/06661315-aa4d-4a4e-b210-82d4487e155f" />




---
## Terraform Resource Creation Structure..

<img width="866" height="892" alt="image" src="https://github.com/user-attachments/assets/c3413874-0cc5-46f1-85af-2a657226702d" />


## 🌐 Terraform Infrastructure – VPC

Terraform is used to provision a **custom VPC** with multiple subnets to support Kubernetes workloads.

- Custom CIDR block
- Public and private subnets
- Designed for high availability

### VPC Created via Terraform

<img width="592" height="564" alt="image" src="https://github.com/user-attachments/assets/455b4eea-f9c8-44b7-bcd6-90760eef25c0" />

<img width="1006" height="928" alt="image" src="https://github.com/user-attachments/assets/04ffadc3-ad0b-409d-b8ac-f979bcfc702d" />


## 🗄️ Terraform Infrastructure – S3 Bucket

Terraform provisions an **S3 bucket with versioning enabled**, which is used as a **remote backend** for Terraform state storage.

### S3 Bucket Created via Terraform

<img width="1186" height="904" alt="image" src="https://github.com/user-attachments/assets/8512ee89-7cc9-4df4-9018-e944e14c9110" />


