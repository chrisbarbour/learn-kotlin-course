package app.content.overview

import app.annotatedCode
import app.divider
import app.readOnlyCode
import app.runnableCode
import markdown
import react.RBuilder
import react.dom.code
import react.dom.textArea


val parameters: RBuilder.() -> Unit = {
    markdown("# Function Parameters")
    annotatedCode(
            annotation = """
                Declaring Parameters follows this format

                > fun myFunction(name: Type, name2: Type)
            """,
            code = """
                //Example functions
                fun foo(bar: String){}
                fun foo2(bar: String, baz: Int){}
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                ## Default Values

                Any parameters can be assigned default values. If not passed then the default value will be assigned.

            """,
            code = """
                fun log(message: String = "Hello, World!") = println(message)

                log() // message will be defaulted
                log("Something else")
            """
    )
    annotatedCode(
            annotation = """
                ## Named Parameters

                When calling functions you can add more context by supplying the name of the parameter you want to set.

                This also allows you to reorder parameters for readability.

                > Note: Unnamed parameters cannot proceed named parameters
            """,
            code = """
                fun log(message: String, severity: String = "INFO") = println("${'$'}severity: ${'$'}message")

                log("Hello") // standard call
                log(message = "Hello")
                log("World", severity = "WARN") // Unnamed parameters can come first if they are ordered correctly
                log(message = "World", "WARN") // Cannot supply unnamed parameter after named
                log(severity = "ERROR", message = "Ahhhh!") // You can reorder named parameters
            """
    )
    annotatedCode(
            annotation = """
                ## When the Last Parameter is a Function

                A special feature of kotlin appears when a function has a final parameter that is itself a function (see 'Function Types' below)

                When this case occurs you can invoke the function in a new way shown below
            """,
            code = """
                 // last parameter (modified) is a function
                fun doSomethingTo(string: String, modified: (String) -> String): String {
                    // All this does is pass the string to the modifyFunction and return the result
                    return modified(string)
                }

                // This is a function being stored as a variable (see 'Function Types' below)
                val myModifyFunction: (String) -> String = { string -> "${'$'}string from the other side" }
                // Normal invocation passing function as a variable
                val modifiedString = doSomethingTo("Hello", myModifyFunction)
                println(modifiedString)

                // This has the same result, note that the function is like the body of the line
                val modifiedString2 = doSomethingTo("Hello"){ string -> "${'$'}string from the other side" }
                println(modifiedString2)
                // This is also equivalent
                val modifiedString3 = doSomethingTo("Hello", { string -> "${'$'}string from the other side" })
                println(modifiedString3)
            """
    )
    annotatedCode(
            annotation = """
                ## Vararg

                You can specify that a parameter represents mutliple values using the **vararg** keyword.

                > A vararg parameter does not have to be the last parameter
                >
                > However you may only use a single one in any function

                Vararg parameters appear as Array<T> where T is the type of the parameter (Int in the example below)
                > When the type is a primitive type then a special Array of that type will be used. In this example its an IntArray
            """,
            code = """
                fun abc(a: String, vararg b: Int){
                |    print(a)
                |    b.forEach { print(it) }
                }
                abc("ABC", 1, 2, 3)
            """
    )
    annotatedCode(
            annotation = """
                ### The Spread Operator

                When a function requires a vararg parameter you can also supply an array of the correct type.

                To make it comply to the vararg parameter you need the spread operator *
            """,
            code = """
                fun abc(a: String, vararg b: Int){
                |    print(a)
                |    b.forEach { print(it) }
                }
                val integerValues = listOf(1,2,3).toIntArray() // Coverts to IntArray
                abc("ABC", *integerValues) // Spreads into varargs parameter
            """
    )
}

val returnTypes: RBuilder.() -> Unit = {
    markdown("## Return Types")
    annotatedCode(
            annotation = "The return type of a function is declared at the end of the parameter brackets following a colon **:**",
            code = "fun myFunction(): String  { ... } // <- This is the return type (String)", readOnly = true
    )
    annotatedCode(
            annotation = "If the return type is not provided the function will return Unit (See [Types](/types))",
            code = "fun myFunction()  { ... } // <- This function's return type is Unit", readOnly = true
    )
    markdown("> You do not have to specify that a function throws exceptions")
    markdown("Exceptions can be thrown from any function because throw statements result in the Nothing type (See [Types](/types))")
}


val memberFunctions: RBuilder.() -> Unit = {
    markdown("## Member Functions")
    annotatedCode(
            annotation = "A function is called a member function when it is a member of a class",
            code = """
                data class Car(val color: String){
                |   fun honkHorn() = println("Honk")
                }
            """, readOnly = true
    )
    markdown("### Overriding Functions")
    annotatedCode(
            annotation = """
                All functions are final by default.

                A function can be overwritten using the **override** keyword.

                To make a function overwrittable use the **open** keyword.

                This is only possible if the super types function is either abstract or open
            """,
            code = """open class Car(val color: String){
                |   open fun drive() = print("Driving")
                }
                class FastCar: Car("red"){
                   |override fun drive(){
                   |     super.drive()  // Use super to access super type
                   |     print(" Fast")
                   |}
                }
                Car("blue").drive()
                println()
                FastCar().drive()
            """
    )
}

val overloadingFunctions: RBuilder.() -> Unit = {
    markdown("## Overloading Functions")
}


val stdFunctions: RBuilder.() -> Unit = {
    markdown("## let, apply, also, run, with")
    markdown("""
                Kotlin comes with a standard library that includes lots of very useful functions.

                Here are a few definitions for functions that let you interact with state in a functional way.

                Each do the same thing but have different return types, receivers and parameters.

                They pass access to some state so you can modify safely or do some side effect.
            """.trimIndent())
    markdown("### let")
    markdown("> passes variable, returns what you return")
    readOnlyCode("inline fun <T, R> T.let(block: (T) -> R): R")
    runnableCode("""
                val fooBar = "Foo".let { "${'$'}it Bar" }
                println(fooBar)
            """.trimIndent()
    )

    markdown("### apply")
    markdown("> passes variable as receiver, returns same variable")
    readOnlyCode("inline fun <T> T.apply(block: T.() -> Unit): T")
    runnableCode("""
                class Car(val color: String, var topSpeed: Int = 0)
                val myCar = Car("red").apply { topSpeed = 200 } // Used to modify (useful for java types)
                println(myCar)
            """.trimIndent()
    )

    markdown("### also")
    markdown("> passes variable, returns same variable")
    readOnlyCode("fun <T> T.also(block: (T) -> Unit): T")
    runnableCode("""
                class Car(val color: String, var topSpeed: Int = 0)
                val myCar = Car("red").also { it.topSpeed = 200 } // Used to modify (useful for java types)
                println(myCar)
            """.trimIndent()
    )

    markdown("### run")
    markdown("> passes variable as receiver, returns what you return.")
    readOnlyCode("inline fun <T, R> T.run(block: T.() -> R): R")
    runnableCode("""
                data class Car(val color: String, var topSpeed: Int = 100){
                    fun paintCar(color: String) = Car(color, topSpeed)
                }
                val paintedCar = Car("red").run { paintCar("blue") } // Used to run something
                println(paintedCar)
            """.trimIndent()
    )

    markdown("### with")
    markdown("> passes variable as receiver, returns what you return.")
    markdown("This is the same as run but is a global method rather than an extension method on type T")
    readOnlyCode("fun <T, R> with(receiver: T, block: T.() -> R): R")
    runnableCode("""
                 data class Car(val color: String, var topSpeed: Int = 100){
                    fun paintCar(color: String) = Car(color, topSpeed)
                }
                val paintedCar = with(Car("red")) { paintCar("blue") }
                println(paintedCar)
            """.trimIndent()
    )
}

val extensionFunctions: RBuilder.() -> Unit = {
    markdown("## Extension Functions")
    annotatedCode(
            annotation = """
                Kotlin has a concept called extension functions that lets you externally extend the functionality of a type by adding a function to it.
            """,
            code = """
                // Here, we are adding a function called randomizeCase to String
                fun String.randomizeCase() = map { if(Math.random() > 0.5) it.toUpperCase() else it.toLowerCase() }.joinToString("")
                println("Hello, World!".randomizeCase())
                println("Hello, World!".randomizeCase())
                println("Hello, World!".randomizeCase())

                // You can also do this using Generics
                fun <T> T.print() = println(this) // This applies to all types
                "Foo Bar".print()
            """
    )
    markdown("> The type you are extending is known as the receiver of that function")
}

val receiverTypes: RBuilder.() -> Unit = {
    markdown("## Receivers")
    annotatedCode(
            annotation = """
                Functions in Kotlin have a concept called a receiver. This is assigned to the **this** keyword when in the scope of the function body.

                Normally this presents itself when in the context of a function that is a member of a class
            """,
            code = """
                data class Car(val color: String = "red"){
                    fun drive(){
                        println("${'$'}this is Driving") // The 'this' here is the Car instance (The Car is the receiver)
                    }
                }
                Car().drive()
            """
    )
    annotatedCode(
            annotation = """
                This is the same example as above but using an extension method

                > fun Car.drive() declares Car explicitly as the receiver
            """,
            code = """
                data class Car(val color: String = "red")
                fun Car.drive(){
                    println("${'$'}this is Driving") // The 'this' here is still the Car instance
                }
                Car().drive()
            """
    )
    annotatedCode(
            annotation = """
                This gets interesting when you create an extension function inside a class.

                Now you have two layers of receivers.
            """,
            code = """
                data class Car(val color: String = "red")
                data class Driver(val name: String = "Bob"){
                    fun Car.drive(){
                        println("${'$'}this is Driving") // The 'this' here is still the Car instance
                        println("We now also have access to the instance of Driver")
                        println(this@Driver)
                    }
                }

                with(Driver()){ // <- This gives us a Driver in scope
                    Car().drive() // We can now call drive on Car because we have both a Driver and a Car
                }
            """
    )
    markdown("> This layering of receivers can be used to specify that an instance must be in scope")
}

val infix: RBuilder.() -> Unit = {
    markdown("## Infix Functions")
    annotatedCode(
            annotation = """
                The **infix** keyword applied to a function allow you to omit the . and () for readability (optionally).

                This is how operators work.

                > Infix functions must be a member function or and extension function

                > Infix functions must have a single value parameter
            """,
            code = """
                infix fun Int.dot(b: Int) = this * b

                println(5 dot 4) // Now you don't need the . and brackets
                println(5.dot(4)) // Still works
            """
    )
}

val genericFunctions: RBuilder.() -> Unit = {
    markdown("## Generic Functions")
    annotatedCode(
            annotation = "A function can use generic parameters as follows",
            code = "fun <A,B> myFunction(a: A): B  { ... } ", readOnly = true
    )
    annotatedCode(
            annotation = "",
            code = """
                data class MyCollection<T>(val items: MutableList<T> = mutableListOf()){
                    fun add(item: T) = items.add(item)
                    fun get(index: Int): T = items.get(index)
                }
                val myStringCollection = MyCollection<String>()
                val myIntCollection = MyCollection<Int>()
                myStringCollection.add("One")
                myIntCollection.add(1)
                println(myStringCollection)
                println(myIntCollection)
            """
    )
}

val functionScopes: RBuilder.() -> Unit = {
    markdown("## Function Scope")
}

val functionTypes: RBuilder.() -> Unit = {
    markdown("## Function Types")
    annotatedCode(
            annotation = """
                In Kotlin functions are first class citizens. This means there is a defined type for any given function and its value can be stored in a variable.

                You can access a reference to a function using the **::** operator
            """,
            code = """
                fun myFunction(){}
                val refToMyFunction = ::myFunction
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                Another way of getting a reference to a function is to create it as a **lambda**

                ### Lambda Functions

                A Lambda function is just a function but it is defined without the fun keyword as follows.
            """,
            code = """
                fun myFunction(){ }
                val myLambda = { } // This is equivalent to the above line

                // This is a function that takes two arguments, a and b and returns a string (a + b)
                fun functionWithArgs(a: String, b: Int) = a + b
                val lambdaWithArgs = { a: String, b: Int -> a + b } // This is equivalent to the above line
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                Functions themselves also have types.
            """,
            code = """
                val myLambda: () -> Unit = { } // The type of this lambda is () -> Unit

                // Here the type is (String) -> Int
                val myLambdaWithArgs: (String) -> Int = { a: String -> a.toInt() }
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                This means they can be passed as arguments
            """,
            code = """
                // This function expects a function as its first parameter (runThis)
                fun higherOrderFunc(runThis: () -> String) = "A " + runThis()

                // We can pass it a lambda expression, or a reference to a function, or an anonymous function
                // Note: The braces have moved because its the last argument. See 'When the Last Parameter is a Function' above
                println(higherOrderFunc { "B" })
            """
    )
    annotatedCode(
            annotation = """
                What does the lambda version of that look like ??
            """,
            code = """
               // Here is the original again
               fun higherOrderFunc(runThis: () -> String) = "A " + runThis()

               // In lambda form it looks like this
               val higherOrderLambda = { runThis: () -> String -> "A " + runThis() }

               // What is the Type of those functions ?

               val higherOrderLambda2: (() -> String) -> String = { runThis: () -> String -> "A " + runThis() }

               println(higherOrderFunc { "B" })
               println(higherOrderLambda { "C" })
               println(higherOrderLambda2 { "D" })
            """
    )
    annotatedCode(
            annotation = """
                ### it
                If a lambda expression only takes a single argument then you can simply use the **it** keyword without specifying the parameter.
            """,
            code = """
                val logWithoutIt: (String) -> Unit = { message -> println(message) }
                val log: (String) -> Unit = { println(it) } // This is equivalent to the above line
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                ### _
                When a lambda expression does not need a parameter it can replace its name with an underscore
            """,
            code = """
                val log: (String, String) -> Unit = { message, _ -> println(message) }
            """, readOnly = true
    )
}

val currying: RBuilder.() -> Unit = {
    markdown("## Currying")
    annotatedCode("""
                Currying is when a function with n number of arguments is deconstructed into n functions linearly combined.

                Functions are easier to reason about when they take a single argument. Currying deconstructs any function to be exactly this.

                Currying decomposes a function so that it can be partially applied later.
            """,
            code = """
                // The following function
                val a: (String, String) -> String = { a, b -> a + b }
                // Currying this makes the following
                val c: (String) -> (String) -> String = { a -> { b -> a + b } }

                println(a("One", "Two"))
                println(c("One")("Two"))
            """
    )
}

val functions: RBuilder.() -> Unit = {
    annotatedCode(
            annotation = """
                # Functions
                Functions in Kotlin are first class citizens and can be declared anywhere, including outside of any classes.

                They can be declared using the **fun** keyword as follows:
            """,
            code = """
                fun log(log: String) = println(log)
                log("Hello, World!")
            """
    )
    annotatedCode(
            annotation = """
                Functions can have implicit return types when using the = form.

                The following are equivalent
            """,
            code = """
                fun doSomething() = "Foo Bar"
                fun doSomething2(): String = "Foo Bar"
                fun doSomething3(): String { return "Foo Bar" }
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                Calling functions looks like this:
            """,
            code = """
                fun text() = "Foo Bar"
                println(text())
            """
    )
    divider()
    parameters()
    divider()
    returnTypes()
    divider()
    overloadingFunctions()
    divider()
    memberFunctions()
    divider()
    genericFunctions()
    divider()
    functionScopes()
    divider()
    functionTypes()
    divider()
    currying()
    divider()
    extensionFunctions()
    divider()
    receiverTypes()
    divider()
    stdFunctions()
    divider()
    infix()
    divider()
    markdown("## Operators")
    markdown("## Invoke Function")
    markdown("## Tail Recursion")
    markdown("## Inline")
}