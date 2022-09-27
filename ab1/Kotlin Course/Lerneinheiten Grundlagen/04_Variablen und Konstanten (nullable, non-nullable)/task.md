##Variablen und Konstanten (nullable und nicht-nullable)

Mit `?` wird Konstanten (*val*) und Variablen (*var*) erlaubt, auch den Wert
*null* anzunehmen. Das sollte nach Möglichkeit verhindert werden, denn dadurch wird
eine Sonderbehandlung der Situation notwendig. 

Kotlin verfolgt den **Fast-Fail** Ansatz, d.h. wird einer Konstanten oder Variablen ohne '?' Modifier
der Wert *null* zugewiesen, so kommt es zu einer Exception.

Per `?` kann ein Objekt auf null abgefragt werden. Ist dies der Fall, so wird 
die Methode / Funktion auf dem null-Wert erst gar nicht aufgerufen.

<pre>
  var v : Float = args[0]?.toFloat()
</pre>

Der Compiler hilft, solche Situationen schon zur Compile-Zeit zu erkennen.
In dem Beispiel unten, versucht der Compiler eine **Typ-Inferenz**. Der erwartete Typ 
in `s()` ist `Float`, aber `Float?` wird für die Konstante `t` inferiert, 
was zu einem Fehler beim Aufruf von `s()`führt.

<pre>
  val t = t_arg?.toFloat()
  println ("Wegstrecke ist " + s(v, t) + " km.")
</pre>

Aber wie initialisiert man die Werte richtig? Modifizieren den Code zur Initialisierung 
von 'v' und 't' mit Hilfe des **Elvis-Operators**!

<div class="hint">
Beim Elvis-Operator `?:` wird der Ausdruck vor dem Operator auf `null` überprüft. 

<pre>
  val t: Float = some_var?.toFloat() ?: 0.0F
</pre>

Falls dies der Fall ist, wird der Variablen `t` ein Standard-Wert (z.B. `0.0F`) zugewiesen.
</div>
