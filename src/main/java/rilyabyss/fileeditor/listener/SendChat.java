package rilyabyss.fileeditor.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import rilyabyss.fileeditor.gui.Editor;
import rilyabyss.fileeditor.until.map;

public class SendChat implements Listener {

    Editor editor = new Editor();

    @EventHandler
    public void sendChat(AsyncPlayerChatEvent event) {

        Player p = event.getPlayer();

        String mes = event.getMessage();

        if (map.fileEditor.containsKey(p) && map.fileEditor.containsValue("FOLDER")) {
            if (editor.onCreateFolder(map.directory.get(p), mes)) {
                p.sendMessage("フォルダ: " + event.getMessage() + " を作成しました");
                map.fileEditor.remove(p);
                p.openInventory(editor.load(p));
                event.setCancelled(true);
            } else {
                p.sendMessage("エラーが発生しました");
                map.fileEditor.remove(p);
                p.openInventory(editor.load(p));
                event.setCancelled(true);
            }
        } else if (map.fileEditor.containsKey(p) && map.fileEditor.containsValue("FILE")) {
            if (!mes.contains(".")) {
                p.sendMessage("§c拡張子を入れてください");
            } else {
                editor.onCreateFile(map.directory.get(p), event.getMessage());
                p.sendMessage("ファイル: " + event.getMessage() + " を作成しました");
            }
        }

    }

}
