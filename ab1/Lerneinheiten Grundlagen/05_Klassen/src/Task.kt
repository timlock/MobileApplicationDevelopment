open class WayLength (speed: Float, time: Float) {
    var length = speed * time;
}
// Hier WayLengthAtSpeed3 definieren.
class WayLengthAtSpeed3 (time: Float){
    val speed : Float = 3.0F
    var length = speed * time
}
fun main () {
    println ("Wegstrecke: " + WayLength (2.0F, 5.0F).length)
    // Berechnung von WayLengthAtSpeed3 abrufen.
    println ("Wegstrecke: " + WayLengthAtSpeed3 (5.0F).length)

}


