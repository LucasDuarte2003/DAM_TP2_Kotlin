// Representa os diferentes tipos de eventos do sistema
sealed class Event {
    data class Login(val username: String, val timestamp: Long) : Event()
    data class Purchase(val username: String, val amount: Double, val timestamp: Long) : Event()
    data class Logout(val username: String, val timestamp: Long) : Event()
}

// Devolve apenas os eventos associados ao utilizador indicado
fun List<Event>.filterByUser(username: String): List<Event> =
    filter { event ->
        when (event) {
            is Event.Login    -> event.username == username
            is Event.Purchase -> event.username == username
            is Event.Logout   -> event.username == username
            else -> false
        }
    }

// Calcula o total gasto pelo utilizador em eventos de compra
fun List<Event>.totalSpent(username: String): Double =
    filterIsInstance<Event.Purchase>()
        .filter { it.username == username }
        .sumOf { it.amount }

// Aplica o handler a cada evento da lista
fun processEvents(events: List<Event>, handler: (Event) -> Unit) {
    events.forEach { handler(it) }
}

fun main() {
    val events = listOf(
        Event.Login("alice", 1_000),
        Event.Purchase("alice", 49.99, 1_100),
        Event.Purchase("bob", 19.99, 1_200),
        Event.Login("bob", 1_050),
        Event.Purchase("alice", 15.00, 1_300),
        Event.Logout("alice", 1_400),
        Event.Logout("bob", 1_500)
    )

    // Imprime uma descrição legível de cada evento
    processEvents(events) { event ->
        when (event) {
            is Event.Login    -> println("[LOGIN] ${event.username} logged in at t=${event.timestamp}")
            is Event.Purchase -> println("[PURCHASE] ${event.username} spent $${event.amount} at t=${event.timestamp}")
            is Event.Logout   -> println("[LOGOUT] ${event.username} logged out at t=${event.timestamp}")
            else -> false
        }
    }

    println()

    // Mostra o total gasto por cada utilizador (formatado com 2 casas decimais)
    println("Total spent by alice: ${"%.2f".format(events.totalSpent("alice"))}")
    println("Total spent by bob: ${"%.2f".format(events.totalSpent("bob"))}")

    println()

    // Lista os eventos filtrados por utilizador
    println("Events for alice:")
    events.filterByUser("alice").forEach { println(it) }
}
