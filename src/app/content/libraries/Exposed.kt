package app.content.libraries

import app.annotatedCode
import app.readOnlyCode
import markdown
import react.RBuilder

val exposed: RBuilder.() -> Unit = {
    markdown("""
        # Kotlin Exposed

        Kotlin Exposed is a lightweight SQL library for working with a database in kotlin.

        ## Get Started

        Include the kotlin exposed dependency and repository in your pom.xml

        The H2 database is also listed for a simple in-memory database.

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

    annotatedCode("""

    """, """""")

}