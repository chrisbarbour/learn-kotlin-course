package app

import Markdown
import kotlinx.html.CODE
import markdown
import react.RBuilder
import react.dom.RDOMBuilder
import react.dom.code
import react.dom.div

fun RBuilder.readOnlyCode(code: String, builder: RDOMBuilder<CODE>.() -> Unit = {}){
    code {
        attrs["auto-indent"] = "false"
        attrs["data-highlight-only"] = "true"
        attrs["lines"] = "true"
        +code
        builder()
    }
}
fun RBuilder.runnableCode(code: String, inMain: Boolean = true, tryCode: Boolean = false, builder: RDOMBuilder<CODE>.() -> Unit = {}){
    div(classes = if (tryCode) "tryCode" else "") {
        code {
            attrs["lines"] = "true"
            attrs["highlight-on-fly"] = (!tryCode).toString()
            if (inMain) +"fun main(){\n//sampleStart\n$code //sampleEnd\n}"
            else +code
            builder()
        }
    }
}

fun RBuilder.annotatedCode(annotation: String, code: String, trim: Boolean = true, readOnly: Boolean = false, tryCode: Boolean = false){
    markdown(if(trim) annotation.trimIndent().trimMargin() else annotation)
    if(readOnly) readOnlyCode(if(trim) code.trimIndent().trimMargin() else code)
    else runnableCode(if(trim) code.trimIndent().trimMargin() else code, tryCode = tryCode)
}

fun RBuilder.divider() {
    div("section-divider-wrapper") {
        div("section-divider") {}
    }
}