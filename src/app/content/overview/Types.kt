package app.content.overview

import Markdown
import app.annotatedCode
import app.divider
import markdown
import react.RBuilder
import react.dom.code

val types: RBuilder.() -> Unit = {
    markdown("# Classes and Types")
    classes()
    divider()
    inheritance()
    divider()
    interfaces()
    divider()
    multipleInheritance()

}

val classes: RBuilder.() -> Unit = {
    markdown("## Classes")
    annotatedCode(
            annotation = """Like Java Classes, Kotlin classes are declared using keyword class.
      | Implementation is optional and carried out inside braces
    """.trimMargin(),
            code = """class Foo {
      | fun baz(): String = "baz"
      | }
      |
      | class Bar
      | val foo = Foo()
      | println(foo.baz())
      | val bar = Bar()
      | println(bar)
      |
    """.trimMargin())
    markdown("### Constructor and init")
    annotatedCode(
            annotation = """An init block is called on initialization of a class
      |
    """.trimMargin(),
            code = """
      | class Car(colour: String, make: String, model: String, yearOfReg: Int) {
      | val modelAndMake ="${'$'}make - ${'$'}model"
      | val age: Int // must be initialised here or in init block
      |
      | init {
      |   age = 2018 - yearOfReg
      | }
      | }
      |
      | val fiesta = Car("Red", "Ford", "Fiesta", 1990)
      | println(fiesta.age)
      | println(fiesta.modelAndMake)
      |
    """.trimIndent())
    markdown("### Primary Constructors")
    annotatedCode(
            annotation = """Initialization can also be carried out using the primary constructor,
      | properties must be prefixed by val/var
    """.trimMargin(),
            code = """
      | class Car(val colour: String, val make: String, val model: String, val yearOfReg: Int) {
      | val modelAndMake ="${'$'}make - ${'$'}model"
      | }
      |
      | val fiesta = Car("Red", "Ford", "Fiesta", 1990)
      | println(fiesta.make)
      | println(fiesta.modelAndMake)
      |
    """.trimIndent())

    markdown("### Overloading Constructors")
    annotatedCode(
            annotation = """The constructor can be overloaded using the constructor keyword
    """.trimMargin(),
            code = """
      | class Car(val colour: String, val modelAndMake: String, val yearOfReg: Int) {
      | constructor(colour: String, make: String, model: String, yearOfReg: Int):
      |   this(colour, "${'$'}make - ${'$'}model",  yearOfReg)
      | }
      |
      | val fiesta = Car("Red", "Ford", "Fiesta", 1990)
      | println(fiesta.modelAndMake)
      | val escort = Car("Blue", "Ford Escort", 1995)
      | println(escort.modelAndMake)
    """.trimIndent())

}
val inheritance: RBuilder.() -> Unit = {
    markdown("## Inheritance")
    markdown("""Kotlin makes classes final by default to avoid ad hoc and messy hierarchies
  """.trimIndent())
    markdown("""The open keyword can be used to allow a class and their methods to be extended""")
    code {
        attrs["lines"] = "true"
        attrs["auto-indent"] = "true"
        attrs["highlight-on-fly"] = "true"
        +"""
      | open class Foo(val i: Int) {
      |   open fun baz(): String = "foo baz"
      | }
      | class Bar(j: Int): Foo(j) {
      |   override fun baz(): String = "bar bar"
      |  }
      | fun main() {
      |   val bar = Bar(5)
      |   val foo = Foo(3)
      |
      |   println(bar.baz())
      |   println(bar.i)
      |   println(foo.baz())
      |   println(foo.i)
      | }
      |
      """.trimMargin()
    }
}

val interfaces: RBuilder.() -> Unit = {
    markdown("## Interfaces")
    markdown("""Interfaces follow a similar convention to Java interfaces post version 8,
    this means default methods (fun) can have an implementation
    note: vals can not be implemented in an interface """)
    interfaceCodeBlock1()
    markdown("Unlike Java, implementing an interface uses the same notation as extending " +
            "an abstract class")
}
val interfaceCodeBlock1: RBuilder.() -> Unit = {
    code {
        attrs["lines"] = "true"
        attrs["auto-indent"] = "true"
        attrs["highlight-on-fly"] = "true"
        +"""
      | interface Foo {
      | fun baz(): String = "baz"
      | }
      |
      | interface Bar {
      |   val bop: Int
      |   fun baz(): String
      | }
      | class FooImpl: Foo
      | class BarImpl: Bar {
      |   override val bop:Int = 1
      |   override fun baz(): String = "barImpl baz"
      | }
      |
      | fun main() {
      | val foo = FooImpl()
      | val bar = BarImpl()
      |
      | println(foo.baz())
      | println(bar.baz())
      | }
      """.trimMargin()
    }
}
val multipleInheritance: RBuilder.() -> Unit = {
    markdown("### Multiple inheritance ")
    markdown("""Kotlin allows inheritance from multiple via interfaces and open/abstract classes,
    | in the case of a clash when multiple parent classes have the same fun defined, the Kotlin compiler will
    | defer implementation to the concrete class
  """.trimMargin())
    multipleInheritanceCodeBlock()
}
val multipleInheritanceCodeBlock: RBuilder.() -> Unit = {
    code {
        attrs["lines"] = "true"
        attrs["auto-indent"] = "true"
        attrs["highlight-on-fly"] = "true"
        +"""
      | interface Foo {
      | fun foo(): String = "foo"
      | fun baz(): String = "baz"
      | }
      |
      | interface Bar {
      |   fun bar(): String = "bar"
      |   fun baz(): String
      | }
      | class BazImpl: Bar, Foo {
      |  // override fun baz(): String = "barImpl baz"
      | }
      |
      | fun main() {
      | val baz = BazImpl()
      | println(baz.foo())
      | println(baz.bar())
      | println(baz.baz())
      | }
      """.trimMargin()
        //"fun main(){\n//sampleStart\n //sampleEnd\n}"
    }
}
/**
# Classes and Types
...
## Classes
...
## Data Classes
...
## Interfaces
...
# Inheritance
...
## Kotlin Type Tree
...
## Nothing
... Talk about Nothing extending Everything including Unit
## Generics
...
### Reified
...
## Enum Classes
...
## Sealed Classes
...
## Objects and Companions (Singletons)
...
## Type Aliases
...
## Singletons
 **/