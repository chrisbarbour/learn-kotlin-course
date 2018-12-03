package app.content.other

import Markdown
import react.RBuilder
import react.dom.code
import react.dom.div
import react.dom.img

val other: RBuilder.() -> Unit = {
    Markdown { attrs.source = "# Other" }

}