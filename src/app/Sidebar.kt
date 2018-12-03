package app

import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import kotlin.browser.window


fun location() = window.location.pathname

class Sidebar : RComponent<Sidebar.Props, RState>() {
    data class Props(var links: List<SidebarLink>, var linkSelected: (SidebarLeaf) -> Unit): RProps

    override fun componentDidUpdate(prevProps: Props, prevState: RState, snapshot: Any) {
       props.links.find { it.links.find { it.location == location() } != null }?.let {
            if(!it.open) {
                it.open = true
                setState {  }
                props.linkSelected(it.links.find { it.location == location() }!!)
            }
        }
    }

    fun linkClicked(link: String){
        props.links.find { it.name ==  link }?.apply {
            open = !open
        }
        setState {  }
    }

    override fun RBuilder.render() {
        div(classes = "sidebar") {
            props.links.forEach {
                sidebarLink(it.name,it.open, it.links, props.linkSelected, ::linkClicked)
            }
        }
    }
}

data class SidebarLink(var name: String, var links: List<SidebarLeaf> = emptyList(), var open: Boolean = false)
data class SidebarLeaf(var name: String, var location: String, var render: RBuilder.()->Unit)

class SidebarLinkComponent: RComponent<SidebarLinkComponent.Props, SidebarLinkComponent.State>(){
    data class Props(
            var name: String,
            var open: Boolean,
            var links: List<SidebarLeaf>,
            var onSelection: (SidebarLeaf) -> Unit,
            var linkClicked: (String) -> Unit
    ): RProps
    data class State(var open: Boolean): RState

    override fun componentDidMount() {
        setState {
            open = props.open
        }
    }

    override fun componentDidUpdate(prevProps: Props, prevState: State, snapshot: Any) {
        if(state.open != props.open) {
            setState {
                open = props.open
            }
        }
    }

    override fun RBuilder.render() {
        div(classes = "sidebar-link" + if(!state.open) " closed" else " open") {
            p("sidebar-text") {
                +props.name
                attrs.onClickFunction = { props.linkClicked(props.name) }
            }
        }
        if(state.open) {
            props.links.forEach { leaf -> sidebarLeaf(leaf.name, leaf.location) { props.onSelection(leaf) } }
        }
    }
}
class SidebarLeafComponent: RComponent<SidebarLeafComponent.Props, SidebarLeafComponent.State>(){
    data class Props(var name: String, var location: String, var onSelection: () -> Unit): RProps
    data class State(var selected: Boolean): RState

    override fun componentDidMount() {
        val isSelected = location() == props.location
        if(state.selected != isSelected) {
            setState { selected = isSelected }
        }
    }

    override fun componentWillUpdate(nextProps: Props, nextState: State) {
        val isSelected = location() == props.location
        if(state.selected != isSelected) {
            setState { selected = isSelected }
        }
    }

    override fun RBuilder.render() {
        div(classes = "sidebar-leaf") {
            p("sidebar-text" + if(state.selected) " sidebar-text-selected" else "") {
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
private fun RBuilder.sidebarLink(name: String, open: Boolean = false, links: List<SidebarLeaf>, onSelection: (SidebarLeaf) -> Unit, linkSelected: (String) -> Unit) = child(SidebarLinkComponent::class) {
    attrs.name = name
    attrs.open = open
    attrs.links = links
    attrs.onSelection = onSelection
    attrs.linkClicked = linkSelected
}
private fun RBuilder.sidebarLeaf(name: String, location: String, onSelection: () -> Unit) = child(SidebarLeafComponent::class) {
    attrs.name = name
    attrs.location = location
    attrs.onSelection = onSelection
}
