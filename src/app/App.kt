package app

import app.content.basics.functions
import app.content.basics.literals
import app.content.overview.welcome
import linkedContent
import react.*
import react.dom.*
import logo.*
import ticker.*


class App : RComponent<RProps, RState>() {

    val contents = mapOf(
            "Overview" to listOf(
            "Welcome" to welcome
        ),
        "Basics" to listOf(
            "Literals" to literals,
            "Functions" to functions
        )
    )

    override fun RBuilder.render() {
        div("navigation"){
            div("navPadding"){}
            div("navBody") {
                object_("kotlin-logo") {
                    attrs.data = "/logo-text.svg"
                    attrs.type = "image/svg+xml"
                }
            }
            div("navPadding"){}
        }
        linkedContent(contents.map {
            (name, links) ->
            SidebarLink(name, links.map {
                (linkName, component) ->
                SidebarLeaf(linkName, component)
            }, name == "Overview")
        }, SidebarLeaf("Welcome"){})
    }
}

fun RBuilder.app() = child(App::class) {}
