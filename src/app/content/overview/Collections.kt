package app.content.overview

import Markdown
import react.RBuilder

val collections: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
                # Collections

                """.trimIndent().trimMargin()
    }
}