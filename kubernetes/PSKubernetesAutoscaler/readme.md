kind delete clusters --all
```shell
kind create cluster --config=kind.yaml
kubectl cluster-info
kubectl get deployments
```
https://kubernetes-sigs.github.io/metrics-server/

```shell
#metrics server is not installed by default, and Horizontal Autoscaler is using it
kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml

# Patch to enable insecure TLS (required for most local/test clusters)
kubectl patch deployment metrics-server -n kube-system --type='json' -p='[{"op": "add", "path": "/spec/template/spec/containers/0/args/-", "value": "--kubelet-insecure-tls"}]'

# Verify installation
kubectl get pods -n kube-system -l k8s-app=metrics-server
kubectl top nodes
```

```shell
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl apply -f hpa.yaml
```

```shell
kubectl run --rm -i --tty load-generator --image=busybox -- /bin/sh
```
```shell
while true; do wget -q -O- http://nginx-service.default.svc.cluster.local;done
```

```shell
kubectl get hpa -w
```

```shell
kubectl get pods -w
```
