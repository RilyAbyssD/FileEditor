package rilyabyss.fileeditor.until;

import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class map {

    public static HashMap<Player, String> editor = new HashMap<>();
    public static HashMap<Player, String> fileEditor = new HashMap<>();
    public static HashMap<Player, String> directory = new HashMap<>();
    public static HashMap<Player, List<File>> fileList = new HashMap<>();
    public static HashMap<Player, Integer> pages = new HashMap<>();

    public static void deleteMap(Player p) {
        editor.remove(p);
        fileEditor.remove(p);
        directory.remove(p);
        fileList.remove(p);
        pages.remove(p);
    }

}
