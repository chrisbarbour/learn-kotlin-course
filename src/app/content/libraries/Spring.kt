package app.content.libraries

import Markdown
import react.RBuilder
import app.annotatedCode
import app.divider

val spring: RBuilder.() -> Unit = {

    Markdown {
        attrs.source = """
                ![alt text](https://spring.io/img/homepage/icon-spring-boot.svg "Spring 5 Logo")
                # Spring
                """.trimIndent().trimMargin()
    }


    annotatedCode(readOnly=true,
            annotation = """

        Pivotal has embraced Kotlin as a first class language.

        Examples from spring.io blog - highlight some differences in Java and Kotlin.

        ## Functional bean declaration DSL
        Spring Framework 5.0 introduces a new way to register beans using lambda as an alternative to XML or JavaConfig with **@Configuration** and **@Bean**. In a nutshell, it makes it possible to register beans with a Supplier lambda that acts as a FactoryBean.

        ### Java
            """,
            code = """

               GenericApplicationContext context = new GenericApplicationContext();
               context.registerBean(Foo.class);
               context.registerBean(Bar.class, () -> new
                |   Bar(context.getBean(Foo.class))
               );
                """.trimIndent().trimMargin()
    )
    annotatedCode(readOnly=true,
            annotation = """
        ### Kotlin
            """,
            code = """

                beans {
                |   bean<Foo>()
                |   bean { Bar(ref()) }
            }
        """.trimIndent().trimMargin()
    )

    divider()

    Markdown {
        attrs.source = """***""".trimIndent().trimMargin()
    }

    annotatedCode(readOnly=true,
            annotation = """

        # Example Reactive Web App with Spring.

        We are going to build a web app using Spring WebFlux and Kotlin which is Springs implemenation of Reactive Streams.

        The main goal of Reactive Streams is to govern the exchange of stream data across an asynchronous boundary.
        With a reactive stream the receiving side is not forced to buffer arbitrary amounts of data.
        In other words, *back pressure is an integral part of this model in order to allow the queues which mediate between threads to be bounded.
        Therefore care has to be taken to mandate fully non-blocking and asynchronous behavior of all aspects of a Reactive Streams implementation.

        (*In other words, if the client can't handle what the server is dishing out, they will auto negoiate and the server will back off a bit!)

        #### MongoDB
        For the full stack implemenation we need to choose a DB with a reactive driver. MongoDB, Cassandra, Redis are currently supported reactive DBs.

        We will build a service that has some movies in a database.
        We can get a movie id and ask for event stream of data for that movie.

        1. Download / Verify Docker. Install mongo Docker image and run it.
        2. Create a blank web project using the Initializer
        3. Build out Movie & Event data classes
        4. Build out the FlixFlux service
        5. Build Bean to populate the database
        6. Create a WebController class to expose our endpoints.
        7. Build a MovieHandler Component
        8. Create a router function to handle requests

                """.trimIndent().trimMargin(),

            code = """
"""
    )

    annotatedCode(readOnly=true,
            annotation = """

        ## 1. Install Mongo Docker image and run it.

        Open Terminal,
        Create a tmp directory for mongo to save the data to
        Start docker and download / run the MongoDB instance.

        Verify and Quit()
                """.trimIndent().trimMargin(),

            code = """
                cd /tmp
                mkdir mongo
                docker run --name reactive-demo -p 27017:27017 -v /tmp/mongo:/data/db -d mongo

                #verify the mongo install
                docker ps

                #should see mongo running OK. Attempt connect
                mongo localhost/mydb

                #quit mongo client
                quit()

"""
    )

    annotatedCode(readOnly=true,
            annotation = """

        ## 2. Create a blank web project using the Initializer

        * Navigate to Spring Initializr https://start.spring.io/
        * Give your project a name and change the language to Kotlin.
        * Include dependencies: (Reactive Web, Reactive MongoDB)
        * Generate a project and open in IntelliJ.
                """.trimIndent().trimMargin(),

            code = """
"""
    )

    annotatedCode(readOnly=true,
            annotation = """

        ##  3. Build out Movie & Event data classes

        For spring to work you need a class. It also must be open, Kotlin classes are final by default. Newer Spring versions do this for you.

        Find and open the Application class in the IDE.


                """.trimIndent().trimMargin(),

            code = """
        data class Movie(@Id val id: String, val title: String, val genre: String?)
        data class MovieEvent(@Id val movie: Movie, val eventDate: Date, val user: String?)
"""
    )
    annotatedCode(readOnly=true,
            annotation = """

        ##  4. Build out the FlixFlux service

        Pay attention to the streamStreams function. Its the function that will generate the Fluxs every second to simulate an event source.
        Try and figure out what it is doing.

                """.trimIndent().trimMargin(),

            code = """

        interface MovieRepository : ReactiveMongoRepository<Movie, String>

        @Service
        class FluxFlixService(private val movieRepository: MovieRepository) {

        fun streamStreams(movie: Movie): Flux<MovieEvent> {

        val interval :Flux<Long> = Flux.interval(Duration.ofSeconds(1))

        val events :Flux<MovieEvent> = Flux.fromStream(Stream.generate { MovieEvent(movie, Date(), randomUser()) })

        return Flux.zip(interval, events)
                .map { it.t2 }
        }

        private fun randomUser(): String {
            val users = "David, Calum, Cris, Richard, The Hoff, The Rock".split(",")
            return users[Random().nextInt(users.size)]
        }

        fun all(): Flux<Movie> {
            return movieRepository.findAll()
        }

        fun byId(id: String): Mono<Movie> {
            return movieRepository.findById(id)
        }

        }
"""
    )
    annotatedCode(readOnly=true,
            annotation = """

        ##  5. Build a CommandLineRunner Bean to populate the database on startup


                """.trimIndent().trimMargin(),

            code = """
        @Bean
        fun demo(movieRepository: MovieRepository) = CommandLineRunner {

        movieRepository.deleteAll()
               .subscribe(null, null, {
            Stream.of("Aeon Flux", "Enter the flux", "something else", "Blah")
                    .map { name -> Movie(UUID.randomUUID().toString(), name, randomGenre()) }
                    .forEach { m -> movieRepository.save(m)
                            .subscribe { println(it) } }
        })

        }

        private fun randomGenre(): String {
            val genres = "horror, romcom, drama, action, documentry".split(",")
            return genres[Random().nextInt(genres.size)]
        }
"""
    )

    Markdown {
        attrs.source = """
            At this point we should be able to run the code, and have it load some data into the MongoDB.

            If all is well. Continue.
        """.trimIndent().trimMargin()
    }


    annotatedCode(readOnly=true,
            annotation = """

        ##  6. Create a WebController class to expose our endpoints.

        You * might * require @CrossOrigin(origins = arrayOf("http://localhost:8080")) added to the class.

                """.trimIndent().trimMargin(),

            code = """
        @RestController
        @RequestMapping("/movies")
        class MovieRestController(private val fluxFlixService: FluxFlixService) {

        @GetMapping( value = arrayOf("/{id}/events"), produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
        fun events(@PathVariable id: String): Flux<MovieEvent> {
            return fluxFlixService.byId(id)
                    .flatMapMany { x -> fluxFlixService.streamStreams(x as Movie) }
        }

        @GetMapping(produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
        fun moviesNew(): Flux<Movie> {
            return fluxFlixService.all()
        }

        @GetMapping
        fun all(): Flux<Movie> {
            return fluxFlixService.all()
        }

        @GetMapping("/{id}")
        fun byId(@PathVariable id: String): Mono<Movie> {
            return fluxFlixService.byId(id)
        }
        }
"""
    )

    Markdown {
        attrs.source = """
            Test the web controller and see if there are events available.

            If your running standard port 8080 then try:

            http://localhost:8080/movies
            |   * Did you get a list of Movie objects?

            |   Grab an id an use it in the following request.

            http://localhost:8080/movies/{id}
            |   * Did you get the details of a particular movie?

            |   Lets ask for a Flux stream of events.

            http://localhost:8080/movies/{id}/events


        """.trimIndent().trimMargin()
    }

    divider()

    Markdown {
        attrs.source = """

            # Functional routing with the Kotlin DSL for Spring WebFlux
            **WebControllers are nice and demonstrate how you would do regular Web API, but for reactive streams we can do better!**

            Spring WebFlux has a new Routing functionality which replaces the @RestController, @RequestMapping
            using the WebFlux functional API via a dedicated Kotlin DSL.

            Lets create a Router to replace the controller above.

        """.trimIndent().trimMargin()
    }


    annotatedCode(readOnly=true,
            annotation = """

        ##  7. Build a MovieHandler to handle the requests and provide a response


                """.trimIndent().trimMargin(),

            code = """
        @Component
        class MovieHandler(val service: FluxFlixService) {

            fun all(serverRequest: ServerRequest): Mono<ServerResponse> {
                return ServerResponse.ok().body(service.all(), Movie::class.java)
            }

            fun byId(serverRequest: ServerRequest): Mono<ServerResponse> {
                return ServerResponse.ok().body(service.byId(serverRequest.pathVariable("id")), Movie::class.java)
            }

            fun events(serverRequest: ServerRequest): Mono<ServerResponse> {
                return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(service.byId(serverRequest.pathVariable("id"))
                                .flatMapMany { m -> service.streamStreams(m) }, MovieEvent::class.java)
            }

        }
"""
    )
    annotatedCode(readOnly=true,
            annotation = """

        ##  8. Create a router function to handle requests


                """.trimIndent().trimMargin(),

            code = """
        @Configuration
        class Router {
            @Bean
            fun routes(service: FluxFlixService, handler: MovieHandler): RouterFunction<ServerResponse> {
                return router {
                    ("/movies").nest {
                        GET("/") { handler.all(it) }
                        GET("/{id}") { handler.byId(it)  }
                        GET("/{id}/events") { handler.events(it) }
                    }
                }
            }
        }
"""
    )

    divider()
    Markdown {
        attrs.source = """
# All Done.
            """.trimIndent().trimMargin()
    }

    annotatedCode(readOnly=true,
            annotation = """

        For some additional stuff to play around with in your own time. Check out spring-kotlin-functional.
        ## Additional Fun()..  Create a Reactive webservice using Spring Webflux witout Springboot.

        Spring has developed spring-kotlin-functional library.
        Spring WebFlux and Reactor Netty allow for programmatic bootstrap of the application since they are natively designed to run as an embedded webserver.

        More Info on this here: [https://spring.io/blog/2017/08/01/spring-framework-5-kotlin-apis-the-functional-way]

                """.trimIndent().trimMargin(),

            code = """
            class Application {

            private val httpHandler: HttpHandler
            private val server: HttpServer
            private var nettyContext: BlockingNettyContext? = null

            constructor(port: Int = 8080) {
            val context = GenericApplicationContext().apply {
                beans().initialize(this)
                refresh()
            }
            server = HttpServer.create(port)
            httpHandler = WebHttpHandlerBuilder.applicationContext(context).build()
            }

            fun start() {
            nettyContext = server.start(ReactorHttpHandlerAdapter(httpHandler))
            }

            fun startAndAwait() {
            server.startAndAwait(ReactorHttpHandlerAdapter(httpHandler),
                { nettyContext = it })
            }

            fun stop() {
            nettyContext?.shutdown()
            }
            }

            fun main(args: Array<String>) {
            Application().startAndAwait()
            }
"""
    )



}