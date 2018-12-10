package app.content.libraries

import app.annotatedCode
import app.readOnlyCode
import markdown
import react.RBuilder

val exposed: RBuilder.() -> Unit = {
    markdown("""
        # Kotlin Exposed

        Kotlin Exposed is a lightweight SQL library for working with a database in kotlin made by JetBrains.

        ## Get Started

        Include the kotlin exposed dependency and repository in your pom.xml

        The H2 database is also listed for a simple in-memory SQL database.

        > pom.xml
    """.trimIndent())
    readOnlyCode("""
        <repositories>
            <repository>
                <id>exposed</id>
                <name>exposed</name>
                <url>https://dl.bintray.com/kotlin/exposed</url>
            </repository>
        </repositories>

        <!-- Add these to the dependencies section -->
        <dependency>
            <groupId>org.jetbrains.exposed</groupId>
            <artifactId>exposed</artifactId>
            <version>0.10.4</version>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.197</version>
        </dependency>
    """.trimIndent())

    annotatedCode(
            """
                # Database Connection
            """, """
                Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
            """, readOnly = true
    )

    annotatedCode("""
        # Tables

        A table can be represented by extending the Table class.

        Here is a singleton that represents a Car table
    """, """
        object Car: Table( name = "Car" )
    """, readOnly = true)

    annotatedCode("""
        # Transaction

        Exposed always wants a transaction so you need to wrap any database process in the transaction { ... } block
    """, """
        transaction { doDatabaseStuff() }
    """, readOnly = true)

    annotatedCode("""
        # Columns

        Exposed has a Column type that lets you specify columns like varchar, bool or long.

        Each type has a function associated to it that returns a Column with the correct name and type.

        You can also specify primary keys and other indexes.

        > [Click here](https://github.com/JetBrains/Exposed/wiki/DataTypes) to see all the available datatypes
    """, """
        val myStringPrimaryKeyColumn = varchar("id", length = 36).primaryKey()
    """, readOnly = true)

    annotatedCode("""
        # Car Example

        Here is an example of a Car table with an identifier, a make and a model.
    """, """
        object Car: Table("Car"){ // Notice this is a singleton
            val ID = varchar("id", length = 36).primaryKey()
            val MAKE = varchar("make", length = 50)
            val MODEL = varchar("model", length = 50)
        }
    """, readOnly = true)

    annotatedCode("""
        # SQL

        Exposed has a type named FieldSet that has all of the SQL commands like select, delete, insert and update.

        After an operation that returns results you will get the raw ResultSet from JDBC.

        To convert it to a useful object you can use the columns defined in the Table object to pull out that data.

        Table is a FieldSet so you can do something like this

    """, """
        val makes: List<String> = Car.selectAll().map { it[Car.MAKE] }

        // Lets pull out something more useful
        val makesAndModels = Car.selectAll().map { it[Car.MAKE] to it[Car.MODEL] }
    """, readOnly = true)

    annotatedCode("""
        Lets make a Car class to hold the data. We can use the Car table object.
    """, """
        data class Car(val make: String, val model: String, val id: String = UUID.randomUUID().toString()){
            companion object: Table() {
                val ID = varchar("id", length = 36).primaryKey()
                val MAKE = varchar("make", length = 50)
                val MODEL = varchar("model", length = 50)

                // Lets make a selectAll function to pull out the car class
                fun selectAllCars(): List<Car> = Car.selectAll().map { Car(it[MAKE], it[Car.MODEL], it[Car.ID]) }
            }
            // And an insert function
            fun insert() = Car.insert {
                it[MAKE] = make
                it[MODEL] = model
                it[ID] = id
            }
        }

    """, readOnly = true)

    annotatedCode("""
        Then we can create a table in memory, put data into it and pull it back out.
    """, """
        fun main () {
            Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
            transaction {
                SchemaUtils.create(Car) // This creates the Car table
                cars.forEach { it.insert() }  // Inserts from a list of cars we have from http4k
                val cars = Car.selectAllCars() // Selects all cars from the DB
                println(cars) // prints them
            }
        }
    """, readOnly = true)

    markdown("""
        # Exercise

        Put all of this together into the HTTP4k Exercise so that the data is in the database and is pulled out from the database.

        > /car  GET returns all cars in JSON array

        > /car POST creates a new car and returns location in Location header (Status code 201)

        > /car/{id} GET returns car as JSON with id

        > /car/{id} DELETE deletes car with id

        **Guide**

        * On Startup, the database should be populated with cars

    """.trimIndent())

}