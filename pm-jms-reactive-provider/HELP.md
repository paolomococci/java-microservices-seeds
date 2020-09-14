# pm-jms-reactive-provider

## if I try to connect to a path not expressly defined I get:
```
$ curl -v -i http://127.0.0.1:8081
* Rebuilt URL to: http://127.0.0.1:8081/
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET / HTTP/1.1
> Host: 127.0.0.1:8081
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
< Date: Sun, 13 Sep 2020 14:37:23 GMT
Date: Sun, 13 Sep 2020 14:37:23 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
{"timestamp":"2020-09-13T14:37:23.002+00:00","status":404,"error":"Not Found","message":"No message available","path":"/"}
```

## as expected, if I connect to the path defined in application.properties, I get:
```
$ curl -v -i http://127.0.0.1:8081/api/reactive
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/reactive HTTP/1.1
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
< Date: Sun, 13 Sep 2020 14:41:34 GMT
Date: Sun, 13 Sep 2020 14:41:34 GMT

< 
{
  "_links" : {
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers"
    },
    "items" : {
      "href" : "http://127.0.0.1:8081/api/reactive/items"
    },
    "invoices" : {
      "href" : "http://127.0.0.1:8081/api/reactive/invoices"
    },
    "profile" : {
      "href" : "http://127.0.0.1:8081/api/reactive/profile"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## if I ask for the list of customers already registered in the system:
```
$ curl -v -i http://127.0.0.1:8081/api/reactive/customers
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/reactive/customers HTTP/1.1
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
< Date: Sun, 13 Sep 2020 15:01:13 GMT
Date: Sun, 13 Sep 2020 15:01:13 GMT

< 
{
  "_embedded" : {
    "customers" : [ {
      "name" : "John",
      "surname" : "Jump",
      "email" : "johnjump@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers/5f43558b371ce96b10561c0f"
        },
        "customers" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers"
        }
      }
    }, {
      "name" : "James",
      "surname" : "Do",
      "email" : "jamesdo@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers/5f4356da7575a25fbb466a95"
        },
        "customers" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers"
        }
      }
    } ]
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## now I make a request by inserting a specific identifier in the path:
```
$ curl -v -i http://127.0.0.1:8081/api/reactive/customers/5f43558b371ce96b10561c0f
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/reactive/customers/5f43558b371ce96b10561c0f HTTP/1.1
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
< Date: Sun, 13 Sep 2020 15:07:21 GMT
Date: Sun, 13 Sep 2020 15:07:21 GMT

< 
{
  "name" : "John",
  "surname" : "Jump",
  "email" : "johnjump@example.local",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers/5f43558b371ce96b10561c0f"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## now I post more customers in the system:
```
$ curl -v -i -H "Content-Type:application/json" -d '{"name":"Liz","surname":"Do","email":"lizdo@example.local"}' http://127.0.0.1:8081/api/reactive/customers
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> POST /api/reactive/customers HTTP/1.1
> Host: 127.0.0.1:8081
> User-Agent: curl/7.58.0
> Accept: */*
> Content-Type:application/json
> Content-Length: 59
> 
* upload completely sent off: 59 out of 59 bytes
< HTTP/1.1 201 
HTTP/1.1 201 
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
< Date: Sun, 13 Sep 2020 15:23:13 GMT
Date: Sun, 13 Sep 2020 15:23:13 GMT

< 
{
  "name" : "Liz",
  "surname" : "Do",
  "email" : "lizdo@example.local",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers/5f5e3960bf16687d57b5d877"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
$ curl -v -i -H "Content-Type:application/json" -d '{"name":"Sheila","surname":"Jump","email":"sheilajump@example.local"}' http://127.0.0.1:8081/api/reactive/customers
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> POST /api/reactive/customers HTTP/1.1
> Host: 127.0.0.1:8081
> User-Agent: curl/7.58.0
> Accept: */*
> Content-Type:application/json
> Content-Length: 69
> 
* upload completely sent off: 69 out of 69 bytes
< HTTP/1.1 201 
HTTP/1.1 201 
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
< Date: Sun, 13 Sep 2020 15:25:10 GMT
Date: Sun, 13 Sep 2020 15:25:10 GMT

< 
{
  "name" : "Sheila",
  "surname" : "Jump",
  "email" : "sheilajump@example.local",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers/5f5e39d5bf16687d57b5d878"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## of one of these I change all the fields:
```
$ curl -v -i -H "Content-Type:application/json" -X PUT  -d '{"name":"Sharon","surname":"Bow","email":"sharonbow@example.local"}' http://127.0.0.1:8081/api/reactive/customers/5f5e39d5bf16687d57b5d878
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> PUT /api/reactive/customers/5f5e39d5bf16687d57b5d878 HTTP/1.1
> Host: 127.0.0.1:8081
> User-Agent: curl/7.58.0
> Accept: */*
> Content-Type:application/json
> Content-Length: 67
> 
* upload completely sent off: 67 out of 67 bytes
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
< Date: Sun, 13 Sep 2020 15:31:11 GMT
Date: Sun, 13 Sep 2020 15:31:11 GMT

< 
{
  "name" : "Sharon",
  "surname" : "Bow",
  "email" : "sharonbow@example.local",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers/5f5e39d5bf16687d57b5d878"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## now I change only e-mail field:
```
$ curl -v -i -H "Content-Type:application/json" -X PATCH  -d '{"email":"sharon.bow@example.local"}' http://127.0.0.1:8081/api/reactive/customers/5f5e39d5bf16687d57b5d878
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> PATCH /api/reactive/customers/5f5e39d5bf16687d57b5d878 HTTP/1.1
> Host: 127.0.0.1:8081
> User-Agent: curl/7.58.0
> Accept: */*
> Content-Type:application/json
> Content-Length: 36
> 
* upload completely sent off: 36 out of 36 bytes
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
< Date: Sun, 13 Sep 2020 15:32:25 GMT
Date: Sun, 13 Sep 2020 15:32:25 GMT

< 
{
  "name" : "Sharon",
  "surname" : "Bow",
  "email" : "sharon.bow@example.local",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers/5f5e39d5bf16687d57b5d878"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## then I delete that same record by passing its identifier:
```
$ curl -v -i -X DELETE http://127.0.0.1:8081/api/reactive/customers/5f5e39d5bf16687d57b5d878
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> DELETE /api/reactive/customers/5f5e39d5bf16687d57b5d878 HTTP/1.1
> Host: 127.0.0.1:8081
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 204 
HTTP/1.1 204 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Date: Sun, 13 Sep 2020 15:34:28 GMT
Date: Sun, 13 Sep 2020 15:34:28 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
```

## which is no longer present in the system:
```
$ curl -v -i http://127.0.0.1:8081/api/reactive/customers/5f5e39d5bf16687d57b5d878
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/reactive/customers/5f5e39d5bf16687d57b5d878 HTTP/1.1
> Host: 127.0.0.1:8081
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
< Content-Length: 0
Content-Length: 0
< Date: Sun, 13 Sep 2020 15:36:58 GMT
Date: Sun, 13 Sep 2020 15:36:58 GMT

< 
* Connection #0 to host 127.0.0.1 left intact
```

## I search by e-mail:
```
$ curl -v -i http://127.0.0.1:8081/api/reactive/customers/email/johnjump@example.local
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/reactive/customers/email/johnjump@example.local HTTP/1.1
> Host: 127.0.0.1:8081
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Disposition: inline;filename=f.txt
Content-Disposition: inline;filename=f.txt
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sun, 13 Sep 2020 15:57:18 GMT
Date: Sun, 13 Sep 2020 15:57:18 GMT

< 
{
  "name" : "John",
  "surname" : "Jump",
  "email" : "johnjump@example.local",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers/5f43558b371ce96b10561c0f"
    },
    "customers" : {
      "href" : "http://127.0.0.1:8081/api/reactive/customers"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## I search by name:
```
$ curl -v -i http://127.0.0.1:8081/api/reactive/customers/name/Liz
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/reactive/customers/name/Liz HTTP/1.1
> Host: 127.0.0.1:8081
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sun, 13 Sep 2020 16:14:17 GMT
Date: Sun, 13 Sep 2020 16:14:17 GMT

< 
{
  "_embedded" : {
    "customers" : [ {
      "name" : "Liz",
      "surname" : "Do",
      "email" : "lizdo@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers/5f5e3960bf16687d57b5d877"
        },
        "customers" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers"
        }
      }
    } ]
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## I search by surname:

```
$ curl -v -i http://127.0.0.1:8081/api/reactive/customers/surname/Do
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8081 (#0)
> GET /api/reactive/customers/surname/Do HTTP/1.1
> Host: 127.0.0.1:8081
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 200 
HTTP/1.1 200 
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sun, 13 Sep 2020 16:17:35 GMT
Date: Sun, 13 Sep 2020 16:17:35 GMT

< 
{
  "_embedded" : {
    "customers" : [ {
      "name" : "James",
      "surname" : "Do",
      "email" : "jamesdo@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers/5f4356da7575a25fbb466a95"
        },
        "customers" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers"
        }
      }
    }, {
      "name" : "Liz",
      "surname" : "Do",
      "email" : "lizdo@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers/5f5e3960bf16687d57b5d877"
        },
        "customers" : {
          "href" : "http://127.0.0.1:8081/api/reactive/customers"
        }
      }
    } ]
  }
* Connection #0 to host 127.0.0.1 left intact
}
```
