## pm-jms-express-consumer

```
$ express --view=pug pm-jms-express-consumer

   create : pm-jms-express-consumer/
   create : pm-jms-express-consumer/public/
   create : pm-jms-express-consumer/public/javascripts/
   create : pm-jms-express-consumer/public/images/
   create : pm-jms-express-consumer/public/stylesheets/
   create : pm-jms-express-consumer/public/stylesheets/style.css
   create : pm-jms-express-consumer/routes/
   create : pm-jms-express-consumer/routes/index.js
   create : pm-jms-express-consumer/routes/users.js
   create : pm-jms-express-consumer/views/
   create : pm-jms-express-consumer/views/error.pug
   create : pm-jms-express-consumer/views/index.pug
   create : pm-jms-express-consumer/views/layout.pug
   create : pm-jms-express-consumer/app.js
   create : pm-jms-express-consumer/package.json
   create : pm-jms-express-consumer/bin/
   create : pm-jms-express-consumer/bin/www

   change directory:
     $ cd pm-jms-express-consumer

   install dependencies:
     $ npm install

   run the app:
     $ DEBUG=pm-jms-express-consumer:* npm start
```

```
$ cd pm-jms-express-consumer
$ npm install

npm notice created a lockfile as package-lock.json. You should commit this file.
added 118 packages from 174 contributors and audited 247 packages in 4.445s
found 1 low severity vulnerability
  run `npm audit fix` to fix them, or `npm audit` for details
```

```
$ npm audit

                       === npm audit security report ===

# Run  npm install pug@2.0.4  to resolve 1 vulnerability
┌───────────────┬──────────────────────────────────────────────────────────────┐
│ Low           │ Regular Expression Denial of Service                         │
├───────────────┼──────────────────────────────────────────────────────────────┤
│ Package       │ clean-css                                                    │
├───────────────┼──────────────────────────────────────────────────────────────┤
│ Dependency of │ pug                                                          │
├───────────────┼──────────────────────────────────────────────────────────────┤
│ Path          │ pug > pug-filters > clean-css                                │
├───────────────┼──────────────────────────────────────────────────────────────┤
│ More info     │ https://npmjs.com/advisories/785                             │
└───────────────┴──────────────────────────────────────────────────────────────┘


found 1 low severity vulnerability in 247 scanned packages
  run `npm audit fix` to fix 1 of them.
```

```
$ npm audit fix
+ pug@2.0.4
removed 3 packages and updated 8 packages in 4.102s
fixed 1 of 1 vulnerability in 247 scanned packages
$ cd ..
```
