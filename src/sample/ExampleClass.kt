package sample

import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTableElement
import org.w3c.dom.HTMLTableRowElement
import kotlin.browser.document

// Bind to native javascript setInterval
@native fun setInterval(function: () -> Unit, timeout: Long)

class Example {

    var textField = document.getElementById("textField") as HTMLInputElement
    val submitButton = document.getElementById("submitButton") as HTMLButtonElement
    val table = document.getElementById("table") as HTMLTableElement

    init {
        textField.readOnly = true
        submitButton.onclick = {
            addTextToTable(textField.value, table)
        }
        setInterval({setTextField(Date().toString())}, 200)
    }

    fun addTextToTable(value: String, table: HTMLTableElement) {
        var row = table.insertRow(1) as HTMLTableRowElement
        var cell = row.insertCell(0)
        var rowText = document.createTextNode(value)
        cell.appendChild(rowText)
    }

    fun setTextField(text: String) {
        textField.value = text
        textField.style.width = (text.length * 8).toString() + "px"
    }
}