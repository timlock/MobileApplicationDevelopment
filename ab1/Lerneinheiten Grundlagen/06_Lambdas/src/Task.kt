fun s_constant_speed (t: Float?) = t?.let { t * 2.0F }
fun s_variable_speed (t: Float?, v: Float?) = t?.let { v ?: s_constant_speed(t) }

fun main (args: Array<String>) {
    if (args.isEmpty())
        println("Keine Argumente angegeben!")
    // man könnte main hier auch beenden, allerdings kann man auch über die
    // leere Liste iterieren
    args.forEach {
        // Die Elemente der Liste werden der Reihe nach dem Iterator zugewiesen.
        // Den per Konvention vergegbenen Namen müssen Sie hier verwenden.
        println(s_constant_speed(it.toFloatOrNull()) ?: "'$it' ist kein Zahlenwert")
//        var nullFloat : Float? = null
        var nullFloat : Float? = 3.0F
        println(s_variable_speed(it.toFloatOrNull(), nullFloat) ?: "'$it' ist kein Zahlenwert")
    }
    val c: (Float, Float) -> Float = { a, b -> a * b }
    println ("c = " + c(3.0F, 5.0F))
}