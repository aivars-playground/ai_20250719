setup
=================
add to /etc/hosts
```
127.0.0.1 gitlab.example.com
```

generate data directory
```shell
mkdir ./.data
mkdir ./.data/gitlab
mkdir ./.data/gitlab/{config,config/ssl,data,logs}
```

generate self-signed certificate with SAN
```shell
openssl req -x509 -newkey rsa:4096 -sha256 -days 10 -nodes \
    -keyout ./.data/gitlab/config/ssl/gitlab.example.com.key \
    -out    ./.data/gitlab/config/ssl/gitlab.example.com.crt \
    -subj "/CN=gitlab.example.com" \
    -addext "subjectAltName=DNS:gitlab.example.com,IP:127.0.0.1"
```

start gitlab (takes up to 5 minutes)
```shell
docker compose up -d
```
