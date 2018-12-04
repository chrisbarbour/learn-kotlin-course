package app.content.overview

import Markdown
import react.RBuilder

val expressions: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
                # Expressions
                ...
                ## When
                ...
                ## if {} else if {} else {}
                ...
                ## Try Catch Throw
                ...

                """.trimIndent().trimMargin()
    }
}