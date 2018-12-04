package app.content.libraries

import Markdown
import react.RBuilder

val spring: RBuilder.() -> Unit = {
    Markdown { attrs.source = "# Spring" }

}