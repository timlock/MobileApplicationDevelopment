TODO()class Point (TODO()x: Double, TODO()y: Double)

fun main () {
    // Initialisierung der Liste per Lambda
    val list = List(5) { "el$it" }
    println (list)
    val mlist = TODO()
    // Element am Anfang der Liste plazieren
    mlist.add (0, "el4711")

    // Menge mit Punkten
    val points = mutableSetOf<Point>()
    points.add(Point(1.0,1.0))
    println (points.contains(Point(1.0,1.0)))
    points.add(Point(1.0,1.0));
    // Der Punkt [1.0,1.0] sollte nur einmal im Set auftauchen.
    println (points)
}