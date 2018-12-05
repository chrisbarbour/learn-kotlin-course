package app.content.overview

import Markdown
import react.RBuilder

val functions: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
                # Functions
                ...
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