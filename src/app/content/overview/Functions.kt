package app.content.overview

import app.annotatedCode
import app.divider
import markdown
import react.RBuilder

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
                val integerValues = arrayOf(1,2,3).toTypedArray() // Coverts to IntArray
                abc("ABC", *integerValues) // Spreads into varargs parameter
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
    markdown("## Return Types")
    markdown("## Member Functions")
    markdown("## Generic Functions")
    markdown("## Function Scope")
    markdown("## Function Types")
    markdown("## let, apply, run, also, with ...")
    markdown("## Infix")
    markdown("## Operators")
    markdown("## Invoke Function")
    markdown("## Extension Functions")
    markdown("## Tail Recursion")
    markdown("## Receiver Types")
    markdown("## Inline")
    markdown("## this")
    markdown("## Higher Order Functions")
}