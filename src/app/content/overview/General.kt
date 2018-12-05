package app.content.overview

import Markdown
import app.annotatedCode
import app.readOnlyCode
import app.runnableCode
import react.RBuilder

val general: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                # General Idioms
                ## String Interpolation
                Strings can be built from various components by using the $ symbol inside quotes " "
            """,
            code = """
                val hello = "Hello"
                val world = " World!"
                println("${'$'}hello, World")
                println("You can also use \${'$'}{...} to execute code inline like this: ${'$'}{ hello + world }")
            """
    )
    annotatedCode(
            annotation = """
                ## Runtime Instance Checking
                To check the class of something at runtime using reflection you can use the special syntax ::class
            """,
            code = """
                val abc = "Hello"
                println(abc::class) // Gets a Kotlin KClass<T>
                println(abc::class.java) // Gets a Java Class<T>
            """
    )
    annotatedCode(
            annotation = "You can also check if the type of something matches a specific type using the **is** keyword",
            code = """
                val abc: Any = "Hello" // Try removing the Any here
                println(abc is kotlin.String)
                println(abc is java.lang.String) // Don't do this (but will work if type is unknown at compile time)
                println(abc is Int)
            """
    )
    annotatedCode(
            annotation = """
                ## Ranges
                A range of numbers can easily be created using the **..** operator
            """,
            code = """
                val range = 0..5
                val sameRange = 0.rangeTo(5) // rangeTo is the same as ..
                println(range.toList())
                println(sameRange.toList())
            """
    )
    annotatedCode(
            annotation = """
                To reverse the order use downTo
            """,
            code = """
                val range = 10.downTo(5)
                val sameRange = 10 downTo 5 // This is the infix notation
                println(range.toList())
                println(sameRange.toList())
            """
    )
    annotatedCode(
            annotation = """
                If you want to exclude the last number use until
            """,
            code = """
                val range = 4.until(10)
                val sameRange = 4 until 10 // This is the infix notation
                println(range.toList())
                println(sameRange.toList())
            """
    )
    annotatedCode(
            annotation = """
                Ranges also have a step property allowing you to skip values.

                > Step can only be a positive number.
            """,
            code = """
                val range = 4.until(10).step(3)
                val sameRange = 4 until 10 step 3 // Step is also infix
                println(range.toList())
                println(sameRange.toList())
            """
    )
    Markdown {
        attrs.source = "> Ranges are also iterable. This means you can iterate over them with all the standard iterable functions listed in [Collections](/collections)"
    }
}