package org.harakiry.Event;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.harakiry.Commands.AbhCommand;
import org.harakiry.Config;

public class PluginListener implements Listener {
    @EventHandler
    public void PlayerJumpEvent(PlayerJumpEvent e){
        Player p = e.getPlayer();
        if (!AbhCommand.status) {
            return;
        }
        if (p.getGameMode() != GameMode.SURVIVAL || p.hasPermission("abh.access")) {
            return;
        }
        if (e.getFrom().getY() < e.getTo().getY() && p.isSprinting()) {
                String msgbar = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Action-bar"));
                String msgtitle = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Title"));
                String msgsubtitle = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Subtitle"));
                String AbhEffect = Config.getConfig().getString("Effect");

                if(AbhEffect.equals("true")){
                    int EffectDelay = Config.getConfig().getInt("Effect-dealy");
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, EffectDelay*20, 5000));
                }

                e.setCancelled(true);
                p.teleport(e.getFrom());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 0);
                p.sendActionBar(msgbar);
                p.sendTitle(msgtitle, msgsubtitle, 2, 20, 10);
        }
    }
}




