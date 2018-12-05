package app

import app.content.advanced.arrow
import app.content.advanced.coroutines
import app.content.advanced.delegatedProperties
import app.content.advanced.typesafeBuilders
import app.content.intro.introduction
import app.content.libraries.exposed
import app.content.libraries.http4k
import app.content.libraries.spring
import app.content.overview.*
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
                    ("/idioms" to "Idioms") to general,
                    ("/types" to "Types") to types,
                    ("/functions" to "Functions") to functions,
                    ("/collections" to "Collections") to collections
            ),
            "Libraries" to listOf(
                    ("/spring" to "Spring") to spring,
                    ("/http4k" to "Http4k") to http4k,
                    ("/exposed" to "Exposed") to exposed
            ),
            "Advanced" to listOf(
                    ("/coroutines" to "Coroutines") to coroutines,
                    ("/typesafeBuilders" to "Typesafe Builders") to typesafeBuilders,
                    ("/delegatedProperties" to "Delegated Properties") to delegatedProperties,
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
                    h1("navTitle") { +"Kotlin Course" }
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
