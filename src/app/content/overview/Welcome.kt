package app.content.overview

import Markdown
import react.RBuilder

val welcome: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
            # Kotlin

            || Tables        | Are           | Cool  |
            || ------------- |:-------------:| -----:|
            || col 3 is      | right-aligned | ${'$'}1600 |
            || col 2 is      | centered      |   ${'$'}12 |
            || zebra stripes | are neat      |    ${'$'}1 |

            ```kotlin
            fun main(){
            //sampleStart
            |    println("hello")
            //sampleEnd
            }
            ```
        """.trimIndent().trimMargin()
    }
}