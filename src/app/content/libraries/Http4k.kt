package app.content.libraries

import Markdown
import react.RBuilder

val http4k: RBuilder.() -> Unit = {
    Markdown { attrs.source = "# HTTP4K" }

}