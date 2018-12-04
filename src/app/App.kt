package app

import app.content.intro.arrow
import app.content.intro.coroutines
import app.content.intro.introduction
import app.content.intro.typesafeBuilders
import app.content.libraries.exposed
import app.content.libraries.http4k
import app.content.libraries.spring
import app.content.overview.classes
import app.content.overview.functions
import app.content.overview.ktFiles
import app.content.overview.variables
import linkedContent
import react.*
import react.dom.*
import kotlin.browser.window

class App : RComponent<RProps, RState>() {

    val contents = mapOf(
            "Introduction" to listOf(
                    ("/intro" to "Introduction") to introduction
            ),
            "Kotlin Language" to listOf(
                    ("/ktFiles" to "Kotlin Files") to ktFiles,
                    ("/variables" to "Variables") to variables,
                    ("/types" to "Types") to classes,
                    ("/functions" to "Functions") to functions
            ),
            "Libraries" to listOf(
                    ("/spring" to "Spring") to spring,
                    ("/http4k" to "Http4k") to http4k,
                    ("/exposed" to "Exposed") to exposed
            ),
            "Advanced" to listOf(
                    ("/coroutines" to "Coroutines") to coroutines,
                    ("/typesafeBuilders" to "Typesafe Builders") to typesafeBuilders,
                    ("/arrow" to "Arrow") to arrow
            )
    )

    override fun componentDidMount() {
        if(location() == "/") window.location.assign("/intro")
    }

    override fun RBuilder.render() {
        div("navigation"){
            div("navPadding"){}
            div("navBody") {
                object_("kotlin-logo") {
                    attrs.data = "/logo-text.svg"
                    attrs.type = "image/svg+xml"
                }
                div{
                    h1("navTitle") { +"Chris' Kotlin Course" }
                }
            }
            div("navPadding"){}
        }
        linkedContent(contents.map {
            (name, links) ->
            SidebarLink(name, links.map {
                (linkName, component) -> SidebarLeaf(linkName.second, linkName.first, component)
            }, name == "Overview")
        }, "Get Started")
    }
}

fun RBuilder.app() = child(App::class) {}
