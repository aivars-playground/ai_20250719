kind delete clusters --all
```shell
kind create cluster --config=kind.yaml
kubectl cluster-info
```

create initial syntactically correct (but includes some non-user modifiable fields!!!)
```shell
kubectl create deployment nginx --image=nginx \
    --dry-run=client -o yaml > nginx-deployment.yaml
```

```shell
kubectl apply -f nginx-deployment.yaml
```

* show difference between given file and cluster
```shell
kubectl diff -f nginx-deployment_modified.yaml
```

```shell
kubectl get deployment nginx -o yaml | grep generation
```
