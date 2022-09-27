// 1. Behandlung von negativen Zeiten ergänzen
fun s (v: Float, t: Float) = if(t > 0) v * t else 0.0f

fun run_with_args (v: Float, t: Any) : Float {
    // 4. Zeitparameter kann auch String "zehn" sind
    var t_f : Float = 0.0F
    t_f = if(t.toString().equals("zehn")) s(v, 10.0f) else s(v, t as Float)
    return t_f
}

fun main (args: Array<String>) {
    val velocity = 5.0F
    val times = listOf (-1.0F, 1.0F, 2.0F)
    // 2. Weg für verschiedene Zeiten ausrechnen
    for (t in times)
        println (s(velocity, t))
    // 3. Weg für ganzzahlige Zeiten mit Schrittweite 1 ausrechnen
    for (t in times)
        println (s(velocity, t))
    // Funktion mit Argumenten aufrufen
    println (run_with_args(args[0].toFloat(), args[1]))
}