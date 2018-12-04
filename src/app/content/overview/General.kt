package app.content.overview

import Markdown
import react.RBuilder

val general: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
                # General Idioms
                ...
                ## String Interpolation
                ...
                ## Runtime Instance Checking
                ...
                ## Ranges
                ...
                ## Null ?!!
                ...
                ## Destructuring
                ...
                ## Yada Yada Yada...
                """.trimIndent().trimMargin()
    }
}