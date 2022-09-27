fun s (v: Float, t: Float) = v * t

fun main (args: Array<String>) {
    // ? bedeutet, die Variable v könnte auch null sein
    // würde man dies weglassen, so würde der Compiler einen Fehler
    // anzeigen
    val v : Float = args[0]?.toFloat()
    // Das zweite Argument kann auch null sein.
    // Lässt man das ? weg, so käme es zu einer IndexOutOfBounds Exception.
    var t_arg : String ? = null
    // Kommentieren Sie die folgende Zeile einmal aus.
//    t_arg = args[1]
    // Der Compiler versucht dann eine Typ-Inferenz.
    // Der erwartete Typ in s() ist Float, aber Float? wird inferiert.
    // Dies führt zu einem Compiler-Fehler.
    val t  = t_arg?.toFloat() ?: 0.0F
    println ("Wegstrecke ist " + s(v, t) + " km.")
}