# pm-jms

## some tests from the command line

### for example:
```
$ curl -v -i -H "Content-Type:application/json" -d "{\"name\":\"seedTwo\"}" http://127.0.0.1:9090/api/seeds
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> POST /api/seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Content-Type:application/json
> Content-Length: 18
> 
* upload completely sent off: 18 out of 18 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Fri, 11 Oct 2019 07:20:56 GMT
Date: Fri, 11 Oct 2019 07:20:56 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
{"id":2,"name":"seedTwo","seedDoubleValue":0.0,"created":null}

$ curl -v -i http://127.0.0.1:9090/api/seeds
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> GET /api/seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Fri, 11 Oct 2019 07:21:23 GMT
Date: Fri, 11 Oct 2019 07:21:23 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
[{"id":1,"name":"seedOne","seedDoubleValue":0.0,"created":null},{"id":2,"name":"seedTwo","seedDoubleValue":0.0,"created":null}]

$ curl -v -i -X PUT -H "Content-Type:application/json" -d "{\"name\":\"someOne\"}" http://127.0.0.1:9090/api/seeds/2
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> PUT /api/seeds/2 HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Content-Type:application/json
> Content-Length: 18
> 
* upload completely sent off: 18 out of 18 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Fri, 11 Oct 2019 07:25:21 GMT
Date: Fri, 11 Oct 2019 07:25:21 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
{"id":2,"name":"someOne","seedDoubleValue":0.0,"created":null}

$ curl -v -i -X PUT -H "Content-Type:application/json" -d "{\"seedDoubleValue\":\"0.15\"}" http://127.0.0.1:9090/api/seeds/2
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> PUT /api/seeds/2 HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Content-Type:application/json
> Content-Length: 26
> 
* upload completely sent off: 26 out of 26 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Fri, 11 Oct 2019 07:27:13 GMT
Date: Fri, 11 Oct 2019 07:27:13 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
{"id":2,"name":null,"seedDoubleValue":0.15,"created":null}

$ curl -v -i -X DELETE http://127.0.0.1:9090/api/seeds/2
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> DELETE /api/seeds/2 HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 204 
HTTP/1.1 204 
< Date: Fri, 11 Oct 2019 07:28:27 GMT
Date: Fri, 11 Oct 2019 07:28:27 GMT

< 
* Connection #0 to host 127.0.0.1 left intact

$ curl -v -i http://127.0.0.1:9090/api/seeds
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> GET /api/seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Fri, 11 Oct 2019 07:29:02 GMT
Date: Fri, 11 Oct 2019 07:29:02 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
[{"id":1,"name":"seedOne","seedDoubleValue":0.0,"created":null}]
```

### some example:

```
$ curl -v -i -H "Content-Type:application/json" -d "{\"name\":\"some seed\"}" http://127.0.0.1:9090/api/seeds
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> POST /api/seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Content-Type:application/json
> Content-Length: 20
> 
* upload completely sent off: 20 out of 20 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Fri, 11 Oct 2019 07:35:16 GMT
Date: Fri, 11 Oct 2019 07:35:16 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
{"id":1,"name":"some seed","seedDoubleValue":0.0,"created":null}

$ curl -v -i -X DELETE http://127.0.0.1:9090/api/seeds/1
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> DELETE /api/seeds/1 HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 204 
HTTP/1.1 204 
< Date: Fri, 11 Oct 2019 07:35:47 GMT
Date: Fri, 11 Oct 2019 07:35:47 GMT

< 
* Connection #0 to host 127.0.0.1 left intact

$ curl -v -i http://127.0.0.1:9090/api/seeds/1
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> GET /api/seeds/1 HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 404 
HTTP/1.1 404 
< Content-Length: 0
Content-Length: 0
< Date: Fri, 11 Oct 2019 07:35:58 GMT
Date: Fri, 11 Oct 2019 07:35:58 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
```
