```shell
kind create cluster --config=kind.yaml
kubectl cluster-info
kubectl get deployments
```

```
docker build -t aivarssmaukstelis/flask-app:latest .
docker push aivarssmaukstelis/flask-app:latest
```

```shell
kubectl apply -f flask-deployment.yaml 
kubectl get pods -w
```
container created
container running 70seconds
container ready   100 seconds

```shell
kubectl logs flask-app-699f6d5fc9-bkpnv
```
```
  10.244.0.1 - - [16/Apr/2026 14:41:57] "GET /health HTTP/1.1" 500 -
  10.244.0.1 - - [16/Apr/2026 14:42:07] "GET /health HTTP/1.1" 500 -
  10.244.0.1 - - [16/Apr/2026 14:42:17] "GET /health HTTP/1.1" 500 -
  10.244.0.1 - - [16/Apr/2026 14:42:27] "GET /health HTTP/1.1" 200 -
```
