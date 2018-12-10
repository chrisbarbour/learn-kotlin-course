package app.content.advanced

import app.runnableCode
import markdown
import react.RBuilder

val typesafeBuilders: RBuilder.() -> Unit = {
    markdown("# Typesafe Builders")
    runnableCode("""
        data class Engine(val size: Int){
            data class Builder(var size: Int = 1000){
                fun size(size: Int){ this.size = size }
                fun build() = Engine(size)
            }
        }
        data class Car(val engine: Engine, val color: String){
           data class Builder(var engine: Engine = Engine(1000), var color: String = "red"){
                fun engine(build: Engine.Builder.() -> Unit){ this.engine = Engine.Builder().apply(build).build() }
                fun color(color: String){ this.color = color }
                fun build() = Car(engine, color)
            }
        }
        fun car(builder: Car.Builder.() -> Unit) = Car.Builder().apply(builder).build()
        fun main(){
            val myCar = car {
                color("blue")
                engine {
                    size(2000)
                }
            }
            println(myCar)
        }
    """.trimIndent(), inMain = false)
}