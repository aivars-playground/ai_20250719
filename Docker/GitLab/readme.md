setup
=================
add to /etc/hosts
```
127.0.0.1 gitlab.example.com
```

login
====
http://gitlab.example.com/
root/your_password

token
=====
get token from:
```
http://gitlab.example.com/admin/runners
```
```bash
docker exec -it gitlab-runner gitlab-runner register
```
choose shell script

add to gitlab runner toml config
extra_hosts = ["gitlab.example.com:<ip_of_server>"]







