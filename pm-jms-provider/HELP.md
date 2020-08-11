# pm-jms-provider

## example of use:

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
< Date: Tue, 11 Aug 2020 18:12:48 GMT
Date: Tue, 11 Aug 2020 18:12:48 GMT

< 
{
  "_links" : {
    "invoices" : {
      "href" : "http://127.0.0.1:8080/invoices"
    },
    "items" : {
      "href" : "http://127.0.0.1:8080/items"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8080/customers"
    },
    "profile" : {
      "href" : "http://127.0.0.1:8080/profile"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```
