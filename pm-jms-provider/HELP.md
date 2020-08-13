# pm-jms-provider

## example of use with the old root path:

```
$ curl -v -i http://127.0.0.1:8080
* Rebuilt URL to: http://127.0.0.1:8080/
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> GET / HTTP/1.1
> Host: 127.0.0.1:8080
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 404 
HTTP/1.1 404 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Content-Type: application/json
Content-Type: application/json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Thu, 13 Aug 2020 05:37:21 GMT
Date: Thu, 13 Aug 2020 05:37:21 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
{"timestamp":"2020-08-13T05:37:21.209+00:00","status":404,"error":"Not Found","message":"No message available","path":"/"}
```

## now use the new paths:

```
$ curl -v -i http://127.0.0.1:8080/api/restful/customers
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> GET /api/restful/customers HTTP/1.1
> Host: 127.0.0.1:8080
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 501 
HTTP/1.1 501 
< Content-Length: 0
Content-Length: 0
< Date: Thu, 13 Aug 2020 05:40:53 GMT
Date: Thu, 13 Aug 2020 05:40:53 GMT
< Connection: close
Connection: close

< 
* Closing connection 0
$ curl -v -i http://127.0.0.1:8080/api/restful/invoices
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> GET /api/restful/invoices HTTP/1.1
> Host: 127.0.0.1:8080
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 501 
HTTP/1.1 501 
< Content-Length: 0
Content-Length: 0
< Date: Thu, 13 Aug 2020 05:41:18 GMT
Date: Thu, 13 Aug 2020 05:41:18 GMT
< Connection: close
Connection: close

< 
* Closing connection 0
$ curl -v -i http://127.0.0.1:8080/api/restful/items
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> GET /api/restful/items HTTP/1.1
> Host: 127.0.0.1:8080
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 501 
HTTP/1.1 501 
< Content-Length: 0
Content-Length: 0
< Date: Thu, 13 Aug 2020 05:42:09 GMT
Date: Thu, 13 Aug 2020 05:42:09 GMT
< Connection: close
Connection: close

< 
* Closing connection 0
```
