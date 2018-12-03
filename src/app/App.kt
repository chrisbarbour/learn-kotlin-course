package app

import app.content.intro.introduction
import app.content.other.other
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
                    ("/classes" to "Classes") to classes,
                    ("/functions" to "Functions") to functions
            ),
            "Other" to listOf(
                    ("/other" to "Other") to other //TODO
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
