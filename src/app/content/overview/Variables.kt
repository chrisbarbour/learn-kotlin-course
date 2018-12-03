package app.content.overview

import Markdown
import react.RBuilder

val variables: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
                # Variables
                ...
                ## Use Val not Var
                ...
                """.trimIndent().trimMargin()
    }
}