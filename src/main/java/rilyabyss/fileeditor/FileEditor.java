package rilyabyss.fileeditor;

import org.bukkit.plugin.java.JavaPlugin;
import rilyabyss.fileeditor.gui.Commands;
import rilyabyss.fileeditor.listener.ClickInventory;
import rilyabyss.fileeditor.listener.SendChat;

public final class FileEditor extends JavaPlugin {

    public static FileEditor plugin;

    @Override
    public void onEnable() {

        plugin = this;

        getCommand("file").setExecutor(new Commands());

        getServer().getPluginManager().registerEvents(new ClickInventory(), this);
        getServer().getPluginManager().registerEvents(new SendChat(), this);

    }

    @Override
    public void onDisable() {

    }

    public static FileEditor plugin() {
        return plugin;
    }

}
