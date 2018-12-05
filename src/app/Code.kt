package app

import kotlinx.html.CODE
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
fun RBuilder.runnableCode(code: String, inMain: Boolean = true, builder: RDOMBuilder<CODE>.() -> Unit = {}){
    code {
        attrs["lines"] = "true"
        attrs["highlight-on-fly"] = "true"
        if(inMain) +"fun main(){\n//sampleStart\n$code //sampleEnd\n}"
        else +code
        builder()
    }
}

fun RBuilder.divider() {
    div("section-divider-wrapper") {
        div("section-divider") {}
    }
}