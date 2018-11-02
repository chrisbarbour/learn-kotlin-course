import app.*
import react.*
import react.dom.*
import kotlin.browser.window


class LinkedContent : RComponent<LinkedContent.Props, LinkedContent.State>() {
    data class Props(var links: List<SidebarLink>, var default: SidebarLeaf): RProps
    data class State(var selected: SidebarLeaf? = null, var mounted: Boolean?): RState

    override fun componentDidMount() {
        playground("code")
        window.setTimeout({setState { mounted = true }}, 1000)
    }

    private fun linkSelected(leaf: SidebarLeaf){
        setState{  selected = leaf }
    }

    override fun RBuilder.render() {
        div("container") {
            div("contents") {
                sidebar(props.links, this@LinkedContent::linkSelected)
                div {
                props.links.flatMap { it.links }.forEach { link ->
                    val hidden = if(state.mounted == true)" hidden-not-displayed" else " hidden"
                        div("block" + if (link.name != (state.selected ?: props.default).name) hidden else "") {
                            link.render(this)
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.linkedContent(links: List<SidebarLink>, default: SidebarLeaf) = child(LinkedContent::class) { attrs.links = links; attrs.default = default }