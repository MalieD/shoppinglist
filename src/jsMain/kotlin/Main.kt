import react.child
import react.dom.render
import kotlinx.browser.document

fun main() {
    render(document.getElementById("root")) {
        child(app)
    }
}

data class Video(
    val id: Int,
    val title: String,
    val speaker: String,
    val videoUrl: String
)
