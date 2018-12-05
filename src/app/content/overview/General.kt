package app.content.overview

import Markdown
import app.readOnlyCode
import app.runnableCode
import react.RBuilder

val general: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
                # General Idioms
                ## String Interpolation
                Strings can be built from various components by using the $ symbol inside quotes " "
                """.trimIndent()
    }
    runnableCode("""
        val hello = "Hello"
        val world = " World!"
        println("${'$'}hello, World")
        println("You can also use \${'$'}{...} to execute code inline like this: ${'$'}{ hello + world }")
        """.trimIndent()
    )
    Markdown {
        attrs.source = """
                ## Runtime Instance Checking
                To check the class of something at runtime using reflection you can use the special syntax ::class
                """.trimIndent()
    }
    runnableCode("""
        val abc = "Hello"
        println(abc::class) // Gets a Kotlin KClass<T>
        println(abc::class.java) // Gets a Java Class<T>
        """.trimIndent()
    )
    Markdown { attrs.source = "You can also check if the type of something matches a specific type using the **is** keyword" }
    runnableCode("""
        val abc: Any = "Hello" // Try removing the Any here
        println(abc is kotlin.String)
        println(abc is java.lang.String) // Don't do this (but will work if type is unknown at compile time)
        println(abc is Int)
        """.trimIndent()
    )
    Markdown {
        attrs.source = """
                ## Ranges
                step, downTo, rangeTo, until, ..
                """.trimIndent().trimMargin()
    }
    Markdown {
        attrs.source = """
                ## Null ?!!
                ...
                ## Destructuring
                ...
                ## Yada Yada Yada...
                """.trimIndent().trimMargin()
    }
}