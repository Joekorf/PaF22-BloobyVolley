# Websocket-Kommunikation
Im Projekt werden Websockets für die Kommunikation des Gameplay Controllers mit dem jeweiligen Client genutzt.
Während des Spiels werden dabei die Spielerbewegungen, die Position des Balls und auch der aktuelle Spielstand zwischen
dem Server und den beiden Clients ausgetauscht.
Auch für die Spielersuche nutzen wir Websocket, da dies im Gegensatz zu REST eine asynchrone Kommunikation ermöglicht.

Websocket-Nutzung:
- Spiel
  - Spielerbewegungen (links, rechts und springen)
  - aktuelle Position des Balls
  - Punktestand jedes Spielers
  - zurücksetzen des Spielfeldes nach einer erfolgten Runde/Punkt
  - Meldung an beide Spieler nach Spielende
- Spielersuche
  - Client erstellt eine Lobby für ein neues Spiel ODER
  - Client tritt einer schon bestehende Lobby eines anderen Spielers bei
  - innerhalb der Lobby bestätigen die Spieler das sie bereit für das Spiel sind
  - wenn beide Spieler bereit sind, wird das Spiel gestartet
  - (eventuell noch ein eigener Chat in der Lobby, damit sich die Spieler austauschen können)