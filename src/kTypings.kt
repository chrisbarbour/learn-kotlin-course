import react.RClass
import react.RProps

external interface MarkdownProps: RProps{
    var source: String
}
@JsModule("react-markdown")
external val Markdown: RClass<MarkdownProps>