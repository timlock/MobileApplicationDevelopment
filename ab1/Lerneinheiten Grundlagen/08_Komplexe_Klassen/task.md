##Klassen: Initialisierung und Berechnungen

Mit dem Beispiel liegt eine etwas komplexere Klassenstruktur zugrunde. Eine Klasse *Segment* (= Strecke) stellt die Verbindung zwischen 2 Punkten (Klasse *Point*) dar
und ermöglicht die Berechnung des Mittelpunkts der Strecke über die Funktion *center()*. 

Aufgaben:
1. Im *init*-Block ist Generierung der Liste von Punkten aus übergegebenen Initialisierungsparametern zu ergänzen.
2. Die Berechnung des Steckenmittelpunkts mit den Koordinaten *x_center* und *y_center* sollen per **Chaining** von Lambdas vorgenommen werden.
<div class="hint">
Zu 2.: Die Methode `let` hatten wir schon kennengelernt. Hier bietet sich die Verwendung von `map` und `sum` an.
</div>
