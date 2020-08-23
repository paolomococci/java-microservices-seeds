# pm-jms-restful-provider

## example of use
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
< HTTP/1.1 200 
HTTP/1.1 200 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sun, 23 Aug 2020 15:28:55 GMT
Date: Sun, 23 Aug 2020 15:28:55 GMT

< 
{
  "_links" : {
    "invoices" : {
      "href" : "http://127.0.0.1:8080/invoices{?page,size,sort}",
      "templated" : true
    },
    "customers" : {
      "href" : "http://127.0.0.1:8080/customers{?page,size,sort}",
      "templated" : true
    },
    "items" : {
      "href" : "http://127.0.0.1:8080/items{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://127.0.0.1:8080/profile"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```
