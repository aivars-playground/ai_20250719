```shell
kind create cluster --config kind-cluster-c1.yaml
kind create cluster --config kind-cluster-c2.yaml
kind get clusters
kubectl cluster-info --context kind-c1
kubectl cluster-info --context kind-c2
kind delete cluster --name c1
kind delete cluster --name c2
```

```shell
kind create cluster --config kind.yaml
kind get clusters
kubectl cluster-info --context kind-kind
#kind delete cluster --name kind
```

```shell
kubectl apply -f deployment.yaml
```

```shell
kubectl apply -f service.yaml
```

```shell
kubectl get deployments
kubectl get pods
kubectl get services
```
```text
NAME     READY   UP-TO-DATE   AVAILABLE   AGE
my-app   2/2     2            2           6m34s

NAME             TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)        AGE
kubernetes       ClusterIP      10.96.0.1      <none>        443/TCP        15m
my-app-service   LoadBalancer   10.96.194.65   <pending>     80:32178/TCP   2m37s
```

* forever pending!!!! ok on AWS, need a load balancer on bare metal!!!!!!
* https://metallb.universe.tf/installation/
```shell
kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/v0.15.3/config/manifests/metallb-native.yaml
```
```shell
kubectl get pods -n metallb-system
```
```text
NAME                          READY   STATUS    RESTARTS   AGE
controller-66bdd896c6-7llzj   1/1     Running   0          3m4s
speaker-6lpc2                 1/1     Running   0          3m4s
```

```shell
kubectl apply -f ip-pool.yaml
kubectl apply -f l2-advertisement.yaml
```

```shell
kubectl get services
```
```text
NAME             TYPE           CLUSTER-IP     EXTERNAL-IP      PORT(S)        AGE
kubernetes       ClusterIP      10.96.0.1      <none>           443/TCP        48m
my-app-service   LoadBalancer   10.96.194.65   172.18.255.200   80:32178/TCP   35m
```

```shell
kubectl describe service my-app-service
```
by default,Session Affinity is none, random allocated pods
```text
Session Affinity:         None
```



* add affinity 
```shell
kubectl apply -f service-with-session-affinity.yaml
```


```shell
kubectl describe service my-app-service
```
by default,Session Affinity is set to ClientIP, sticky session tied to ip for given time
```text
Session Affinity:         ClientIP
```
