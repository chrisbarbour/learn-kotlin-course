package app.content.intro

import Markdown
import react.RBuilder
import react.dom.code
import react.dom.div
import react.dom.img

val arrow: RBuilder.() -> Unit = {
    Markdown { attrs.source = """
        # Introduction to Arrow
        ```
    """.trimIndent().trimMargin() }
}