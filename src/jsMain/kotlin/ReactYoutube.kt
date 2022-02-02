@file:JsModule("react-youtube-lite")
@file:JsNonModule

import react.*

@JsName("ReactYouTubeLite")
external val reactPlayer: ComponentClass<ReactYouTubeProps>

external interface ReactYouTubeProps : Props {
    var url: String
}