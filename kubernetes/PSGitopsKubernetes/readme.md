Flux v2
-------

```shell
kind delete clusters --all
kind create cluster --config=kind-my-flux-cluster.yaml
```

check is we can install flux
```shell
flux check --pre
```

```shell
set -a && source .env && set +a
bash -c 'echo "${GITLAB_HOSTNAME:? is not set}"'
bash -c 'echo "${GITLAB_USER:? is not set}"'
bash -c 'echo "${GITLAB_TOKEN:? is not set}"'
```

```shell
flux bootstrap gitlab \
  --hostname=$GITLAB_HOSTNAME \
  --repository=gitops-flux \
  --path=./clusters/kind \
  --branch=main \
  --owner=$GITLAB_USER \
  --token-auth \
  --personal
```
