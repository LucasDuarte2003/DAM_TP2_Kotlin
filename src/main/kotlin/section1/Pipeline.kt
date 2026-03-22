package section1

// representa uma pipeline de transformações sobre listas de strings
class Pipeline {

    // lista de etapas, cada uma tem um nome e uma função de transformação
    private val stages = mutableListOf<Pair<String, (List<String>) -> List<String>>>()

    // adiciona uma nova etapa à pipeline
    fun addStage(name: String, transform: (List<String>) -> List<String>) {
        stages.add(Pair(name, transform))
    }

    // executa todas as etapas em ordem e devolve o resultado final
    fun execute(input: List<String>): List<String> {
        var result = input
        for (stage in stages) {
            result = stage.second(result)
        }
        return result
    }

    // imprime o nome de cada etapa na ordem em que foram adicionadas
    fun describe() {
        println("Pipeline stages:")
        for (i in stages.indices) {
            println("${i + 1}. ${stages[i].first}")
        }
    }
}

// cria uma pipeline e aplica o bloco de configuração
fun buildPipeline(block: Pipeline.() -> Unit): Pipeline {
    val pipeline = Pipeline()
    pipeline.block()
    return pipeline
}

fun main() {
    val logs = listOf(
        "  INFO: server started  ",
        "  ERROR: disk full  ",
        "  DEBUG: checking config  ",
        "  ERROR: out of memory  ",
        "  INFO: request received  ",
        "  ERROR: connection timeout  "
    )

    val pipeline = buildPipeline {
        // remove espaços no início e no fim de cada linha
        addStage("Trim") { lines ->
            val result = mutableListOf<String>()
            for (line in lines) {
                result.add(line.trim())
            }
            result
        }

        // mantém apenas as linhas que contêm "ERROR"
        addStage("Filter errors") { lines ->
            val result = mutableListOf<String>()
            for (line in lines) {
                if (line.contains("ERROR")) {
                    result.add(line)
                }
            }
            result
        }

        // converte cada linha para maiúsculas
        addStage("Uppercase") { lines ->
            val result = mutableListOf<String>()
            for (line in lines) {
                result.add(line.uppercase())
            }
            result
        }

        // adiciona um índice no início de cada linha
        addStage("Add index") { lines ->
            val result = mutableListOf<String>()
            for (i in lines.indices) {
                result.add("${i + 1}. ${lines[i]}")
            }
            result
        }
    }

    pipeline.describe()
    println()

    val result = pipeline.execute(logs)
    println("Result:")
    for (line in result) {
        println(line)
    }
}