package app.content.overview

import Markdown
import react.RBuilder

val ktFiles: RBuilder.() -> Unit = {
    Markdown {
        attrs.source = """
                # Kotlin Files
                ...
                ## Structure
                ...
                ## Packages
                ...
                ## Imports
                ..
                ## 1st Class Citizens
                ... (classes, functions, variables) [ high level ]
                """.trimIndent().trimMargin()
    }
}