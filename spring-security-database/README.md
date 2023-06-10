# Spring Security with database
_In this section we will understand how to implement spring security by storing the users in the database._  
_Before proceeding with this section make sure you understand the [basic spring security](https://github.com/rajasekhar1404/spring-secuirty/tree/master/spring-security-basic#readme) with inMemory user details_

### Structure
- We'll create a bunch of API's with different securities,
  - `/` can be accessed by anyone.
  - `/reg` can be accessed by anyone, for registering the users.
  - `/admin` can be accessed only by `ADMIN`.
  - `/employee` can be accessed only by `EMPLOYEE`.
  - `/super-admin` can be accessed only by `SUPERADMIN`.
  - `/emp-admin` can be accessed by `ADMIN` and `EMPLOYEE`.
  - `/principle` can be accessed by any registered user, it will return the User profile.
- We'll create a `UserAuthenticationProvider` by implementing `AuthenticationProvider` interface.
  - `AuthenticationProvider` will give have two abstract methods `authenticate` and `support` which needs to be overridden.
  - `authenticate` will give the `Authorization` object, which has been created in the `SecurityContext` and supplied to this method.
  - `support` is for checking the which `Authentication` class is validating the user.
---
### Request Flow
- When any request to a secured endpoint is received, an Authorization object will be created with the credentials entered in the form and passed to `Authentication` object in the `authenticate` method.
- We'll extract the username from the authentication.getPrinciple() and password from authentication.getCredentials()
- Fetch the user profile by username from the database,
- Verify the password by using matches method from PasswordEncoder,
- If the user is valid then we'll create the user Principle, credentials, roles and send it using `UsernamePasswordAuthenticationToken`.
- Roles has to be sent as `List<GrantedAuthorities>`.
- That principle we have created can be accessed in any api with `@AuthenticationPrinciple` annotation.