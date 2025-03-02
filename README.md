# Lab 3 Part 1: Deploying using Google Kubernetes Engine

Ensure you have Docker and Kubernetes installed

- **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
- **Kubernetes**: [Install kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)

##Files (YAML Files)

- **`mysql-deploy.yaml`**: Defines the MySQL deployment.
- **`mysql-service.yaml`**: Creates a service to expose the MySQL deployment.

##Deployment

###1. Apply the deployment and Service yaml by running the following commands in MySQL:

kubectl apply -f mysql-deploy.yaml
kubectl apply -f mysql-service.yaml

###2. Verify deployment with:

kubectl get pods
kubectl get services

###3. Access the database

kubectl exec -it <pod-name> -- mysql -u user -psofe3980u

(Please ensure you replace the <pod-name> with the running pod name on Kubernetes)

When finished, delete both service and deployment 

kubectl delete -f mysql-deploy.yaml
kubectl delete -f mysql-service.yaml
