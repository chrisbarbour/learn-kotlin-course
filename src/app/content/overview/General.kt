package app.content.overview

import app.annotatedCode
import app.divider
import app.readOnlyCode
import app.runnableCode
import kotlinx.html.CODE
import markdown
import react.RBuilder
import react.dom.RDOMBuilder
import react.dom.textArea

private val stringInterpolation: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                ## String Interpolation
                Strings can be built from various components by using the $ symbol inside quotes " "
            """,
            code = """
                val hello = "Hello"
                val world = " World!"
                println("${'$'}hello, World")
                println("You can also use \${'$'}{...} to execute code inline like this: ${'$'}{ hello + world }")
            """
    )
    annotatedCode(
            code = """
                // Print out the sum of the following two numbers using string interpolation
                // The result should read 'The sum of 100 and 20 is 120'
                val a = 100
                val b = 20
                TODO()
            """,
            tryCode = true
    )
}

private val runtimeInstanceChecking: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                ## Runtime Instance Checking
                To check the class of something at runtime using reflection you can use the special syntax ::class
            """,
            code = """
                val abc = "Hello"
                println(abc::class) // Gets a Kotlin KClass<T>
                println(abc::class.java) // Gets a Java Class<T>
            """
    )
    annotatedCode(code = """
        // Here is a list of items with different types
        // Print out the type of each element using runtime instance checking

        val items = listOf(1, "2", 3L, 4f, 5.0)
        // For example - you can use [] to get an item from an array
        val firstItem = items[0]
        TODO()
    """.trimIndent(), tryCode = true)
    annotatedCode(
            annotation = "You can also check if the type of something matches a specific type using the **is** keyword",
            code = """
                val abc: Any = "Hello" // Try removing the Any here
                println(abc is kotlin.String)
                println(abc is java.lang.String) // Don't do this (but will work if type is unknown at compile time)
                println(abc is Int)
            """
    )
}
private val ranges: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                ## Ranges
                A range of numbers can easily be created using the **..** operator
            """,
            code = """
                val range = 0..5
                val sameRange = 0.rangeTo(5) // rangeTo is the same as ..
                println(range.toList())
                println(sameRange.toList())
            """
    )

    annotatedCode(
            annotation = "",
            code = """
                // Print a range of numbers from 4 to 10
                val range: IntRange = TODO()
                println(range.toList())
            """, tryCode = true
    )
    annotatedCode(
            annotation = """
                To reverse the order use downTo
            """,
            code = """
                val range = 10.downTo(5)
                val sameRange = 10 downTo 5 // This is the infix notation
                println(range.toList())
                println(sameRange.toList())
            """
    )
    annotatedCode(
            annotation = """
                If you want to exclude the last number use until
            """,
            code = """
                val range = 4.until(10)
                val sameRange = 4 until 10 // This is the infix notation
                println(range.toList())
                println(sameRange.toList())
            """
    )
    annotatedCode(
            annotation = """
                Ranges also have a step property allowing you to skip values.

                > Step can only be a positive number.
            """,
            code = """
                val range = 4.until(10).step(3)
                val sameRange = 4 until 10 step 3 // Step is also infix
                println(range.toList())
                println(sameRange.toList())
            """
    )
    markdown("> Ranges are also iterable. This means you can iterate over them with all the standard iterable functions listed in [Collections](/collections)")
    annotatedCode(
            annotation = """
                Ranges also have a step property allowing you to skip values.

                > Step can only be a positive number.
            """,
            code = """
                val range = 4.until(10).step(3)
                val sameRange = 4 until 10 step 3 // Step is also infix
                println(range.toList())
                println(sameRange.toList())
            """
    )

    annotatedCode(
            code = """
                // Print all the odd numbers from 101 down to 9 using ranges
                TODO()
            """,
            tryCode = true
    )
}
private val nulls: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                # Null !! ?
                Kotlin has taken care of the null problem that arises in java where anything can be set to null.

                In Kotlin you must explicitly say that a type can be null if you want it to be possible.
                At runtime null pointer exceptions are still a possibility but you are now protected by the type system at compile time.
            """,
            code = """
                var foo = "a string" // this is implied to be a kotlin.String which can not be null
                foo = null // will not compile
            """
    )
    markdown("""
                ## Make something nullable (?)
                To make a type nullable add a ? to it. The compiler will make sure you do something about it.

                If you have this class defined
            """.trimIndent())
    val carClass = "data class Car(val color: String)"
    val myCar = "val myCar: Car? = Car(\"red\")"
    val hiddenCar: RDOMBuilder<CODE>.()->Unit = { textArea(classes = "hidden-dependency") { +"$carClass;$myCar" } }
    readOnlyCode(carClass)
    markdown("You can define a variable with a type of Car? meaning a nullable Car, like this")
    readOnlyCode(myCar)
    markdown("The compiler will not allow access the cars color property directly")
    runnableCode("println(myCar.color)"){ hiddenCar() }
    markdown("To get access to the cars color property there are a few options\n## Null Access")
    markdown("### 1. Safe Access (?)")
    markdown("By adding a question mark you can safely access the property. The result will be null if the accessed object is null")
    runnableCode("println(myCar?.color)"){ hiddenCar() }
    runnableCode("""
        // Print out the value of the blue component of the color of myCar

        data class Color(val red: Int?, val green: Int?, val blue: Int?)
        data class Car(val color: Color?)
        val myCar: Car? = Car(Color(255,100,20)) // A redish green car that could possibly be null even though it isn't
        TODO()
    """.trimIndent(), tryCode = true)
    markdown("### 2. Asserting Not Null (!!)")
    markdown("By adding a !! you can tell the compiler to stop worrying about it.")
    runnableCode("println(myCar!!.color)"){ hiddenCar() }
    markdown("If the result is null then a **KotlinNullPointerException** will be thrown")
    runnableCode("val myOtherCar: Car? = null\nprintln(myOtherCar!!.color) // This will throw a runtime KotlinNullPointerException"){ hiddenCar() }
    markdown("### 3. Compiler Hints")
    markdown("""Under some situations the compiler can guarantee safe use of null and will back off.
        The simplest example of this is the if expression. Inside an if block that checks for not null case the compiler is happy.
    """.trimIndent())
    runnableCode("""
        if(myCar != null){
        |    println(myCar.color) // Compiler accepts this
        }
        else {
        |    println(myCar.color) // Compiler rejects this
        }
        """.trimIndent().trimMargin()){ hiddenCar() }
    markdown("## Elvis Operator")
    markdown("You can also choose to use a nullable value if not null otherwise select a default value using the elvis operator")
    runnableCode("""
        val car: Car? = null
        val color = car?.color ?: "red" // car is null so result of car?.color is also null
        println(color)
    """.trimMargin()){ hiddenCar() }
}
private val destructuring: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                # Destructuring
                All types in Kotlin have functions that can be called to get components aside from their setters.

                This applies to classes and also arrays.

                They are name **component1()** **component2()** .. **componentN()**
            """,
            code = """
                data class Foo(val a: String = "a",val  b: Int = 5)
                val foo = Foo()
                println("${'$'}{foo.component2()} == ${'$'}{foo.b}")
            """
    )
    annotatedCode(
            annotation = """
                Because these generically named methods exist, basic destructuring is possible.

                This allows you to take parts of classes or arrays and store them into variables as shown below.
            """,
            code = """
                data class Car(val make: String,val model: String, val bodyTypes: List<String>)
                val nissan350z = Car("Nissan", "350z", listOf("Coupe", "Roadster"))

                val (make, model, bodyTypes) = nissan350z // Destructuring an Object
                val (typeA, typeB) = bodyTypes // Destructuring a List

                println("Make: ${'$'}make")
                println("Model: ${'$'}model")
                println("Body Type A: ${'$'}typeA")
                println("Body Type B: ${'$'}typeB")
            """
    )
    annotatedCode(
            annotation = """
                This is very nice when using iterable functions like the map function.
            """,
            code = """
                data class Car(val make: String,val model: String)

                val carInfo = listOf(
                    "Nissan" to "350z",
                    "Nissan" to "Micra"
                )

                val cars = carInfo.map { (make, model) -> Car(make, model) } // Destructures each Pair into make and model

                println(cars)
            """
    )
}

private val pairs: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                # Pair and Triple (Tuples)

                Kotlin does not have the notion of a tuple (although it used to). However it does have pairs and triples which allow 2 and 3 values to be stored without creating intermediary classes.

                A **Triple** simply stores 3 values (first, second and third).

                A **Pair** stores two (first and second).

                Pairs can be created either by constructing them as normal types or by using the **to** keyword.
            """,
            code = """
                val aPair = Pair("key", "value")
                val aTriple = Triple("first", "second", "third")
                val anotherPair = "key" to "value"

                println(anotherPair)
            """
    )
    annotatedCode(
            annotation = """
                Pairs can be extracted from keys and values in a Map and Maps can be constructed from lists of pairs.
            """,
            code = """
                val myMap: Map<String, Int> = mapOf("a" to 1, "b" to 2) // mapOf wants Pairs
                val pairs: List<Pair<String, Int>> = myMap.toList()
                val backToMap: Map<String, Int> = pairs.toMap()

                println("List of Pairs: ${'$'}pairs")
                println("Map: ${'$'}backToMap")
            """
    )
    runnableCode("""
        // Create a map of Car Makes and Models and print it out
        val makesAndModels = mapOf<String, String>(TODO())
        println(makesAndModels)
    """.trimIndent(), tryCode = true)
}

val general: RBuilder.() -> Unit = {
    markdown("# General Idioms")
    stringInterpolation()
    divider()
    runtimeInstanceChecking()
    divider()
    ranges()
    divider()
    nulls()
    divider()
    pairs()
    divider()
    destructuring()
}