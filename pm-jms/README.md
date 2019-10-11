# pm-jms

## some tests from the command line

```
$ curl -v -i http://127.0.0.1:9090
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> GET / HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> 
* Mark bundle as not supporting multiuse
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
< Date: Mon, 07 Oct 2019 21:05:19 GMT
Date: Mon, 07 Oct 2019 21:05:19 GMT

< 
{
  "_links" : {
    "seeds" : {
      "href" : "http://127.0.0.1:9090/seeds"
    },
    "profile" : {
      "href" : "http://127.0.0.1:9090/profile"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}

$ curl -v -i http://127.0.0.1:9090/seeds
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> GET /seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> 
* Mark bundle as not supporting multiuse
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
< Date: Mon, 07 Oct 2019 21:05:31 GMT
Date: Mon, 07 Oct 2019 21:05:31 GMT

< 
{
  "_embedded" : {
    "seeds" : [ ]
  },
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9090/seeds"
    },
    "profile" : {
      "href" : "http://127.0.0.1:9090/profile/seeds"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}

$ curl -v -i -H "Content-Type:application/json" -d "{\"name\":\"seedOne\"}" http://127.0.0.1:9090/seeds
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> POST /seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Content-Type:application/json
> Content-Length: 18
> 
* upload completely sent off: 18 out of 18 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 201 
HTTP/1.1 201 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Location: http://127.0.0.1:9090/seeds/1
Location: http://127.0.0.1:9090/seeds/1
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Mon, 07 Oct 2019 21:06:10 GMT
Date: Mon, 07 Oct 2019 21:06:10 GMT

< 
{
  "name" : "seedOne",
  "value" : 0.0,
  "created" : null,
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9090/seeds/1"
    },
    "seed" : {
      "href" : "http://127.0.0.1:9090/seeds/1"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## I try repository interface CORS configuration

### at the beginning without configuring anything:
```
$ curl 'http://127.0.0.1:9090/seeds' -v -i -H 'Origin: http://192.168.0.1'
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> GET /seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Origin: http://192.168.0.1
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
HTTP/1.1 200 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Access-Control-Allow-Origin: *
Access-Control-Allow-Origin: *
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Wed, 09 Oct 2019 20:01:46 GMT
Date: Wed, 09 Oct 2019 20:01:46 GMT

< 
{
  "_embedded" : {
    "seeds" : [ ]
  },
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9090/seeds"
    },
    "profile" : {
      "href" : "http://127.0.0.1:9090/profile/seeds"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```
### then I add to the interface SeedRepository:
```
@CrossOrigin(origins = "http://127.0.0.1")
```
### and i get:
```
$ curl 'http://127.0.0.1:9090/seeds' -v -i -H 'Origin: http://192.168.0.1'
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> GET /seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Origin: http://192.168.0.1
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 403 
HTTP/1.1 403 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Wed, 09 Oct 2019 20:02:59 GMT
Date: Wed, 09 Oct 2019 20:02:59 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
Invalid CORS request
```
### finally:
```
$ curl 'http://127.0.0.1:9090/seeds' -v -i -H 'Origin: http://127.0.0.1'
*   Trying 127.0.0.1:9090...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 9090 (#0)
> GET /seeds HTTP/1.1
> Host: 127.0.0.1:9090
> User-Agent: curl/7.65.3
> Accept: */*
> Origin: http://127.0.0.1
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
HTTP/1.1 200 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Access-Control-Allow-Origin: http://127.0.0.1
Access-Control-Allow-Origin: http://127.0.0.1
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Wed, 09 Oct 2019 20:03:34 GMT
Date: Wed, 09 Oct 2019 20:03:34 GMT

< 
{
  "_embedded" : {
    "seeds" : [ ]
  },
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:9090/seeds"
    },
    "profile" : {
      "href" : "http://127.0.0.1:9090/profile/seeds"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```
