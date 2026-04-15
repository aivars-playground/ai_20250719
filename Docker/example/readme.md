NOTE: on MacOs use
------------------
```shell
kubectl port-forward simple-app 5000:5000
```

based on
--------
Pods in Kubernetes
by David Clinton

build an image
--------------
```
docker build -t aivarssmaukstelis/simple-app:latest .
docker push aivarssmaukstelis/simple-app:latest
```

prepare cluster
---------------
```
kind create cluster
kubectl cluster-info --context kind-kind 
```

run syntax check (dry run)
----------------
client side check
```
kubectl apply -f pod.yaml --dry-run=client
```

run syntax check on the actual server
```
kubectl apply -f pod.yaml --dry-run=server
```

apply - deploy
--------------
deploy pod with a single container
```shell
kubectl apply -f pod.yaml
kubectl get pods
```

deploy a pod with multple conatiners (main app and logging sidecar)
```shell
kubectl apply -f multi-container-pod.yaml
kubectl get pods
```

get information on a pod
```
kubectl describe pod multi-container-app
```

get logs
-------------
```shell
kubectl logs multi-container-app
```
```shell
kubectl logs multi-container-app -c main-app
```
```shell
kubectl logs multi-container-app -c logger-app
```

***** tagged as v1 is the first simple app version ***
add some changes to the app
```
docker build -t aivarssmaukstelis/simple-app:latest .
docker push aivarssmaukstelis/simple-app:latest
```

```shell
kubectl get pods
kubectl delete pod simple-app
kubectl apply -f pod.yaml
kubectl get pods
kubectl port-forward simple-app 5000:5000 &
curl localhost:5000
```

***** tagged as v2***
