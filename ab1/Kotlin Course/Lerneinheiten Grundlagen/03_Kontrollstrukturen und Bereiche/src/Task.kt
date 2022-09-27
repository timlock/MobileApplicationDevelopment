// 1. Behandlung von negativen Zeiten ergänzen
fun s (v: Float, t: Float) = TODO() v * t TODO()

fun run_with_args (v: Float, t: Any) : Float {
    // 4. Zeitparameter kann auch String "zehn" sind
    var t_f : Float = 0.0F
TODO()
    return t_f
}

fun main (args: Array<String>) {
    val velocity = 5.0F
    val times = listOf (-1.0F, 1.0F, 2.0F)
    // 2. Weg für verschiedene Zeiten ausrechnen
    for (TODO())
        println (s(velocity, t))
    // 3. Weg für ganzzahlige Zeiten mit Schrittweite 1 ausrechnen
    for (TODO())
        println (s(velocity, t.toFloat()))
    // Funktion mit Argumenten aufrufen
    println (run_with_args(args[0].toFloat(), args[1]))
}