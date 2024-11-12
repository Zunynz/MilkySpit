package milkyland.milkyspit.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CoolDownManager {
    public static Map<UUID, Double> spitCooldowns;

    public CoolDownManager() {
    }

    public static void setupCooldown() {
        spitCooldowns = new HashMap<>();
    }

    public static void setSpitCooldown(Player player, int seconds) {
        double delay = (double) (System.currentTimeMillis() + (seconds * 1000));
        spitCooldowns.put(player.getUniqueId(), delay);
    }

    public static int getSpitCooldown(Player player) {
        return getCooldown(player, spitCooldowns);
    }

    public static boolean checkSpitCooldown(Player player) {
        return checkCooldown(player, spitCooldowns);
    }

    private static int getCooldown(Player player, Map<UUID, Double> cooldowns) {
        if (cooldowns.containsKey(player.getUniqueId())) {
            long currentTimeInSeconds = System.currentTimeMillis() / 1000;
            long cooldownEndTimeInSeconds = cooldowns.get(player.getUniqueId()).longValue() / 1000;
            long differenceInSeconds = cooldownEndTimeInSeconds - currentTimeInSeconds;
            return (int) differenceInSeconds;
        }
        return 0;
    }

    private static boolean checkCooldown(Player player, Map<UUID, Double> cooldowns) {
        if (!cooldowns.containsKey(player.getUniqueId()) || cooldowns.get(player.getUniqueId()) <= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }
}