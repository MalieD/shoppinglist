import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

import kotlinx.browser.window

val endpoint = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val jsonClient = HttpClient {
    install(JsonFeature) { serializer = KotlinxSerializer() }
}

suspend fun getShoppingList(): List<ShoppingListItem> {
    return jsonClient.get(endpoint + ShoppingListItem.path)
}

suspend fun addShoppingListItem(shoppingListItem: ShoppingListItem) {
    jsonClient.post<Unit>(endpoint + ShoppingListItem.path) {
        contentType(ContentType.Application.Json)
        body = shoppingListItem
    }
}

suspend fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) {
    jsonClient.delete<Unit>(endpoint + ShoppingListItem.path + "/${shoppingListItem.id}")
}




suspend fun getTodoList(): List<TodoListItem> {
    return jsonClient.get(endpoint + TodoListItem.path)
}

suspend fun addTodoListItem(todoListItem: TodoListItem) {
    jsonClient.post<Unit>(endpoint + TodoListItem.path) {
        contentType(ContentType.Application.Json)
        body = todoListItem
    }
}

suspend fun deleteTodoListItem(todoListItem: TodoListItem) {
    jsonClient.delete<Unit>(endpoint + TodoListItem.path + "/${todoListItem.id}")
}