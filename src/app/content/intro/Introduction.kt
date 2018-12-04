package app.content.intro

import Markdown
import react.RBuilder
import react.dom.code
import react.dom.div
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
                # What you need to know for this course
                ...
                # Course Content
                ...
                ...
                """.trimIndent().trimMargin()
    }
    Markdown {
        attrs.source = """
                # What is Kotlin?
                ...
                ## JVM, JS, Native
                ...
                """.trimIndent().trimMargin()
    }
    code {
        attrs["auto-indent"] ="true"
        attrs["match-brackets"] ="true"
        attrs["lines"] = "true"
        +"""
            fun main(){
                println("Hello, World!")
            }
        """.trimIndent()
    }
    Markdown {
        attrs.source = """
                # Procedural vs Object Oriented vs Functional
                ...
                """.trimIndent().trimMargin()
    }

}