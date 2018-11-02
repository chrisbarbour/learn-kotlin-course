package app

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*


class Sidebar : RComponent<Sidebar.Props, RState>() {
    data class Props(var links: List<SidebarLink>): RProps

    override fun RBuilder.render() {
        div(classes = "sidebar") {
            props.links.forEach {
                sidebarLink(it.name, it.links)
            }
        }
    }
}

data class SidebarLink(var name: String, var links: List<SidebarLeaf> = emptyList())
data class SidebarLeaf(var name: String)

class SidebarLinkComponent: RComponent<SidebarLinkComponent.Props, SidebarLinkComponent.State>(){
    data class Props(var name: String, var links: List<SidebarLeaf>): RProps
    data class State(var open: Boolean): RState

    override fun componentDidMount() {
        setState{
            open = false
        }
    }

    override fun RBuilder.render() {
        div(classes = "sidebar-link" + if(!state.open) " closed" else " open") {
            p("sidebar-text") {
                +props.name
                attrs.onClickFunction = { setState{ open = !state.open } }
            }
        }
        if(state.open) {
            props.links.forEach {
                sidebarLeaf(it.name)
            }
        }
    }
}
class SidebarLeafComponent: RComponent<SidebarLeafComponent.Props, RState>(){
    data class Props(var name: String): RProps

    override fun RBuilder.render() {
        div(classes = "sidebar-leaf") {
            p("sidebar-text") {
                +props.name
                // attrs.onClickFunction = {  }
            }
        }
    }
}

fun RBuilder.sidebar(links: List<SidebarLink>) = child(Sidebar::class) {
    attrs.links = links
}
private fun RBuilder.sidebarLink(name: String, links: List<SidebarLeaf>) = child(SidebarLinkComponent::class) {
    attrs.name = name
    attrs.links = links
}
private fun RBuilder.sidebarLeaf(name: String) = child(SidebarLeafComponent::class) {
    attrs.name = name
}
