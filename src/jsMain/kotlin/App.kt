import csstype.TextDecoration
import react.*
import react.dom.*
import kotlinx.html.js.*
import kotlinx.coroutines.*
import kotlinx.css.*
import kotlinx.css.properties.TextDecorationLine
import styled.*

private val scope = MainScope()

val app = fc<Props> { _ ->
    var shoppingList by useState(emptyList<ShoppingListItem>())
    var todoList by useState(emptyList<TodoListItem>())

    var currentVideo: Video? by useState(null)

    var unwatchedVideos: List<Video> by useState(listOf(
        Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
        Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
        Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
    ))
    var watchedVideos: List<Video> by useState(listOf(
        Video(4, "Mouseless development", "Tom Jerry", "https://www.youtube.com/watch?v=w3Wluvzoggg")
    ))

    useEffectOnce {
        scope.launch {
            shoppingList = getShoppingList()
            todoList = getTodoList()
        }
    }

    h1 {
        +"Full-Stack Shopping List"
    }
    ul {
        shoppingList.sortedByDescending(ShoppingListItem::priority).forEach { item ->
            li {
                key = item.toString()
                +"[${item.priority}] ${item.desc} "

                attrs.onClickFunction = {
                    scope.launch {
                        deleteShoppingListItem(item)
                        shoppingList = getShoppingList()
                    }
                }
            }
        }
    }

    child(inputComponent) {
        attrs.onSubmit = { input ->
            val cartItem = ShoppingListItem(input.replace("!", ""), input.count { it == '!' })
            scope.launch {
                addShoppingListItem(cartItem)
                shoppingList = getShoppingList()
            }
        }
    }

    h1 {
        +"Full-Stack Todo List"
    }
    ul {
        todoList.sortedByDescending(TodoListItem::priority).forEach { item ->
            li {
                key = item.toString()
                +"[${item.priority}] ${item.desc} "

                attrs.onClickFunction = {
                    scope.launch {
                        deleteTodoListItem(item)
                        todoList = getTodoList()
                    }
                }
            }
        }
    }

    child(inputComponent) {
        attrs.onSubmit = { input ->
            val cartTodoItem = TodoListItem(input.replace("!", ""), input.count { it == '!' })
            scope.launch {
                addTodoListItem(cartTodoItem)
                todoList = getTodoList()
            }
        }
    }

    h1 {
        +"KotlinConf Explorer"
    }
    styledDiv {
        css {
            backgroundColor = Color.lightBlue
            color = Color.crimson
            cursor= Cursor.pointer
//            textDecoration= TextDecoration.underline
        }
        h3 {
            +"Videos to watch"
        }
        child(videoList) {
            attrs {
                videos = unwatchedVideos
                selectedVideo = currentVideo
                onSelectVideo = { video ->
                    currentVideo = video
                }
            }
        }

        h3 {
            +"Videos watched"
        }
        child(videoList) {
            attrs {
                videos = watchedVideos
                selectedVideo = currentVideo
                onSelectVideo = { video ->
                    currentVideo = video
                }
            }
        }
    }
    currentVideo?.let { curr ->
        child(videoPlayer) {
            attrs {
                video = curr
                unwatchedVideo = curr in unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in unwatchedVideos) {
                        unwatchedVideos = unwatchedVideos - video
                        watchedVideos = watchedVideos + video
                    } else {
                        watchedVideos = watchedVideos - video
                        unwatchedVideos = unwatchedVideos + video
                    }
                }
            }
        }
    }
}
