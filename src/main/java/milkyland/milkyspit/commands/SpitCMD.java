package milkyland.milkyspit.commands;

import milkyland.milkyspit.utils.ChatUtil;
import milkyland.milkyspit.utils.ConfigManager;
import milkyland.milkyspit.utils.CoolDownManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

import static milkyland.milkyspit.utils.ChatUtil.color;

public class SpitCMD implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            ChatUtil.sendConfigMessage(sender, "error.only_for_player");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("milkyspit.use")) {
            ChatUtil.sendConfigMessage(player, "error.not_enough_perms");
            return true;
        }

        if (args.length == 0) {
            if (CoolDownManager.checkSpitCooldown(player)) {
                CoolDownManager.setSpitCooldown(player, ConfigManager.instance.get("config").getInt("cooldowns.spit"));

                Vector vector = player.getEyeLocation().getDirection().multiply(0.8);
                player.launchProjectile(LlamaSpit.class, vector);
                player.playSound(player.getLocation(), Sound.ENTITY_LLAMA_SPIT, 1.0F, 1.0F);
            } else {
                String message = ConfigManager.instance.getLocaleConfig().getString("cooldowns.spit").replace("%time%", String.valueOf(CoolDownManager.getSpitCooldown(player)));
                player.sendMessage(color(message));
            }
        } else {
            switch (args[0].toLowerCase()) {
                case "reload":
                    ConfigManager.instance.reload();
                    ChatUtil.sendConfigMessage(player, "messages.reloaded");
                    return true;

                default:
                    ChatUtil.sendConfigMessage(player, "error.too_many_args");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (sender.isOp()) {
            return Arrays.asList("reload");
        }

        return null;
    }
}
