# Spring Security
- Before we continue, I want to congratulate you for brining your spring journey till here, you might have already covered most of the basic spring application concepts, this is not anymore complex than them, I will make this journey as simple as possible, so let's get started...
- Create a new spring boot application with `spring web` and `spring security` starter dependencies, if you IDE don't support creating a project, use [spring starter](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.1.0&packaging=jar&jvmVersion=17&groupId=com.security&artifactId=spring-secuirty-demo&name=spring-secuirty-demo&description=Demo%20project%20for%20Spring%20Boot&packageName=com.security&dependencies=web,security) website.
- Once your application is created run it and try to access `http://localhost:8080`, you will get a login page asking username and password,
- If you are calling the url from browser: You can give the username as `user` and password will be generated everytime you run the application and printed on the console.
- Postman: enter the url which is http://localhost:8080, goto authorization tab and select basic auth, then enter the username password
- CURL: curl -user username:password http://localhost:8080/login
- If you don't want to use the default username and auto generated password, you can specify your own username and password in the `application.properties` file like below.
  - `spring.security.user.name=raja`
  - `spring.security.user.password=sekhar`