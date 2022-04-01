package rilyabyss.fileeditor.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import rilyabyss.fileeditor.gui.Editor;
import rilyabyss.fileeditor.until.map;

public class ClickInventory implements Listener {

    Editor editor = new Editor();

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {

        Player p = (Player) event.getWhoClicked();


        if (event.getView().getTitle().equals("FileEditor")) {

            // アイテム名 アイテムタイプ取得

            String name = event.getCurrentItem().getItemMeta().getDisplayName();
            Material type = event.getCurrentItem().getType();

            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null || event.getView().getTitle() == null) {
                return;
            }
            // オプション

            if (event.getCurrentItem().getType() == Material.PAPER) {
                p.sendMessage("むり＜＜");
                return;
            } else if (name.equals("終了")) {
                map.deleteMap(p);
                p.closeInventory();
                p.sendMessage("§aファイルエディタを終了しました");
                sound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2);
            } else if (name.equals("次")) {
                p.sendMessage(String.valueOf(map.fileList.get(p).size()));
                map.pages.replace(p, map.pages.get(p) + 1);
                if (map.editor.containsValue("END-PAGES")) {
                    p.sendMessage("この操作は出来ません");
                    event.setCancelled(true);
                    return;
                }
                p.openInventory(editor.nextPage(p));
                sound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2);
            } else if (name.equals("戻る")) {
                if (map.editor.containsValue("END-PAGES")) {
                    p.sendMessage("この操作は出来ません");
                    event.setCancelled(true);
                    return;
                }
                map.pages.replace(p, map.pages.get(p) - 1);
                p.openInventory(editor.backPage(p));
                sound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2);
            } else if (name.equals("pluginsフォルダに戻る")) {
                p.openInventory(editor.loadLocalDirectory(p));
            } else if (name.equals("§a新規フォルダを作成する")) {
                map.fileEditor.put(p, "FOLDER");
                p.closeInventory();
                p.sendMessage("チャットにフォルダ名を入力してください");
            } else if (name.equals("§a新規ファイルを作成する")) {
                map.fileEditor.put(p, "FILE");
                p.closeInventory();
                p.sendMessage("チャットにファイル名を入力してください");
            }

            // ディレクトリ移動

            if (type == Material.PAPER || type == Material.CHEST) {
                if (map.directory.containsKey(p)) {
                    map.directory.replace(p, map.directory.get(p) + name + "/");
                    map.fileList.remove(p);
                    map.pages.replace(p, 0);
                    p.sendMessage(map.directory.get(p));
                    p.openInventory(editor.load(p));
                    sound(p, Sound.BLOCK_CHEST_OPEN, 1.0F, 1);
                } else {
                    p.sendMessage("§cエラーが発生しました");
                }
            }

        }

        event.setCancelled(true);
    }

    public void sound(Player p, Sound s, Float v, Integer pitch) {

        p.playSound(p.getLocation(), s, v, pitch);

    }

}
