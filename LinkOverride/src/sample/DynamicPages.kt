package sample

import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.parsing.DOMParser
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.browser.window
import kotlin.dom.childElements
import kotlin.dom.eventHandler
import kotlin.dom.on


fun main (args: Array<String>): Unit {
   var navTag = document.getElementsByTagName("nav")
    // ul
    navTag.get(0).childElements().forEach {
        // li
        it.childElements().forEach {
            it.childElements().forEach {
                val anchor = it as HTMLAnchorElement
                anchor.on("click", true, eventHandler {
                    /* prevent event bubbling*/
                    it.preventDefault()
                    /* force URL/history change */
                    window.history.pushState("","",anchor.href)
                    loadContent(anchor.href)
                })
            }
        }
    }
}

/**
* Load specified href into "main_content" div using GET
*/
fun loadContent(href: String) {
    var mainContentDiv = document.getElementById("main_content") as HTMLElement
    val xmlRequest = XMLHttpRequest()
    xmlRequest.open("GET", href)
    xmlRequest.onreadystatechange = {
        if (xmlRequest.status == 200.toShort() && xmlRequest.readyState == XMLHttpRequest.DONE) {
            var domParser = DOMParser()
            var domText = domParser.parseFromString(xmlRequest.responseText, "text/html")
            mainContentDiv.innerHTML = domText.getElementById("main_content")!!.innerHTML
        }
    }
    xmlRequest.send()
}

