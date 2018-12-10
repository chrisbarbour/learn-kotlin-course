package app.content.overview

import Markdown
import app.annotatedCode
import markdown
import react.RBuilder
import react.dom.img

val collections: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
                |   # Collections

                |   A collection is a class used to represent a set of similar items as a single unit. These classes are used for grouping and managing related objects.
                |
                |   Kotlin provides Collection Types and related functions in the Kotlin standard libraries kotlin.collections package.
                """.trimIndent().trimMargin()
    }
    basicCollectionTypes()
}

private val basicCollectionTypes: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                ## Basic Collection Types

                ### Array
                A container for holding a fixed number of entries of a given type. Arrays in Kotlin are represented by the Array class. To create an array, we can use a library function **arrayOf()**.
            """,
            code = """
                val array = arrayOf("Wolverine", "Punisher", "Thor")
                println(array)
            """
    )
    annotatedCode(
            annotation = """
                Kotlin also has specialized classes to represent arrays of primitive types without boxing overhead
            """,
            code = """
                val intArray: IntArray = intArrayOf(1, 2, 3)
                println(intArray)
            """
    )
    annotatedCode(
            annotation = """
                ### List
                An unorder collection that does not support duplicate entries
            """,
    code = """
               val mutable = mutableListOf("Batman", "Superman", "Wonder Woman")
               mutable.add("Aquaman")

               val immutable = listOf("Robin", "Raven", "Cyborg", "Starfire", "Beast Boy")
               // immutable.add("Storm")

               /* Uncommenting the above will cause a compiler error as
                immutable collections don't have the mutating functions */
            """
    )
    annotatedCode(
            annotation = """
                ### Set
                An unorder collection that does not support duplicate entries
            """,
            code = """
               |    val set = setOf("Spider-man", "Hawkman", "Arrow", "The Flash", "Arrow")
               |    println(set)
            """
    )
    markdown("""
        ### Collection Hierarchy
        Standard collections are collections from native language Java, which are hidden behind interfaces. Creation of them is made by standard top-level functions listOf, setOf, mutableListOf etc..
    """.trimIndent())
    img(src = "collection-hierarchy.png"){ attrs.width = "800px"}
}