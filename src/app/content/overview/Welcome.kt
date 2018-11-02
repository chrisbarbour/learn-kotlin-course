package app.content.overview

import react.RBuilder
import react.dom.h1

val welcome: RBuilder.() -> Unit = {
    h1 { +"Welcome" }
}