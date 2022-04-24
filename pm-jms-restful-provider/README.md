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

## insertion of some records
```
$ curl -v -i -H "Content-Type:application/json" -d '{"name":"John","surname":"Jump","email":"johnjump@example.local"}' http://127.0.0.1:8080/customers
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> POST /customers HTTP/1.1
> Host: 127.0.0.1:8080
> User-Agent: curl/7.58.0
> Accept: */*
> Content-Type:application/json
> Content-Length: 65
> 
* upload completely sent off: 65 out of 65 bytes
< HTTP/1.1 201 
HTTP/1.1 201 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Location: http://127.0.0.1:8080/customers/5f43558b371ce96b10561c0f
Location: http://127.0.0.1:8080/customers/5f43558b371ce96b10561c0f
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Mon, 24 Aug 2020 05:52:12 GMT
Date: Mon, 24 Aug 2020 05:52:12 GMT

< 
{
  "name" : "John",
  "surname" : "Jump",
  "email" : "johnjump@example.local",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8080/customers/5f43558b371ce96b10561c0f"
    },
    "customer" : {
      "href" : "http://127.0.0.1:8080/customers/5f43558b371ce96b10561c0f"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
$ curl -v -i -H "Content-Type:application/json" -d '{"name":"James","surname":"Do","email":"jamesdo@example.local"}' http://127.0.0.1:8080/customers
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> POST /customers HTTP/1.1
> Host: 127.0.0.1:8080
> User-Agent: curl/7.58.0
> Accept: */*
> Content-Type:application/json
> Content-Length: 63
> 
* upload completely sent off: 63 out of 63 bytes
< HTTP/1.1 201 
HTTP/1.1 201 
< Vary: Origin
Vary: Origin
< Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
Vary: Access-Control-Request-Headers
< Location: http://127.0.0.1:8080/customers/5f4356da7575a25fbb466a95
Location: http://127.0.0.1:8080/customers/5f4356da7575a25fbb466a95
< Content-Type: application/hal+json
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Mon, 24 Aug 2020 05:57:46 GMT
Date: Mon, 24 Aug 2020 05:57:46 GMT

< 
{
  "name" : "James",
  "surname" : "Do",
  "email" : "jamesdo@example.local",
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8080/customers/5f4356da7575a25fbb466a95"
    },
    "customer" : {
      "href" : "http://127.0.0.1:8080/customers/5f4356da7575a25fbb466a95"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## now we see the newly inserted records
```
$ curl -v -i http://127.0.0.1:8080/customers
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> GET /customers HTTP/1.1
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
< Date: Mon, 24 Aug 2020 06:00:49 GMT
Date: Mon, 24 Aug 2020 06:00:49 GMT

< 
{
  "_embedded" : {
    "customers" : [ {
      "name" : "John",
      "surname" : "Jump",
      "email" : "johnjump@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8080/customers/5f43558b371ce96b10561c0f"
        },
        "customer" : {
          "href" : "http://127.0.0.1:8080/customers/5f43558b371ce96b10561c0f"
        }
      }
    }, {
      "name" : "James",
      "surname" : "Do",
      "email" : "jamesdo@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8080/customers/5f4356da7575a25fbb466a95"
        },
        "customer" : {
          "href" : "http://127.0.0.1:8080/customers/5f4356da7575a25fbb466a95"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8080/customers"
    },
    "profile" : {
      "href" : "http://127.0.0.1:8080/profile/customers"
    },
    "search" : {
      "href" : "http://127.0.0.1:8080/customers/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 2,
    "totalPages" : 1,
    "number" : 0
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

# Some further examples:

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
< Date: Wed, 09 Sep 2020 12:57:45 GMT
Date: Wed, 09 Sep 2020 12:57:45 GMT

< 
{
  "_links" : {
    "items" : {
      "href" : "http://127.0.0.1:8080/items{?page,size,sort}",
      "templated" : true
    },
    "customers" : {
      "href" : "http://127.0.0.1:8080/customers{?page,size,sort}",
      "templated" : true
    },
    "invoices" : {
      "href" : "http://127.0.0.1:8080/invoices{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://127.0.0.1:8080/profile"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## we see in particular the interface of the document customer
```
Content-Type: application/hal+json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Wed, 09 Sep 2020 13:01:18 GMT
Date: Wed, 09 Sep 2020 13:01:18 GMT

< 
{
  "_embedded" : {
    "customers" : [ {
      "name" : "John",
      "surname" : "Jump",
      "email" : "johnjump@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8080/customers/5f43558b371ce96b10561c0f"
        },
        "customer" : {
          "href" : "http://127.0.0.1:8080/customers/5f43558b371ce96b10561c0f"
        }
      }
    }, {
      "name" : "James",
      "surname" : "Do",
      "email" : "jamesdo@example.local",
      "_links" : {
        "self" : {
          "href" : "http://127.0.0.1:8080/customers/5f4356da7575a25fbb466a95"
        },
        "customer" : {
          "href" : "http://127.0.0.1:8080/customers/5f4356da7575a25fbb466a95"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://127.0.0.1:8080/customers"
    },
    "profile" : {
      "href" : "http://127.0.0.1:8080/profile/customers"
    },
    "search" : {
      "href" : "http://127.0.0.1:8080/customers/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 2,
    "totalPages" : 1,
    "number" : 0
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## I'm interested in seeing:
```
"http://127.0.0.1:8080/customers/search"
```

## therefore:
```
$ curl -v -i http://127.0.0.1:8080/customers/search
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8080 (#0)
> GET /customers/search HTTP/1.1
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
< Date: Wed, 09 Sep 2020 13:03:45 GMT
Date: Wed, 09 Sep 2020 13:03:45 GMT

< 
{
  "_links" : {
    "findByName" : {
      "href" : "http://127.0.0.1:8080/customers/search/findByName{?name}",
      "templated" : true
    },
    "findBySurname" : {
      "href" : "http://127.0.0.1:8080/customers/search/findBySurname{?surname}",
      "templated" : true
    },
    "findByEmail" : {
      "href" : "http://127.0.0.1:8080/customers/search/findByEmail{?email}",
      "templated" : true
    },
    "self" : {
      "href" : "http://127.0.0.1:8080/customers/search"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```

## conclusions:
Here, I found: findByName, findBySurname e findByEmail.
To be used with the respective variables: name, surname e email.
So, in the administrative web application controllers I can, for example, build the following GET request:
```
http://127.0.0.1:8080/customers/search/findByEmail?email=email_to_search_for
```
Thus, I can use the returned entity.
