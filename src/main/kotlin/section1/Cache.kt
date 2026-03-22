package section1
// Armazena pares chave-valor de forma genérica

class Cache<K : Any, V : Any> {

    // mapa interno que guarda as entradas
    private val entries = mutableMapOf<K, V>()

    // insere ou substitui uma entrada no cache
    fun put(key: K, value: V) {
        entries[key] = value
    }

    // devolve o valor associado à chave, ou null se não existir
    fun get(key: K): V? {
        return entries[key]
    }

    // remove uma entrada do cache
    fun evict(key: K) {
        entries.remove(key)
    }

    // devolve o número de entradas armazenadas
    fun size(): Int {
        return entries.size
    }

    // devolve o valor existente, ou calcula e guarda um novo valor usando o lambda
    fun getOrPut(key: K, default: () -> V): V {
        val existing = entries[key]
        if (existing != null) {
            return existing
        }
        val newValue = default()
        entries[key] = newValue
        return newValue
    }

    // aplica uma transformação ao valor existente, devolve true se a chave existir
    fun transform(key: K, action: (V) -> V): Boolean {
        val existing = entries[key]
        if (existing == null) {
            return false
        }
        entries[key] = action(existing)
        return true
    }

    // devolve uma cópia imutável do conteúdo actual do cache
    fun snapshot(): Map<K, V> {
        return entries.toMap()
    }
}

fun main() {
    println("--- Word frequency cache ---")

    val wordCache = Cache<String, Int>()
    wordCache.put("kotlin", 1)
    wordCache.put("scala", 1)
    wordCache.put("haskell", 1)

    println("Size: ${wordCache.size()}")
    println("Frequency of \"kotlin\": ${wordCache.get("kotlin")}")

    println("getOrPut \"kotlin\": ${wordCache.getOrPut("kotlin") { 0 }}")
    println("getOrPut \"java\": ${wordCache.getOrPut("java") { 0 }}")
    println("Size after getOrPut: ${wordCache.size()}")

    println("Transform \"kotlin\" (+1): ${wordCache.transform("kotlin") { it + 1 }}")
    println("Transform \"cobol\" (+1): ${wordCache.transform("cobol") { it + 1 }}")

    println("Snapshot: ${wordCache.snapshot()}")


    println()
    println("--- Id registry cache ---")

    val idCache = Cache<Int, String>()
    idCache.put(1, "Alice")
    idCache.put(2, "Bob")

    println("Id 1 -> ${idCache.get(1)}")
    println("Id 2 -> ${idCache.get(2)}")

    idCache.evict(1)
    println("After evict id 1, size: ${idCache.size()}")
    println("Id 1 after evict -> ${idCache.get(1)}")
}
