# pm-jms-admin

## please note
If "spring-boot-starter-data-rest" is left in the dependencies it will interfere with Vaadin's starter context path.
By replying in the following way:
```
$ curl -v -i http://127.0.0.1:8091
* Rebuilt URL to: http://127.0.0.1:8091/
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8091 (#0)
> GET / HTTP/1.1
> Host: 127.0.0.1:8091
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
< Date: Sun, 30 Aug 2020 05:34:36 GMT
Date: Sun, 30 Aug 2020 05:34:36 GMT

< 
{
  "_links" : {
    "profile" : {
      "href" : "http://127.0.0.1:8091/profile"
    }
  }
* Connection #0 to host 127.0.0.1 left intact
}
```
Instead, in the way one would expect:
```
$ curl -v -i http://127.0.0.1:8091
* Rebuilt URL to: http://127.0.0.1:8091/
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to 127.0.0.1 (127.0.0.1) port 8091 (#0)
> GET / HTTP/1.1
> Host: 127.0.0.1:8091
> User-Agent: curl/7.58.0
> Accept: */*
> 
< HTTP/1.1 200 
HTTP/1.1 200 
< Set-Cookie: JSESSIONID=7E499C239E22F55506B1E34E77A006D3; Path=/; HttpOnly
Set-Cookie: JSESSIONID=7E499C239E22F55506B1E34E77A006D3; Path=/; HttpOnly
< Content-Length: 1373
Content-Length: 1373
< Date: Sun, 30 Aug 2020 05:37:13 GMT
Date: Sun, 30 Aug 2020 05:37:13 GMT

< 
<html><head>  <style>    html {      background: #fff;      color: #444;      font: 400 1em/1.5 "Helvetica Neue", Roboto, "Segoe UI", sans-serif;      padding: 2em;    }    body {      margin: 2em auto;      width: 28em;      max-width: 100%;    }    h1 {      line-height: 1.1;      margin: 2em 0 1em;      color: #000;      font-weight: 400;    }    p {      margin: 0.5em 0 0;    }    a {      text-decoration: none;      color: #007df0;    }    sub {      display: block;      margin-top: 2.5em;      text-align: center;      border-top: 1px solid #eee;      padding-top: 2em;    }    sub,    small {      color: #999;    }  </style></head><body style="width:34em;"><h1>This browser requires transpilation to ES5.</h1><p>To test your app with this browser, enable transpilation in development mode.</p><p>Transpilation can be enabled by setting the <code>vaadin.devmode.transpile=true</code> property for the deployment configuration using an application or a system property.<p><p>Note that transpilation is always enabled for the <code>build-frontend</code> Maven goal, which is also used when creating a production build of the application.</p><p><sub><a onclick="document.cookie='vaadinforceload=1';window.location.reload();return false;" href="#">Continue anyway<br>(eg. if you've setup ES5 transpilation in a custom webpack configuration)</sub></p></body>
* Connection #0 to host 127.0.0.1 left intact
</html>
```
So the following line must not be present in file build.gradle:
```
    ...
	implementation 'org.springframework.boot:spring-boot-starter-data-rest'
    ...
```
If, on the other hand, the aforementioned dependency is essential, it will be necessary to change the basePath of RESTful api in file application.properties as follows:
```
...
# Eventual RESTful api path
spring.data.rest.basePath=/api
...
```
