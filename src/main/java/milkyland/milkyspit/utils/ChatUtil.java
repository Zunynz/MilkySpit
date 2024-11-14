package milkyland.milkyspit.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtil {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendMessage(CommandSender recipient, String message) {
        recipient.sendMessage(color(message));
    }

    public static void sendConfigMessage(CommandSender recipient, String configPath) {
        sendMessage(recipient, ConfigManager.instance.getLocaleConfig().getString(configPath));
    }
}
