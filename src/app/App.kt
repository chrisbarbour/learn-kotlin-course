package app

import react.*
import react.dom.*
import logo.*
import ticker.*


class App : RComponent<RProps, RState>() {
    override fun componentDidMount() {
        playground("code")
    }
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
        div("container") {
            div("contents") {
                sidebar(listOf(
                        SidebarLink("Basics", listOf(
                                SidebarLeaf("Literals"),
                                SidebarLeaf("Types")
                        ))
                        ))
                div("block") {
                    h1 { +"Literals"}
                    p { +"In Kotlin xyz...dklfsdkfjdsjf" }
                    code{
                        +("fun main(args: Array<String>){\n" +
                                "//sampleStart val a = \"Hello\";\n" +
                                "println(a);\n//sampleEnd\n" +
                                "}")
                    }
                }
            }
        }
    }
}

fun RBuilder.app() = child(App::class) {}
