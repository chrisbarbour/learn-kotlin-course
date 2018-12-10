package app.content.intro

import Markdown
import app.annotatedCode
import app.runnableCode
import markdown
import react.RBuilder
import react.dom.img

val introduction: RBuilder.() -> Unit = {
    Markdown { attrs.source = "# Introduction" }
    img(classes = "kotlin-logo-large", src = "https://upload.wikimedia.org/wikipedia/commons/7/74/Kotlin-logo.svg"){}
    Markdown { attrs.source = """
            Welcome to this course on Kotlin by:
            * Chris Barbour
            * Richard Gibson
            * Calum M'cElhone
            * David Fyffe""".trimIndent()
    }
    Markdown {
        attrs.source = """
                # What you need for this course

                * IntelliJ (Not required, its what I will be using)
                * Maven (With Correct Settings xml)
                * Jdk 8
                * Postman or equivalent

                # Course Content

                This course will take you through Kotlin end to end.

                We will learn how to use Spring, HTTP4K and Exposed with Kotlin

                If we have time we will introduce some advanced topics.

                """.trimIndent().trimMargin()
    }
    Markdown {
        attrs.source = """
                # What is Kotlin?
                Kotlin is a programming language built by JetBrains supported by the Kotlin Foundation in collaboration with Google.

                ## JVM, JS, Native

                Kotlin can be run almost anywhere now because it not only runs on the JVM but it can also run in the browser and natively on mac, windows or linux.

                In this course we will be going through the JVM flavour of Kotlin.

                The website you are looking at right now is written in Kotlin JS.

                ## A Basic Example

                You will see many examples like this in the course.

                Most are runnable code snippets. Run them by clicking the green play button at the top right of the snippet.

                Feel free to edit the code too, you can make changes and run them in the browser.

                If you want intellisense you can press Ctrl + Space.
                """.trimIndent().trimMargin()
    }
    runnableCode("""
        println("Hello, World!")
    """.trimIndent())
    annotatedCode(
            annotation = "There will be snippets with a green banner. They will have TODO() elements that need replaced.\n Try them out",
            code = """
                // Replace TODO with a nice message in double quotes
                val message: String = TODO()
                println(message)
            """, tryCode = true
    )
    markdown("Lets get Started, We will begin with describing a Kotlin file, [Click Here](/ktFiles)")
}