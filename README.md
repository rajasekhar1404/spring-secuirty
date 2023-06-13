# Spring Security
_It will provide the authentication and authorization for the api's_
### Architecture
- Client will send a request to server,
- Every request will be intercepted by `Filter` ex: FormLogin/httpBaic => `BasicAuthenticationFilter`,
- Filter will delegate the request to `AuthenticationManager` which is an interface which will consists of two abstract methods `authenticate` and `supports`
    - `authenticate`: Here we'll get the unauthorized authentication object which will consists of user principle and credentials like username and password entered throw form, we can use them to get the user from the db and if the user is valid then we'll prepare a Authentication object with principle, credentials and granted authorities then return it.
    - `supports`: This is to check which type of authentication that provider is supporting.
- `AuthenticationManager` will be implemented by `ProviderManager` class which will loop through all the providers available for that type of authentication and if the authorization is became valid in any provider it will keep the authorization in the Security context then request will be sent to servlet.

- We can also create our own filters by implementing `Filter` or `OncePerRequestFilter`.
- We have to override doFilterInternal method from the filter which will give request, response and filterchain objects as params.
- We'll get the headers or user credentials from the request and we'll validate them, if everything is okay, then we can continue the filtration by calling doFilter method.