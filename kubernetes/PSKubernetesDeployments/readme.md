```shell
kind create cluster --config=kind.yaml
kubectl cluster-info
kubectl apply -f my-app-deployment.yaml
kubectl get deployments
```

```shell
kubectl get pods       
> NAME                      READY   STATUS    RESTARTS   AGE
> my-app-6d877fcb5d-dtmdd   1/1     Running   0          5m21s
> my-app-6d877fcb5d-nxxwk   1/1     Running   0          5m17s
> my-app-6d877fcb5d-tkv4x   1/1     Running   0          5m19s
```
```shell
kubectl port-forward pod/my-app-6d877fcb5d-dtmdd 8080:80
```
```shell
curl localhost:8080
```

run command in pod
```shell
kubectl exec -it my-app-6d877fcb5d-dtmdd -- printenv APP_ENV
```

describe pod
```shell
kubectl describe pod/my-app-6d877fcb5d-dtmdd
```

========================================================
