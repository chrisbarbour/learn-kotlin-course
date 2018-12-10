package app.content.libraries

import app.annotatedCode
import app.divider
import app.readOnlyCode
import markdown
import react.RBuilder
import react.dom.div
import react.dom.img
import react.dom.p

val intro: RBuilder.() -> Unit = {
    annotatedCode("""
        # HTTP4K

        ![http4k logo](http4k.png)

        HTTP4k is a lightweight library built in Kotlin that allows you to build HTTP apis.

        The core library is only around 700kb

        Here is an example of a very simple endpoint that starts a server listening on port 8080 that returns "Hello, World!" no matter what

    """, """
        { request: Request -> Response(OK).body("Hello, World!") }.asServer(SunHttp(8080)).start()
    """, readOnly = true
    )
}

val projectSetup: RBuilder.() -> Unit = {
    markdown("""
        # Project Setup

        1. Create a new maven project in IntelliJ called 'kotlin-movie-api'

        2. Rename src/main/java to src/main/kotlin and src/test/java to src/test/kotlin"

        > File > New > Project...
        """.trimIndent())
    img(src = "newProject.png"){ attrs.width = "400px"}
    p{}
    img(src = "gav.png"){ attrs.width = "400px"}
    annotatedCode("""
        3. Set up Maven for Kotlin and HTTP4k

       > pom.xml
    """,
    """
        <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>

        <groupId>com.kotlin.learn</groupId>
        <artifactId>kotlin-movie-api</artifactId>
        <version>1.0-SNAPSHOT</version>

        <properties>
            <kotlin.version>1.3.11</kotlin.version>
        </properties>

        <dependencies>
            <dependency>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-stdlib</artifactId>
                <version>${'$'}{kotlin.version}</version>
            </dependency>
            <dependency>
                <groupId>org.http4k</groupId>
                <artifactId>http4k-core</artifactId>
                <version>3.103.2</version>
            </dependency>
            <dependency>
                <groupId>org.http4k</groupId>
                <artifactId>http4k-format-jackson</artifactId>
                <version>3.103.2</version>
            </dependency>
        </dependencies>

        <build>
            <sourceDirectory>${'$'}{project.basedir}/src/main/kotlin</sourceDirectory>
            <testSourceDirectory>${'$'}{project.basedir}/src/test/kotlin</testSourceDirectory>

            <plugins>
                <plugin>
                    <artifactId>kotlin-maven-plugin</artifactId>
                    <groupId>org.jetbrains.kotlin</groupId>
                    <version>${'$'}{kotlin.version}</version>

                    <executions>
                        <execution>
                            <id>compile</id>
                            <goals> <goal>compile</goal> </goals>
                        </execution>

                        <execution>
                            <id>test-compile</id>
                            <goals> <goal>test-compile</goal> </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>

    </project>
    """, readOnly = true
    )

}

val firstApi: RBuilder.() -> Unit = {
    annotatedCode("""
        # Your first HTTP4K API

        1. Create a package under src/main/kotlin named api

        2. Create a new Kotlin file called MyFirstApi

        3. Edit the file to look like the following example

        4. Run the main function and hit http://localhost:8080 with postman

        > src/main.kotlin/api/MyFirstAPI.kt
    """, """
        fun main () {
            val api = { request: Request -> Response(OK).body("Hello, World!") } // This is the full api
            api.asServer(SunHttp(8080)).start() // This makes it into a SunHttp Server bound to port 8080 and starts it
        }
    """, readOnly = true)
}

val basicRouting: RBuilder.() -> Unit = {
    annotatedCode("""
        # Basic Routing

        At its simplest http4k is just a function that takes a request and returns a response.

        The Request object holds information about the request. This includes headers, the path and request parameters.

        The Response can hold Response codes, headers and a body among other things.

        There is a typealias named HttpHandler for this function.
    """, """
        typealias HttpHandler = (Request) -> Response
    """, readOnly = true)
    annotatedCode("""
        In fact we have already created a HttpHandler.

        > The reason we can call asServer on it is because there is an extension function on HttpHandler.
    """, """
        { request: Request -> Response(OK).body("Hello, World!") }
    """, readOnly = true)
    annotatedCode("""
        ## routes

        There is a very handy routes function that lets you bind endpoints to your Http functions.

        Change the api value to look like the following. This is similar to what we had before except now you can only do GET requests.
    """, """
        val api = routes(
            "/" bind Method.GET to { request: Request -> Response(OK)}
        )
    """, readOnly = true)
    annotatedCode("""
        Now we can add more routes too. You can even nest them
    """, """
        val api = routes(
            "/" bind Method.GET to { request: Request -> Response(OK)},
            "/other" bind routes(
                "/nested" bind Method.GET to { request: Request -> Response(OK)}
            )
        )
    """, readOnly = true)
}

val requests: RBuilder.() -> Unit = {
    annotatedCode("""
        # Handling Request

        Requests have a lot of possible information including headers, body content, form content, path parameters and query string parameters.

        ## Retrieving Query String Parameters

        When you want the user to pass data via query strings you can simply use .query() on the request.
    """, """
        // With a url request ending with ?myQuery=value;myOtherQuery=value2
        // get myQuery by doing this
        val myQueryValue: String? = request.query("myQuery")
    """, readOnly = true
    )
    annotatedCode("""
        ## Retrieving Path Parameters

        You can define routes with path parameters like this
    """, """
        // This endpoint will require a movieId
        "/movie/{movieId}" bind Method.GET to { request: Request ->
            // To get the parameter
            val movieId: String? = request.path("movieId")
            Response(OK).body("Found movie with id: ${'$'}movieId")
        }
    """, readOnly = true
    )
    annotatedCode("""
        ## Retrieving Request Body

        Some requests may have a body

        This is how to get the body as a String.

        > Check out Lenses below for a better solution.
    """, """
        "/movie" bind Method.POST to { request: Request ->
            // To get the body
            val movieInfo: String = request.body.payload.asString()
            ...
        }
    """, readOnly = true
    )
}

val responses: RBuilder.() -> Unit = {
    annotatedCode("""
        # Handling Responses
        The response object allows you to set headers, status code and a body
    """, """
        Response(OK)
             .headers(listOf("Content-Type" to "application/text"))
             .body("Hello, World!")

        // You can also respond with a JSON payload using the Jackson Library. (already included in the pom above)
        Response(OK)
             .headers(listOf("Content-Type" to "application/json"))
             .body(Jackson.asJsonString(myResponseObject))
    """, readOnly = true
    )
}

val filters: RBuilder.() -> Unit = {
    markdown("")
    annotatedCode("""
        # Working With Filters

        The term filter comes from the pipes and filters pattern where messages are filtered by some process then piped to another filter in a chain.

        In HTTP4K a filter is simply a function that takes an HttpHandler and returns another.

        The result is a chain of filters that can be invoked before the request is actually processed into a response.

        You can choose to do anything with a filter, many are provided for you.

        * Basic Auth
        * Try Catching
        * Request Tracing
        * CORS
        * Cookie Handling
        * Cache Control
        * Debugging

    """, """
        interface Filter : (HttpHandler) -> HttpHandler
    """, readOnly = true
    )
    annotatedCode("""
        They can be applied as follows
    """, """
        ServerFilters.Cors(UnsafeGlobalPermissive).then(api).asServer(SunHttp(8080)).start()
    """, readOnly = true
    )
    annotatedCode("""
        You can build your own like this
    """, """
        val latencyFilter = Filter {
        next: HttpHandler -> {
            request: Request ->
                val start = System.currentTimeMillis()
                val response = next(request)
                val latency = System.currentTimeMillis() - start
                println("I took ${'$'}latency ms")
                response
        }
    }
    ServerFilters.Cors(UnsafeGlobalPermissive).then(latencyFilter).then(api).asServer(SunHttp(8080)).start()
    """, readOnly = true
    )
}

val lenses: RBuilder.() -> Unit = {
    annotatedCode("""
        # Lenses
        Lenses, not to be confused with functional lenses, allow you to get or set a part of an http message in a type safe way.

        Lenses allow you to specify that a piece of an http message, like the body can be transformed into a specific type.

        They are bi-directional.
    """, """
        import org.http4k.format.Jackson.auto

        data class Car(val name: String)

        val carLens = Body.auto<Car>().toLens() // Bi-directional lens
        val api = routes(
            "/" bind Method.POST to { request: Request ->
                val carInfo = carLens(request) // Extract Car from request
                Response(OK).with(
                        carLens of carInfo // Inject it back into the response
                )
            }
        )
    """, readOnly = true
    )
}

val excercise: RBuilder.() -> Unit = {
    markdown("""
        # Exercise

        Build an api with the following specification:

        > /car  GET returns all cars in JSON array

        > /car POST creates a new car and returns location in Location header (Status code 201)

        > /car/{id} GET returns car as JSON with id

        > /car/{id} DELETE deletes car with id

        **Guide**

        * You will need to create a class for Car
        * Use Lenses for mapping to and from a Car(s)
        * You won't need filters but feel free to add some
        * Everything you need is on this page somewhere, I hope :)

    """.trimIndent())
    readOnlyCode("""
        // Use this for some data
        val cars = listOf(
            Car("Nissan", "350z"),
            Car("BMW", "i8"),
            Car("Chevrolet", "Trax"),
            Car("Hyundai", "Sante Fe")
        )
    """.trimIndent())
}

val http4k: RBuilder.() -> Unit = {
    intro()
    markdown("For this section of the course we are going to build an app using http4k")
    divider()
    projectSetup()
    divider()
    firstApi()
    divider()
    basicRouting()
    divider()
    responses()
    divider()
    requests()
    divider()
    lenses()
    divider()
    filters()
    divider()
    excercise()
}