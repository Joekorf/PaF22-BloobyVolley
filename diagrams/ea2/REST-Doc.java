/**
 * REST-Controller für die Authentifizierung des Clients (Login oder Registration)
 */
@Controller
public class AuthenticationController {

    
    @GetMapping("/login")
    public login() {}


    @GetMapping("/register")
    public showRegisterForm(Model model) {}


    @PostMapping("/register_submit")
    public submitRegisterForm(User user) {}

}



/**
 * REST-Controller für alle Punkte des Hauptmenüs (Spielersuche, Profil, Spielstatistiken, etc.)
 */
@Controller
public class MainMenuController {


    @GetMapping("/search_game")
    public searchGame(){}


    @GetMapping("/menu")
    public showMainMenu() {}


    @GetMapping("/profile/{id}")
    public showProfile(@PathVariable("id") String id) {}


    @GetMapping("/profile/{id}/profile_pic")
    public showProfilePicForm(@PathVariable("id") String id) {}


    @PostMapping("/profile/profile_pic_submit")
    public submitProfilePicForm() {}


    @GetMapping("/statistics")
    public showStatistics() {}

}
