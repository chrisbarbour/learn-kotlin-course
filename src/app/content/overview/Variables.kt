package app.content.overview

import app.annotatedCode
import markdown
import react.RBuilder
import react.dom.img

val variables: RBuilder.() -> Unit = {

    markdown("# Values and Variable")
    annotatedCode(
            annotation = """
                ## Values
                Used to create an immutable reference to an object
                > val fooBarBaz = "foo" + "bar" + "baz"

                ## Variables
                Creates an mutable reference to an object
                > var fooBarBaz = "foo" + "bar" + "baz"
                >
                > fooBarBaz = "" //compiles

            """,
            code = """
                //variable allowed to be updated
                var j = 3
                j = 5

                //compiler will not allow
                val i = 3
                i = 5
            """
    )
    img(src = "valnotvar.jpg") { attrs.width = "300px" }
    markdown("""
                > Note: Val/Var only makes the reference not the object muttable/imuttable
  """.trimIndent())
    annotatedCode(annotation = "", code = """
    //immutable reference to mutable object
    val list = arrayListOf(1,2,3,4)
    list.add(5)
    println(list)
  """.trimIndent())

    markdown("# Expressions vs Statements")
    annotatedCode(
            annotation = """
      ## Expression
      An expression evaluates to a value
    """,
            code = """
      //if expression
      val expr = if(1 < 2) {
      "foo"
      } else {
      "bar"
      }
      println(expr)

      //try expression
      val tryExpr  = try {
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