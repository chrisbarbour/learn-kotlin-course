package app.content.libraries

import Markdown
import react.RBuilder

val exposed: RBuilder.() -> Unit = {
    Markdown { attrs.source = "# Exposed" }
}