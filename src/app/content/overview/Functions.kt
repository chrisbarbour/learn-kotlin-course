package app.content.overview

import Markdown
import app.annotatedCode
import react.RBuilder

val functions: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                # Functions
                Functions in Kotlin are first class citizens and can be declared anywhere, including outside of any classes.

                They can be declared using the **fun** keyword as follows:
            """,
            code = """
                fun log(log: String) = println(log)
                log("Hello, World!")
            """
    )
    annotatedCode(
            annotation = """
                Functions can have implicit return types when using the = form.

                The following are equivalent
            """,
            code = """
                fun doSomething() = "Foo Bar"
                fun doSomething2(): String = "Foo Bar"
                fun doSomething3(): String { return "Foo Bar" }
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                Calling functions looks like this:
            """,
            code = """
                fun text() = "Foo Bar"
                println(text())
            """
    )
    Markdown {
        attrs.source = """
                ## Lambdas
                ...
                ## Function Parameters
                ...
                (named parameters, ordering, default values, last parameter curly braces)
                ```kotlin
                fun main(){
                //sampleStart
                fun myFunction(a: String, b: ()->String) = a + b()
                |   println(myFunction("One", {"Two"})) //<- same as below
                |   println(myFunction("Three"){"Four"}) //<- curly brackets here instead of parameter
                //sampleEnd
                }
                ```
                ...
                ## let, apply, run, also, with ...
                ...
                ## map, filter, fold, reduce, etc...
                ...
                ## Infix
                ..
                ## Operators (contains (in), rangeTo(..), plus (+), etc.)
                ..
                ## Receiver Types
                ...
                ## This hierarchy
                ## Yada Yada Yada...
                """.trimIndent().trimMargin()
    }
}