package app.content.basics

import app.playground
import react.RBuilder
import react.dom.code
import react.dom.h1
import react.dom.p

val literals: RBuilder.() -> Unit = {
    h1 { +"Literals"}
    p { +"In Kotlin xyz...dklfsdkfjdsjf" }
    code{
        attrs["auto-indent"] ="true"
        attrs["highlight-on-fly"] = "true"
        attrs["lines"] = "true"
        +("fun main(args: Array<String>){\n" +
                "//sampleStart val a = \"Hello\";\n" +
                "println(a);\n//sampleEnd\n" +
                "}")
    }
}
val functions: RBuilder.() -> Unit = {
    h1 { +"Functions"}
    p { +"In Kotlin xyz...dklfsdkfjdsjf" }
    code{
        +("fun main(args: Array<String>){\n" +
                "//sampleStart val a = \"Hello\";\n" +
                "println(a);\n//sampleEnd\n" +
                "}")
    }
}