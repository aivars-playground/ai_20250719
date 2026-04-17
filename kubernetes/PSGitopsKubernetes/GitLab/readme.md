* add  `127.0.0.1 gitlab.example`.com to `/etc/hosts`
* generate self-signed certificate with SANS (for flux/git!!!) for https://gitlab.example.com

```shell
openssl req -x509 -newkey rsa:4096 -sha256 -days 10 -nodes \
    -keyout ./.data/gitlab/config/ssl/gitlab.example.com.key \
    -out    ./.data/gitlab/config/ssl/gitlab.example.com.crt \
    -subj "/CN=gitlab.example.com" \
    -addext "subjectAltName=DNS:gitlab.example.com,IP:127.0.0.1"
```

start docker (takes up to 5 minutes)
```shell
docker compose up -d
```
access https://gitlab.example.com
