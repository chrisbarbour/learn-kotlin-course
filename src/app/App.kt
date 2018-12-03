package app

import app.content.basics.getStarted
import app.content.basics.functions
import app.content.intro.introduction
import app.content.overview.welcome
import linkedContent
import react.*
import react.dom.*


class App : RComponent<RProps, RState>() {

    val contents = mapOf(
            "Introduction" to listOf(
                    ("intro" to "Introduction") to introduction
            ),"Introduction2" to listOf(
            ("intro2" to "Introduction2") to introduction
    ),"Introduction3" to listOf(
            ("intro3" to "Introduction3") to introduction
    ),"Introduction4" to listOf(
            ("intro4" to "Introduction4") to introduction
    ),"Introduction5" to listOf(
            ("intro5" to "Introduction5") to introduction
    ),
        "Fundamentals" to listOf(
                ("getStarted" to "Get Started") to getStarted,
                ("functions" to "Functions") to functions
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
