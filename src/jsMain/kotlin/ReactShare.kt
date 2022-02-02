@file:JsModule("react-share")
@file:JsNonModule

import react.ComponentClass
import react.Props

@JsName("EmailIcon")
external val emailIcon: ComponentClass<IconProps>

@JsName("EmailShareButton")
external val emailShareButton: ComponentClass<ShareButtonProps>

@JsName("TelegramIcon")
external val telegramIcon: ComponentClass<IconProps>

@JsName("TelegramShareButton")
external val telegramShareButton: ComponentClass<ShareButtonProps>

external interface ShareButtonProps : Props {
    var url: String
}

external interface IconProps : Props {
    var size: Int
    var round: Boolean
}