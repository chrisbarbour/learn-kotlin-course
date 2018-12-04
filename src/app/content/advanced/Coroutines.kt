package app.content.intro

import Markdown
import react.RBuilder
import react.dom.code
import react.dom.div
import react.dom.img

val coroutines: RBuilder.() -> Unit = {
    Markdown { attrs.source = "# Coroutines" }
}