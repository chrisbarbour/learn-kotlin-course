package app

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*


class Sidebar : RComponent<Sidebar.Props, RState>() {
    data class Props(var links: List<SidebarLink>, var linkSelected: (SidebarLeaf) -> Unit): RProps

    override fun RBuilder.render() {
        div(classes = "sidebar") {
            props.links.forEach {
                sidebarLink(it.name, it.open, it.links, props.linkSelected)
            }
        }
    }
}

data class SidebarLink(var name: String, var links: List<SidebarLeaf> = emptyList(), var open: Boolean = false)
data class SidebarLeaf(var name: String, var render: RBuilder.()->Unit)

class SidebarLinkComponent: RComponent<SidebarLinkComponent.Props, SidebarLinkComponent.State>(){
    data class Props(var name: String, var open: Boolean, var links: List<SidebarLeaf>, var onSelection: (SidebarLeaf) -> Unit): RProps
    data class State(var open: Boolean): RState

    override fun componentDidMount() {
        setState{
            open = props.open
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
            props.links.forEach { leaf -> sidebarLeaf(leaf.name) { props.onSelection(leaf) } }
        }
    }
}
class SidebarLeafComponent: RComponent<SidebarLeafComponent.Props, RState>(){
    data class Props(var name: String, var onSelection: () -> Unit): RProps

    override fun RBuilder.render() {
        div(classes = "sidebar-leaf") {
            p("sidebar-text") {
                +props.name
                attrs.onClickFunction = { props.onSelection() }
            }
        }
    }
}

fun RBuilder.sidebar(links: List<SidebarLink>, linkSelected: (SidebarLeaf) -> Unit) = child(Sidebar::class) {
    attrs.links = links
    attrs.linkSelected = linkSelected
}
private fun RBuilder.sidebarLink(name: String, open: Boolean = false, links: List<SidebarLeaf>, onSelection: (SidebarLeaf) -> Unit) = child(SidebarLinkComponent::class) {
    attrs.name = name
    attrs.open = open
    attrs.links = links
    attrs.onSelection = onSelection
}
private fun RBuilder.sidebarLeaf(name: String, onSelection: () -> Unit) = child(SidebarLeafComponent::class) {
    attrs.name = name
    attrs.onSelection = onSelection
}
