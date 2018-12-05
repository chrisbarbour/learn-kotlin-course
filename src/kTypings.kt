import react.RBuilder
import react.RClass
import react.RProps
import react.dom.mark

external interface MarkdownProps: RProps{
    var source: String
}
@JsModule("react-markdown")
external val Markdown: RClass<MarkdownProps>

fun RBuilder.markdown(markdown: String){
    Markdown { attrs.source = markdown }
}
