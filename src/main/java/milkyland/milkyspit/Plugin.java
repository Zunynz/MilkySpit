package milkyland.milkyspit;

import lombok.Getter;
import milkyland.milkyspit.commands.SpitCMD;
import milkyland.milkyspit.listeners.SpitHitListener;
import milkyland.milkyspit.utils.ConfigManager;
import milkyland.milkyspit.utils.CoolDownManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
    @Getter
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("spit").setExecutor(new SpitCMD());

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new SpitHitListener(), this);

        ConfigManager.instance.init("config", "messages");

        CoolDownManager.setupCooldown();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
