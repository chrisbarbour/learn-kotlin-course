package app.content.basics

import Markdown
import app.playground
import react.RBuilder
import react.dom.code
import react.dom.h1
import react.dom.h2
import react.dom.p

val getStarted: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
            # Kotlin Basics

            ## Kotlin Files (.kt)
            Kotlin files can have any name and do not have to match a class name within it.
            Kotlin files end with .kt

            ## File Content

            ```kotlin
            fun main(){
            //sampleStart
            |    println("hello")
            //sampleEnd
            }
            ```
        """.trimIndent().trimMargin()
    }
    h1 { +"Basics"}
    p { +"Here is a list of basics you will need for this course" }
    h2 { +"Kotlin File" }
    h2 { +"Package" }
    p { +"Package declarations go at the top of a kotlin file. They do not have to match the directory structure."}
    code{
        attrs["auto-indent"] ="true"
        attrs["match-brackets"] ="true"
        attrs["data-highlight-only"] = "true"
        attrs["lines"] = "true"
        +(
                "package my.code.goes.here\n" +
                "// ..."
        )
    }
    h2 { +"Import" }
    p { +"Import declarations also go at the top of a kotlin file."}
    code{
        attrs["auto-indent"] ="true"
        attrs["match-brackets"] ="true"
        attrs["data-highlight-only"] = "true"
        attrs["lines"] = "true"
        +(
                "package my.code.goes.here\n" +
                        "// ..."
                )
    }
}
val functions: RBuilder.() -> Unit = {
    h1 { +"Functions"}
    p { +"In Kotlin xyz...dklfsdkfjdsjf" }
    code{
        +("fun main(args: Array<String>){\n" +
                "//sampleStart val a = \"Hello\";\n" +
                "println(a);\n//sampleEnd\n" +
                "}")
    }
}