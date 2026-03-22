package section1

// representa um vetor 2D com coordenadas x e y
data class Vec2(val x: Double, val y: Double) : Comparable<Vec2> {

    // soma dois vetores componente a componente
    operator fun plus(other: Vec2): Vec2 {
        return Vec2(x + other.x, y + other.y)
    }

    // subtrai dois vetores componente a componente
    operator fun minus(other: Vec2): Vec2 {
        return Vec2(x - other.x, y - other.y)
    }

    // multiplica o vetor por um escalar
    operator fun times(scalar: Double): Vec2 {
        return Vec2(x * scalar, y * scalar)
    }

    // nega o vetor (inverte o sinal das duas componentes)
    operator fun unaryMinus(): Vec2 {
        return Vec2(-x, -y)
    }

    // compara dois vetores pela sua magnitude
    override fun compareTo(other: Vec2): Int {
        return magnitude().compareTo(other.magnitude())
    }

    // calcula o comprimento do vetor
    fun magnitude(): Double {
        return Math.sqrt(x * x + y * y)
    }

    // calcula o produto escalar entre dois vetores
    fun dot(other: Vec2): Double {
        return x * other.x + y * other.y
    }

    // devolve o vetor normalizado (comprimento 1)
    fun normalized(): Vec2 {
        val mag = magnitude()
        if (mag == 0.0) {
            throw IllegalStateException("Cannot normalize a zero vector")
        }
        return Vec2(x / mag, y / mag)
    }

    // permite aceder às componentes por índice: v[0] = x, v[1] = y
    operator fun get(index: Int): Double {
        if (index == 0) return x
        if (index == 1) return y
        throw IndexOutOfBoundsException("Index $index is out of bounds for Vec2")
    }
}

fun main() {
    val a = Vec2(3.0, 4.0)
    val b = Vec2(1.0, 2.0)

    println("a = $a")
    println("b = $b")
    println("a + b = ${a + b}")
    println("a - b = ${a - b}")
    println("a * 2.0 = ${a * 2.0}")
    println("-a = ${-a}")
    println("|a| = ${a.magnitude()}")
    println("a dot b = ${a.dot(b)}")
    println("norm(a) = ${a.normalized()}")
    println("a[0] = ${a[0]}")
    println("a[1] = ${a[1]}")
    println("a > b = ${a > b}")
    println("a < b = ${a < b}")

    val vectors = listOf(Vec2(1.0, 0.0), Vec2(3.0, 4.0), Vec2(0.0, 2.0))
    println("Longest = ${vectors.max()}")
    println("Shortest = ${vectors.min()}")
}