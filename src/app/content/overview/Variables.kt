package app.content.overview

import app.annotatedCode
import app.divider
import markdown
import react.RBuilder
val variables: RBuilder.() -> Unit = {

    markdown("# Values and Variables")
    annotatedCode("""
         ## Values (val)

         Used to create an immutable reference to an object
    """, """val fooBarBaz = "foo" + "bar" + "baz"""", readOnly = true)
    annotatedCode("""
         Types can be inferred explicitly or implicitly.

         To define a type explicitly place it after a colon beside the name of the variable.

    """, """
        val i = 1 //implicitly Typed as an Int
        val j: Int = 1 // explicitly an Int
    """, readOnly = true)
    annotatedCode("""
         ## Variables (var)
        Creates a mutable reference to an object
    """, """
        var fooBarBaz = "foo" + "bar" + "baz"
        fooBarBaz = "" // compiles
        val immutable = "foo"
        immutable = "" // Does not compile
    """, readOnly = true)
    annotatedCode("""
         Var type inference is the same as val
    """, """
        var i = 1 //implicitly Typed as an Int
        var j: Int = 1 // explicitly an Int
    """, readOnly = true)
    markdown("""
                ### Note: Val/Var only makes the reference not the object mutable/immutable
  """.trimIndent())
    annotatedCode(annotation = "", code = """
    //immutable reference to mutable object
    val list = arrayListOf(1,2,3,4)
    list.add(5)
    println(list)
  """.trimIndent())
    divider()
    markdown("# Expressions vs Statements")
    annotatedCode(
            annotation = """
      ## Expression
      An expression evaluates to a value
    """,
            code = """
      //if expression
      val expr: String = if(1 < 2) {
      "foo"
      } else {
      "bar"
      }
      println(expr)

      //try expression
      val tryExpr: Int  = try {
        "foo".toInt()
      } catch(e: Exception) {
        7
      }
      println(tryExpr)

      //expressions within functions
      fun evalNum(i:Int): String = when (i) {
        0 -> "is zero"
        1 -> "is one"
        else -> "${'$'}i is not one or zero"
        }
      println(evalNum(0))
      println(evalNum(1))
      println(evalNum(57))
    """.trimIndent()
    )

    annotatedCode(
            annotation = """
      ## Statement
      A statement carries out an action
    """,
            code = """
      //if statement
      if(1 < 2) {
        println("foo")
      } else {
        println("bar")
      }

      //try statement
      try {
        val f = "foo".toInt()
        println(f)
      } catch(e: Exception) {
        println(7)
      }

      //statement within functions
      fun evalNum(i:Int) = when (i) {
        0 -> println("is zero")
        1 -> println("is one")
        else -> println("${'$'}i is not one or zero")
        }
       evalNum(0)
       evalNum(1)
       evalNum(57)
    """.trimIndent()
    )

}