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

        ConfigManager.instance.init("config", "languages/messages_en_US", "languages/messages_ru_RU", "languages/messages_de_DE", "languages/messages_be_BY", "languages/messages_uk_UA");

        CoolDownManager.setupCooldown();

        credits();
    }

    @Override
    public void onDisable() {
    }

    private void credits() {
        getLogger().info("\n" +
                "███╗░░░███╗██╗██╗░░░░░██╗░░██╗██╗░░░██╗░██████╗██████╗░██╗████████╗\n" +
                "████╗░████║██║██║░░░░░██║░██╔╝╚██╗░██╔╝██╔════╝██╔══██╗██║╚══██╔══╝\n" +
                "██╔████╔██║██║██║░░░░░█████═╝░░╚████╔╝░╚█████╗░██████╔╝██║░░░██║░░░\n" +
                "██║╚██╔╝██║██║██║░░░░░██╔═██╗░░░╚██╔╝░░░╚═══██╗██╔═══╝░██║░░░██║░░░\n" +
                "██║░╚═╝░██║██║███████╗██║░╚██╗░░░██║░░░██████╔╝██║░░░░░██║░░░██║░░░\n" +
                "╚═╝░░░░░╚═╝╚═╝╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚═════╝░╚═╝░░░░░╚═╝░░░╚═╝░░░ ");
        getLogger().info("Made by Zunynz");
        getLogger().info(" ");
    }
}
