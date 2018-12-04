package app.content.intro

import Markdown
import react.RBuilder
import react.dom.code
import react.dom.div
import react.dom.img

val typesafeBuilders: RBuilder.() -> Unit = {
    Markdown { attrs.source = """
        # Typesafe Builders
        ```kotlin
        fun main(){
        //sampleStart
        |   fun build(builder: String.()->Unit){
        |        "doSomethingWithThisString"()
        |    }
        |   build{
        |       println(this)
        |   }
        //sampleEnd
        }
        ```
    """.trimIndent().trimMargin() }
}