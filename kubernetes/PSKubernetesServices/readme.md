something is already running
```shell
kubectl get services
```
a few namespaces
```shell
kubectl get namespaces
```

a few services
--------------
* ClusterIp default service type for services in one cluster
* NodeIp exposed by static node address (for debugging) used when there is no external lb
* LoadBalancer external access by intternal or external (provider) load balancer


deployment directly form immage and command line
```shell
kubectl create deployment my-nginx --image=nginx:latest --port=80

kubectl expose deployment my-nginx \
  --type=NodePort\
  --port=80\
  --target-port=80\
  --name=my-nginx-service
```

how to access service
---------------------
* ip address
```shell
kubectl get services
> my-nginx-service   NodePort    10.96.176.146   <none>        80:31474/TCP   15m
```
note random port 31474
```shell
kubectl get nodes -o wide
kind-control-plane   Ready    control-plane   4h42m   v1.35.0   172.18.0.2  <--- internal ip
```

does not work on MacOs...
```shell
curl 172.18.0.2:31474
```

FIX for macos
```shell
kind create cluster --config kind.yaml
```
```kind.yaml
apiVersion: kind.x-k8s.io/v1alpha4
kind: Cluster
name: dev
nodes:
  - role: control-plane
    extraPortMappings:
      - containerPort: 30000
        hostPort: 30000
        listenAddress: "0.0.0.0"
        protocol: TCP
  - role: worker
```
```shell
kubectl create deployment my-nginx --image=nginx:latest --port=80
```
```text
apiVersion: v1
kind: Service
metadata:
  name: my-nginx-service
spec:
  type: NodePort
  selector:
    app: my-nginx
  ports:
  - protocol: TCP
    port: 80
    targetPort: 80
    nodePort: 30000
```
```shell
curl localhost:30000
```
=======================
```shell
kubectl apply -f backend-deployment.yaml
kubectl apply -f frontend-deployment.yaml
```

```shell
kubectl logs -f deployment/frontend
```
```shell
kubectl apply -f backend-service.yaml
```
