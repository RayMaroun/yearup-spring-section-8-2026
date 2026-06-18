# Exercise 14 — Document Your API with Swagger — Solution

A hands-on exercise. The "solution" is the dependency, one small config class, the URL, and what you should see.

## STEP 1 — Add springdoc
Add the single dependency to your Sneaker Drops `pom.xml` and restart the app:
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.6</version>
</dependency>
```

## STEP 2 — Explore the docs
Open:
```
http://localhost:8080/swagger-ui.html
```
You should see all your sneaker endpoints, grouped by controller (e.g. under `sneaker-controller`): the `GET` (all and by id), `POST`, `PUT`, and `DELETE` on `/api/sneakers`. Each is expandable to show its parameters and the shape of what it returns. The page is generated from your actual code, so it always matches the real API.

## STEP 3 — Add the Authorize button
By default Swagger has no way to send a token, so "Try it out" on a secured route fails. Add a small config class declaring an HTTP bearer (JWT) security scheme; it makes an **Authorize** button appear at the top of the page:
```java
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        String scheme = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(scheme))
                .components(new Components().addSecuritySchemes(scheme,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
```
(`OpenAPI`, `Components`, `SecurityScheme`, `SecurityRequirement` come with the springdoc dependency — `io.swagger.v3.oas.models.*`.)

## STEP 4 — Try one out
- Click **Try it out** on `GET /api/sneakers` → it fires a real request and shows **200 OK** with the JSON list — the same result you saw in Insomnia.
- For a secured `POST`: click **Authorize** at the top, paste **just the token** from logging in (springdoc adds the `Bearer` prefix), then **Try it out** the POST with a JSON body → **201 Created**. Without authorizing, that secured POST returns **401**.

Behind the page, springdoc also serves the raw description at `/v3/api-docs` in the **OpenAPI** format, which tools like Insomnia can import.

> **Note (because your API is secured in Exercise 13):** make sure the docs paths are reachable without a token — i.e. `/swagger-ui/**` and `/v3/api-docs/**` are treated as **public** in your security config. If the docs page itself returns a 401, that's why.

You now have a **built, secured, and documented** REST API of your own — exactly the shape the capstone asks for.
