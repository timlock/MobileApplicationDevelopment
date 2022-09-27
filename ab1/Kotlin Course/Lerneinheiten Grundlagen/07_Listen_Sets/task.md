##Listen und Sets

Kotlin bietet analog zu Java zahlreiche Klassen zur Handhabung von **Aufzählungen** (Collections). 
Hier sollen Listen und Sets betrachtet werden.

Aufgaben:
1. Im ersten Teil wird eine Liste angelegt, die später per *add()* erweitert werden soll. Dazu wird aus der ursprünglichen eine 
weitere Liste erstellt, deren Initialisierung Sie ergänzen sollen. 
2. Im zweiten Teil wird ein Set betrachtet, in das Punkte (Elemente der Klasse *Point*) aufgenommen werden können. 
Natürlich sollte jeder Punkt nur einmal im Set auftauchen. Kotlin berechnet dazu intern eindeutige Hashcodes.
Welche Veränderung ist an *Point* vorzunehmen, damit die Einmaligkeit von Einträgen in der Menge gewährleistet ist? 
<div class="hint">
Zu 2.: Sehen Sie sich einmal die Verwendung von `data class` an.
</div>
