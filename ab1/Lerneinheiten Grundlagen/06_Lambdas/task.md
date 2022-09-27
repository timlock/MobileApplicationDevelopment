##Lambdas

**Lambda-Funktionen** sind kurze Code-Schnipsel (geklammert per {...}, die nicht explizit als Funktionen deklariert werden. 
Dies kann die Übersichtlichkeit von Code erhöhen. Im folgenden Beispiel wird die Berechnung einer Konstanten durch einen 
Lamda-Ausdruck beschrieben.

<pre> 
    val c: (Float, Float) -> Float = { a, b -> a * b }
    println ("c = " + c(3.0F, 5.0F))
</pre>

Über die **Scope-Funktion** *let* (ist eher eine Methode) kann der Code an eine
andere Funktion übergeben werden und dort ausgeführt werden.

Aufgaben: 
1. Ergänzen Sie in der Funktion *s_constant_speed()* den Lambda-Ausdruck zur Ermittlung der Wegstrecke via *let{...}*. 
2. Ergänzen in der Iteration über die Argumente (*foreach*) die implizite Iterator-Variable
3. Die Funktion *s_constant_speed()* hat nur einen Parameter. Damit hat *let* ein Objekt für den Methodenaufruf. Wie ist die Lösung zu ändern, damit 
auch 2 Parameter verarbeitet werden können?

Testen sollten Sie Ihr Projekt mit den Parametern 7.0F und zehn in der Run Configuration.

<div class="hint">
Zu 3.: Orientieren Sie sich an dem Bsp. oben zur Berechnung von *length*.
</div>
