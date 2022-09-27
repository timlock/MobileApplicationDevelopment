// Punkt-Klasse
data class Point(var x: Double, var y: Double)

// Klasse Segment für Strecke zwischen zwei Punkten
class Segment(vararg pts: Point) {
    private val points: List<Point>
    // primärer Konstruktor per init-Block
    init {
        if (pts.count() < 2) {
            val msg = "Mindestens 2 Punkte angeben!"
            throw IllegalArgumentException(msg)
        }
        // Aus pts wird eine Liste mit Points erstellt.
        this.points = TODO()
    }
    // Methode zur Berechnung des Mittelpunkts der Strecke
    fun center(): Point {
        val x_center = points.TODO() / 2.0F
        val y_center = points.TODO() / 2.0F
        return Point(x_center, y_center)
    }
}

fun main () {
    val p1 = Point (1.0, 1.0)
    // An dieser Stelle gibt es eine Exception -> ggf. auskommentieren
    val segment1 = Segment (p1)
    val p2 = Point (4.0, 3.0)
    val segment2 = Segment (p1, p2)
    println ("Stecken-Mittelpunkt: " +  segment2.center())
}