
# Example of use

## How to built:

```
$ sudo docker build --tag mongodb_image .
$ sudo docker run -d --name mongodbstore -e MONGO_INITDB_ROOT_USERNAME=dbadmin -e MONGO_INITDB_ROOT_PASSWORD=password -p 27017:27017 mongodb_image
$ sudo docker images
$ sudo docker ps --all
```

## How to verify:
```
$ curl -v -i http://127.0.0.1:27017
* Rebuilt URL to: http://127.0.0.1:27017/
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 27017 (#0)
> GET / HTTP/1.1
> Host: 127.0.0.1:27017
> User-Agent: curl/7.58.0
> Accept: */*
> 
* HTTP 1.0, assume close after body
< HTTP/1.0 200 OK
HTTP/1.0 200 OK
< Connection: close
Connection: close
< Content-Type: text/plain
Content-Type: text/plain
< Content-Length: 85
Content-Length: 85

< 
It looks like you are trying to access MongoDB over HTTP on the native driver port.
* Closing connection 0
```

## How to stop:
```
$ sudo docker stop your_container_id
$ sudo docker ps --all
```
