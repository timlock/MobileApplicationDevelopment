fun s (v: Float, t: Float) = v * t

fun main (args: Array<String>) {
    if (args.size != 2) {
        println ("Falsche Anzahl Argumente!")
        return
    }
    // Typ von v wird definiert
    val v : Float = args[0].toFloat()
    // t wird per Typinferenz als Float-Wert gesetzt
    val t = args[1].toFloat()
    println ("Wegstrecke ist " + s(v, t) + " km.")
}