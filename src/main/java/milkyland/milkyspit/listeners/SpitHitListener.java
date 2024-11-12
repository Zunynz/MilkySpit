package milkyland.milkyspit.listeners;

import milkyland.milkyspit.utils.ChatUtil;
import milkyland.milkyspit.utils.ConfigManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import static milkyland.milkyspit.utils.ChatUtil.color;

public class SpitHitListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Projectile)) {
            return;
        }

        Projectile projectile = (Projectile) e.getDamager();

        if (projectile.getType() != EntityType.LLAMA_SPIT) {
            return;
        }

        ProjectileSource shooter = projectile.getShooter();

        if (!(shooter instanceof Player)) {
            return;
        }

        Entity hitEntity = e.getEntity();

        if (hitEntity.getType() != EntityType.PLAYER) {
            return;
        }

        Player hitPlayer = (Player) hitEntity;
        Player playerShooter = (Player) shooter;

        String spited = ConfigManager.instance.get("messages").getString("messages.spited_on_player").replace("%playerName%", playerShooter.getName());
        String you_spited = ConfigManager.instance.get("messages").getString("messages.you_spited_on_player").replace("%playerName%", hitPlayer.getName());

        hitPlayer.sendMessage(color(spited));
        playerShooter.sendMessage(color(you_spited));

        if (!ConfigManager.instance.get("config").getBoolean("remove_damage_from_spit")) {
            e.setCancelled(true);
        }
    }
}
