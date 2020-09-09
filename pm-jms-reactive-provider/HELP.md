# pm-jms-reactive-provider

```
$ curl -v -i http://127.0.0.1:8081/api/restful
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/restful HTTP/1.1
> Host: 127.0.0.1:8081
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
< Date: Tue, 25 Aug 2020 07:27:54 GMT
Date: Tue, 25 Aug 2020 07:27:54 GMT

< 
{
  "_links" : {
    "items" : {
      "href" : "http://127.0.0.1:8081/api/restful/items"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/restful/customers"
    },
    "invoices" : {
      "href" : "http://127.0.0.1:8081/api/restful/invoices"
    },
    "profile" : {
      "href" : "http://127.0.0.1:8081/api/restful/profile"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## Some further examples:

```
$ curl -v -i http://127.0.0.1:8081/api/restful
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/restful HTTP/1.1
> Host: 127.0.0.1:8081
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
< Date: Wed, 09 Sep 2020 13:12:31 GMT
Date: Wed, 09 Sep 2020 13:12:31 GMT

< 
{
  "_links" : {
    "items" : {
      "href" : "http://127.0.0.1:8081/api/restful/items"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/restful/customers"
    },
    "invoices" : {
      "href" : "http://127.0.0.1:8081/api/restful/invoices"
    },
    "profile" : {
      "href" : "http://127.0.0.1:8081/api/restful/profile"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## customer search:
```
$ curl -v -i http://127.0.0.1:8081/api/restful/customers/search
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/restful/customers/search HTTP/1.1
> Host: 127.0.0.1:8081
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
< Date: Wed, 09 Sep 2020 13:14:08 GMT
Date: Wed, 09 Sep 2020 13:14:08 GMT

< 
{
  "_links" : {
    "findAllBySurname" : {
      "href" : "http://127.0.0.1:8081/api/restful/customers/search/findAllBySurname{?surname}",
      "templated" : true
    },
    "findAllByName" : {
      "href" : "http://127.0.0.1:8081/api/restful/customers/search/findAllByName{?name}",
      "templated" : true
    },
    "findByEmail" : {
      "href" : "http://127.0.0.1:8081/api/restful/customers/search/findByEmail{?email}",
      "templated" : true
    },
    "self" : {
      "href" : "http://127.0.0.1:8081/api/restful/customers/search"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## invoice search:
```
$ curl -v -i http://127.0.0.1:8081/api/restful/invoices/search
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/restful/invoices/search HTTP/1.1
> Host: 127.0.0.1:8081
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
< Date: Wed, 09 Sep 2020 13:16:36 GMT
Date: Wed, 09 Sep 2020 13:16:36 GMT

< 
{
  "_links" : {
    "findByCode" : {
      "href" : "http://127.0.0.1:8081/api/restful/invoices/search/findByCode{?code}",
      "templated" : true
    },
    "self" : {
      "href" : "http://127.0.0.1:8081/api/restful/invoices/search"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## item search:
```
$ curl -v -i http://127.0.0.1:8081/api/restful/items/search
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/restful/items/search HTTP/1.1
> Host: 127.0.0.1:8081
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
< Date: Wed, 09 Sep 2020 13:17:33 GMT
Date: Wed, 09 Sep 2020 13:17:33 GMT

< 
{
  "_links" : {
    "findByCode" : {
      "href" : "http://127.0.0.1:8081/api/restful/items/search/findByCode{?code}",
      "templated" : true
    },
    "findAllByName" : {
      "href" : "http://127.0.0.1:8081/api/restful/items/search/findAllByName{?name}",
      "templated" : true
    },
    "self" : {
      "href" : "http://127.0.0.1:8081/api/restful/items/search"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```
