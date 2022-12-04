/**
 * REST-Controller für die Authentifizierung des Clients (Login oder Registration)
 */
@Controller
public class AuthenticationController {

    /**
     * Startseite auf welcher sich der Nutzer einloggen oder registrieren kann. Nach dem  erfolgreichen Login wird /menu aufgerufen.
     * @param model leerer Container zum Ausfüllen des Registrierungsfomular#s
     * @return Name der Thymeleaf Vorlage für die Login/Registrierungsseite.
     */
    @GetMapping("/")
    public String showLoginRegisterForm(Model model);

    /**
     * Das ausgefüllte Registrierungsformular wird an den Server geschickt und verarbeitet. Bei erfolgreichem Login wird
     * /menu aufgerufen, bei Fehlermeldung wird die Startseite wieder aufgerufen.
     * @param user ausgefülltes User Objekts des Registrierungsformular.
     * @throws UserExistsException wird ausgelöst wenn der gewählte Benutzername schon in der Datenbank vorhanden ist.
     */
    @PostMapping("/register")
    public void submitRegisterForm(User user) throws UserExistsException;

}



/**
 * REST-Controller für alle Punkte des Hauptmenüs (Spielersuche, Profil, Spielstatistiken, etc.)
 */
@Controller
public class MainMenuController {

    /**
     * Menüseite, die auf die Unterseiten Spielesuche und Profil verweist.
     * @return Name der Thymeleaf Vorlage für die Menüseite.
     */
    @GetMapping("/menu")
    public String showMainMenu();

    /**
     * Spielesuchseite, auf der ein Nutzer auswählt ob er nach einer bestehende Lobby suchen will oder ob er eine neue Lobby
     * selber anlegt. Die eigentliche Suche/Lobbygenerierung erfolgt bei einem späteren Nutzeraufruf mithilfe von Websocket.
     * @return Name der Thymeleaf Vorlage für die Spielesuchseite, auf der zwischen Lobbysuche oder Lobbyerstellung
     * ausgewählt werden kann.
     */
    @GetMapping("/search_game")
    public String searchGame();

    /**
     * Profilseite, die den Nutzernamen und das Blobby Profilbild anzeigt. Auch wird hier auf die Statistikseite und auf
     * das Formular zum Ändern des Blobbybildes verlinkt.
     * @param username Parameter des Methodenaufrufs, der auf den Nutzernamen verweist.
     * @return Name der Thymeleaf Vorlage für die Profilseite des Nutzers.
     */
    @GetMapping("/profile/{name}")
    public String showProfile(@PathVariable("name") String username);

    /**
     * Formularseite, auf der ein Nutzer das Bild seines Blobbys durch hochladen einer Datei ändern kann. Dazu wählt er mithilfe
     * des Formulars eine Datei auf seinem Dateisystem aus. (Zurzeit noch nicht sicher ob auf dieser Seite auch ein Vorschaubild
     * mit dem Neuen Aussehen des Blobbys angezeigt wird oder ob der Nutzer diesen beim Aufrufen der Profilseite sieht).
     * @param username Parameter des Methodenaufrufs, der auf den Nutzernamen verweist.
     * @param model leeres Objekt, in welche die Daten des Formulars eingetragen werden.
     * @return Name der Thymeleaf Vorlage für die Änderungsseite des Blobby Bildes.
     */
    @GetMapping("/profile/{name}/blobby_pic")
    public String showBlobbyPicForm(@PathVariable("name") String username, Model model);

    /**
     * Das Formular zum Ändern des Blobby Bildes wird an den Server geschickt und verarbeitet. Nach erfolgter Änderung wird
     * wieder auf die Profilseite des Nutzers gewechselt.
     * @param username wird als Parameter beim Aufruf übergeben und verweist auf den anzuzeigenden Nutzer.
     * @param blobbyPic Bildobjekt, welches die Daten des Neuen Blobbys beinhaltet.
     */
    @PostMapping("/profile/{name}/blobby_pic_submit")
    public void submitBlobbyPicForm(@PathVariable("name") String username, BlobbyPic blobbyPic);

    /**
     * Gibt die Thymeleaf Vorlage mit allen detaillierten Statistikdaten des Nutzers an den Client zurück.
     * @param username wird als Parameter beim Aufruf übergeben und verweist auf den anzuzeigenden Nutzer.
     * @return Name der Thymeleaf Vorlage für die Statistikseite des Nutzers.
     */
    @GetMapping("/profile/{name}/statistics")
    public String showStatistics(@PathVariable("name") String username);

}



/**
 * Websocket-Kommunikation
 *
 * Im Projekt werden Websockets für die Kommunikation des Gameplay Controllers mit dem jeweiligen Client genutzt.
 * Während des Spiels werden dabei die Spielerbewegungen, die Position des Balls und auch der aktuelle Spielstand zwischen
 * dem Server und den beiden Clients ausgetauscht. Auch für die Spielersuche nutzen wir Websocket, da dies im Gegensatz
 * zu REST eine asynchrone Kommunikation ermöglicht.
 *
 * Websocket-Nutzung:
 * - Spiel
 *   - Spielerbewegungen (links, rechts und springen)
 *   - aktuelle Position des Balls
 *   - Punktestand jedes Spielers
 *   - zurücksetzen des Spielfeldes nach einer erfolgten Runde/Punkt
 *   - Meldung an beide Spieler nach Spielende
 * - Spielersuche
 *   - Client erstellt eine Lobby für ein neues Spiel ODER
 *   - Client tritt einer schon bestehende Lobby eines anderen Spielers bei
 *   - innerhalb der Lobby bestätigen die Spieler das sie bereit für das Spiel sind
 *      - wenn beide Spieler bereit sind, wird das Spiel gestartet
 *   - (eventuell noch ein eigener Chat in der Lobby, damit sich die Spieler austauschen können)
 */

