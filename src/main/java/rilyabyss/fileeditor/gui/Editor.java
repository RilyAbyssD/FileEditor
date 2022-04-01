package rilyabyss.fileeditor.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import rilyabyss.fileeditor.FileEditor;
import rilyabyss.fileeditor.until.map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Editor {

    public Inventory loadLocalDirectory(Player p) {

        File dir = new File("plugins/");

        File[] list = dir.listFiles();

        if (list.length > 45) {
            map.editor.put(p, "EDIT-NOW");
        } else {
            map.editor.put(p, "END-PAGES");
        }


        map.fileList.put(p, Arrays.asList(list));

        map.directory.put(p, "plugins/");

        int fileLength = map.fileList.size();

        for (int n = 0; n < fileLength; n++) {
            File name = map.fileList.get(p).get(n);
            p.sendMessage(name.getName());
        }

        map.pages.put(p, 0);

        Inventory inv = Bukkit.createInventory(null, 54, "FileEditor");

        ItemStack file = new ItemStack(Material.PAPER);
        ItemStack folder = new ItemStack(Material.CHEST);

        if(list != null) {
            for(int i = 0; i < list.length && i < 45; i++) {
                //ファイルの場合
                if(list[i].isFile()) {

                    ItemMeta fileMeta = file.getItemMeta();

                    fileMeta.setDisplayName(list[i].getName());

                    file.setItemMeta(fileMeta);

                    inv.setItem(i, file);
                }
                //ディレクトリの場合
                if(list[i].isDirectory()) {

                    ItemMeta folderMeta = folder.getItemMeta();

                    folderMeta.setDisplayName(list[i].getName());

                    folder.setItemMeta(folderMeta);

                    inv.setItem(i, folder);
                }
            }
        } else {
            FileEditor.plugin.getLogger().info("§aファイルの取得の際にエラーが発生しました");
        }

        // ファイル作成

        ItemStack createFile = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta createFileMeta = createFile.getItemMeta();
        createFileMeta.setDisplayName("§a新規ファイルを作成する");
        createFile.setItemMeta(createFileMeta);

        // フォルダ作成

        ItemStack createFolder = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta createFolderMeta = createFolder.getItemMeta();
        createFolderMeta.setDisplayName("§a新規フォルダを作成する");
        createFolder.setItemMeta(createFolderMeta);

        // 閉じる

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("終了");
        close.setItemMeta(closeMeta);

        // 終了

        ItemStack back = new ItemStack(Material.ACACIA_BUTTON);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("戻る");
        back.setItemMeta(backMeta);

        // 次

        ItemStack next = new ItemStack(Material.ACACIA_BUTTON);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName("次");
        next.setItemMeta(nextMeta);

        // 前のディレクトリに戻る

        ItemStack backDirectory = new ItemStack(Material.IRON_DOOR);
        ItemMeta backDirectoryMeta = backDirectory.getItemMeta();
        backDirectoryMeta.setDisplayName("pluginsフォルダに戻る");
        backDirectory.setItemMeta(backDirectoryMeta);

        inv.setItem(45, createFolder);
        inv.setItem(46, createFile);
        inv.setItem(48, back);
        inv.setItem(49, close);
        inv.setItem(50, next);
        inv.setItem(53, backDirectory);

        return inv;
    }

    public Inventory load(Player p) {

        File dir = new File(map.directory.get(p));

        File[] list = dir.listFiles();


        if (list.length > 45) {
            map.editor.replace(p, "EDIT-NOW");
        } else {
            map.editor.replace(p, "END-PAGES");
        }

        map.fileList.put(p, Arrays.asList(list));

        map.pages.replace(p, 0);

        Inventory inv = Bukkit.createInventory(null, 54, "FileEditor");

        ItemStack file = new ItemStack(Material.PAPER);
        ItemStack folder = new ItemStack(Material.CHEST);

        if(list != null) {
            for(int i = 0; i < list.length && i < 45; i++) {

                //ファイルの場合
                if(list[i].isFile()) {

                    ItemMeta fileMeta = file.getItemMeta();

                    fileMeta.setDisplayName(list[i].getName());

                    file.setItemMeta(fileMeta);

                    inv.setItem(i, file);

                }
                //ディレクトリの場合
                else if(list[i].isDirectory()) {

                    ItemMeta folderMeta = folder.getItemMeta();

                    folderMeta.setDisplayName(list[i].getName());

                    folder.setItemMeta(folderMeta);

                    inv.setItem(i, folder);
                }
            }
        } else {
            FileEditor.plugin.getLogger().info("§aファイルの取得の際にエラーが発生しました");
        }

        // ファイル作成

        ItemStack createFile = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta createFileMeta = createFile.getItemMeta();
        createFileMeta.setDisplayName("§a新規ファイルを作成する");
        createFile.setItemMeta(createFileMeta);

        // フォルダ作成

        ItemStack createFolder = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta createFolderMeta = createFolder.getItemMeta();
        createFolderMeta.setDisplayName("§a新規フォルダを作成する");
        createFolder.setItemMeta(createFolderMeta);

        // 閉じる

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("終了");
        close.setItemMeta(closeMeta);

        // 終了

        ItemStack back = new ItemStack(Material.ACACIA_BUTTON);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("戻る");
        back.setItemMeta(backMeta);

        // 次

        ItemStack next = new ItemStack(Material.ACACIA_BUTTON);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName("次");
        next.setItemMeta(nextMeta);

        // 前のディレクトリに戻る

        ItemStack backDirectory = new ItemStack(Material.IRON_DOOR);
        ItemMeta backDirectoryMeta = backDirectory.getItemMeta();
        backDirectoryMeta.setDisplayName("pluginsフォルダに戻る");
        backDirectory.setItemMeta(backDirectoryMeta);

        inv.setItem(45, createFolder);
        inv.setItem(46, createFile);
        inv.setItem(48, back);
        inv.setItem(49, close);
        inv.setItem(50, next);
        inv.setItem(53, backDirectory);

        return inv;

    }

    // 次へボタン

    public Inventory nextPage(Player p) {

        Integer getPlayerPage = map.pages.get(p);

        File dir = new File(map.directory.get(p));

        File[] list = dir.listFiles();

        Inventory inv = Bukkit.createInventory(null, 54, "FileEditor");

        ItemStack file = new ItemStack(Material.PAPER);
        ItemStack folder = new ItemStack(Material.CHEST);

        if (list.length - 45 > map.pages.get(p) * 45) {
            p.sendMessage("１あ");
            if(list != null) {

                for(int i = 0; i < 45; i++) {

                    Integer number = i + 45 * getPlayerPage;

                    //ファイルの場合
                    if(list[number].isFile()) {

                        ItemMeta fileMeta = file.getItemMeta();

                        fileMeta.setDisplayName(list[number].getName());

                        file.setItemMeta(fileMeta);

                        inv.setItem(i, file);
                    } else if(list[number].isDirectory()) {

                        ItemMeta folderMeta = folder.getItemMeta();

                        folderMeta.setDisplayName(list[number].getName());

                        folder.setItemMeta(folderMeta);

                        inv.setItem(i, folder);
                    }
                }
            } else {
                FileEditor.plugin.getLogger().info("§aファイルの取得の際にエラーが発生しました");
            }
        } else if (list.length - 45 < map.pages.get(p) * 45) {
            p.sendMessage("２あ");
            map.editor.replace(p, "END-PAGES");
            if(list != null) {

                Integer size = list.length - 45 * getPlayerPage;

                p.sendMessage(String.valueOf(size));

                for(int i = 0; i < size; i++) {

                    p.sendMessage(String.valueOf(i));

                    Integer number = i + 45 * getPlayerPage;

                    //ファイルの場合
                    if(list[number].isFile()) {

                        ItemMeta fileMeta = file.getItemMeta();

                        fileMeta.setDisplayName(list[number].getName());

                        file.setItemMeta(fileMeta);

                        inv.setItem(i, file);


                    } else if(list[number].isDirectory()) {

                        ItemMeta folderMeta = folder.getItemMeta();

                        folderMeta.setDisplayName(list[number].getName());

                        folder.setItemMeta(folderMeta);

                        inv.setItem(i, folder);
                    }
                }
            } else {
                FileEditor.plugin.getLogger().info("§aファイルの取得の際にエラーが発生しました");
            }
        }

        // ファイル作成

        ItemStack createFile = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta createFileMeta = createFile.getItemMeta();
        createFileMeta.setDisplayName("§a新規ファイルを作成する");
        createFile.setItemMeta(createFileMeta);

        // フォルダ作成

        ItemStack createFolder = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta createFolderMeta = createFolder.getItemMeta();
        createFolderMeta.setDisplayName("§a新規フォルダを作成する");
        createFolder.setItemMeta(createFolderMeta);

        // 閉じる

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("終了");
        close.setItemMeta(closeMeta);

        // 終了

        ItemStack back = new ItemStack(Material.ACACIA_BUTTON);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("戻る");
        back.setItemMeta(backMeta);

        // 次

        ItemStack next = new ItemStack(Material.ACACIA_BUTTON);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName("次");
        next.setItemMeta(nextMeta);

        // 前のディレクトリに戻る

        ItemStack backDirectory = new ItemStack(Material.IRON_DOOR);
        ItemMeta backDirectoryMeta = backDirectory.getItemMeta();
        backDirectoryMeta.setDisplayName("pluginsフォルダに戻る");
        backDirectory.setItemMeta(backDirectoryMeta);

        inv.setItem(45, createFolder);
        inv.setItem(46, createFile);
        inv.setItem(48, back);
        inv.setItem(49, close);
        inv.setItem(50, next);
        inv.setItem(53, backDirectory);

        return inv;

    }


    // 戻るボタン

    public Inventory backPage(Player p) {

        Integer getPlayerPage = map.pages.get(p);

        File dir = new File(map.directory.get(p));

        File[] list = dir.listFiles();

        map.editor.replace(p, "EDIT-NOW");

        Inventory inv = Bukkit.createInventory(null, 54, "FileEditor");

        ItemStack file = new ItemStack(Material.PAPER);
        ItemStack folder = new ItemStack(Material.CHEST);

        if(list != null) {

            for(int i = 0; i < 45; i++) {

                Integer number = i + 45 * getPlayerPage;

                //ファイルの場合
                if(list[number].isFile()) {

                    ItemMeta fileMeta = file.getItemMeta();

                    fileMeta.setDisplayName(list[number].getName());

                    file.setItemMeta(fileMeta);

                    inv.setItem(i, file);
                } else if(list[number].isDirectory()) {

                    ItemMeta folderMeta = folder.getItemMeta();

                    folderMeta.setDisplayName(list[number].getName());

                    folder.setItemMeta(folderMeta);

                    inv.setItem(i, folder);
                }
            }
        } else {
            FileEditor.plugin.getLogger().info("§aファイルの取得の際にエラーが発生しました");
        }

        // ファイル作成

        ItemStack createFile = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta createFileMeta = createFile.getItemMeta();
        createFileMeta.setDisplayName("§a新規ファイルを作成する");
        createFile.setItemMeta(createFileMeta);

        // フォルダ作成

        ItemStack createFolder = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta createFolderMeta = createFolder.getItemMeta();
        createFolderMeta.setDisplayName("§a新規フォルダを作成する");
        createFolder.setItemMeta(createFolderMeta);

        // 閉じる

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("終了");
        close.setItemMeta(closeMeta);

        // 終了

        ItemStack back = new ItemStack(Material.ACACIA_BUTTON);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("戻る");
        back.setItemMeta(backMeta);

        // 次

        ItemStack next = new ItemStack(Material.ACACIA_BUTTON);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName("次");
        next.setItemMeta(nextMeta);

        // 前のディレクトリに戻る

        ItemStack backDirectory = new ItemStack(Material.IRON_DOOR);
        ItemMeta backDirectoryMeta = backDirectory.getItemMeta();
        backDirectoryMeta.setDisplayName("pluginsフォルダに戻る");
        backDirectory.setItemMeta(backDirectoryMeta);

        inv.setItem(45, createFolder);
        inv.setItem(46, createFile);
        inv.setItem(48, back);
        inv.setItem(49, close);
        inv.setItem(50, next);
        inv.setItem(53, backDirectory);

        return inv;

    }

    public boolean onCreateFolder(String directory, String folderName) {
        File file = new File(directory + folderName + "/");
        return file.mkdir();
    }

    public void onCreateFile(String directory, String fileName) {
        Path p = Paths.get(directory + "/" + fileName);
        try {
            Files.createFile(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
