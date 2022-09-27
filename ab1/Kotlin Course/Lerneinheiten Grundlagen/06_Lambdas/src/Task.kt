fun s_constant_speed (t: Float?) = t?.TODO()

fun main (args: Array<String>) {
    if (args.isEmpty())
        println("Keine Argumente angegeben!")
    // man könnte main hier auch beenden, allerdings kann man auch über die
    // leere Liste iterieren
    args.forEach {
        // Die Elemente der Liste werden der Reihe nach dem Iterator zugewiesen.
        // Den per Konvention vergegbenen Namen müssen Sie hier verwenden.
        println(s_constant_speed(TODO().toFloatOrNull()) ?: "'$TODO()' ist kein Zahlenwert")
    }
    val c: (Float, Float) -> Float = { a, b -> a * b }
    println ("c = " + c(3.0F, 5.0F))
}