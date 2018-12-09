package app.content.overview

import Markdown
import app.readOnlyCode
import react.RBuilder
import react.dom.code
import react.dom.div
import react.dom.p
import react.dom.textArea

val ktFiles: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """

                # Kotlin Files
                All Kotlin files have a file type of **.kt**

                Files can be named anything and do not need to match class names like in Java.

                ![files](files.png)
                ## Structure
                Top level declarations include:

                > Note that none are required

                * package
                * import
                * class
                * interface
                * val
                * const
                * var
                * typealias
                * fun

                The ordering is as follows:

                """.trimIndent().trimMargin()
    }
    readOnlyCode("""
            //Comments can appear anywhere

            package a.b.c

            |import java.util.* // Must appear at top below any package declaration

            |typealias ABC = String // Must appear below any imports and above anything else

            |// All other declarations can appear in any order below all of the above if provided
            |val a = 3
            |var b = "Hello"
            |const val c = "4"
            |interface Foo
            |data class Bar(val baz: String): Foo
            |class Useless
            |enum class FooBar { B, A, Z }
            |fun myfunction() = {}

        """.trimIndent().trimMargin()
    )
    Markdown {
        attrs.source = """
                ## Packages
                A package declaration goes at the very top allowing items in this file to be namespaced to that package.

                They can only appear at most once, and are not required.

                Packages are not required to match the directory structure as in java.

                """.trimIndent().trimMargin()
    }
    readOnlyCode("package a.b.c")

    Markdown {
        attrs.source = """
                ## Imports
                Imports appear below package declarations if any exist.

                Imports must appear above all other top level declarations.

                Kotlin does not have a separate keyword for static imports.

                """.trimIndent().trimMargin()
    }
    readOnlyCode("""
        import a.b.c.Bar
        val foo = Bar() // Class in package a.b.c
    """.trimIndent()
    )
}