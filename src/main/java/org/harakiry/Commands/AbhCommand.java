package org.harakiry.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.harakiry.AntiBHOP;
import org.harakiry.Config;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.bukkit.Sound.BLOCK_NOTE_BLOCK_BASS;
import static org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

public class AbhCommand implements CommandExecutor {
    public static boolean status = true;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String arg, @NotNull String[] args) {
        AntiBHOP plugin = AntiBHOP.getPlugin(AntiBHOP.class);
        Config conf = new Config();
        Player p = (Player) sender;
        String noperms = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Permission"));
        Location playerLocation = p.getLocation();
        if (args.length == 0) {
            String usage = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Usage"));
            p.sendMessage(usage);
            p.playSound(playerLocation, BLOCK_NOTE_BLOCK_BASS, 2, 1);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload": {
                if (args[0].equalsIgnoreCase("reload") && p.hasPermission("abh.reload")) {
                    String reload = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Reload"));
                    conf.reloadConfig(plugin);
                    p.playSound(playerLocation, ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
                    p.sendMessage(reload);
                    return true;
                }
                break;
            }
            case "help":{
                if (args[0].equalsIgnoreCase("help") && p.hasPermission("abh.help")) {
                    String helpcmd = ChatColor.translateAlternateColorCodes('&', "&c[ABH] &fДоступные команды:" +
                            "\n&c| &f/abh reload - Перезагрузит конфиг плагина" +
                            "\n&c| &f/abh enable - Включит систему анти-баннихопа" +
                            "\n&c| &f/abh disable - Выключит систему анти-баннихопа");
                    p.sendMessage(helpcmd);
                    return true;
                }
                break;
            }
            case "disable": {
                if (args[0].equalsIgnoreCase("disable") && p.hasPermission("abh.disable")) {
                if (status == true) {
                    String disable = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Disable title"));
                    String disablesub = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Disable subtitle"));
                    p.sendTitle(disable, disablesub, 2, 20, 10);
                    status = false;
                }
                else {
                    if (status == false) {
                        String errorOff = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Error disable title"));
                        String errorOffsub = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Error disable subtitle"));
                        p.sendTitle(errorOff, errorOffsub, 2, 20, 10);
                    }
                }
                    return true;
                }
                break;
            }
            case "enable":{
                if (args[0].equalsIgnoreCase("enable") && p.hasPermission("abh.enable")) {
                    if (status == false) {
                        String enable = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Enable title"));
                        String enablesub = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Enable subtitle"));
                        p.sendTitle(enable, enablesub, 2, 20, 10);
                        status = true;
                    } else {
                        if (status == true) {
                            String errorOn = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Error enable title"));
                            String errorOnsub = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Error enable subtitle"));
                            p.sendTitle(errorOn, errorOnsub, 2, 20, 10);
                        }
                    }
                    return true;
                }
                break;
            }
            default: {
                String unknown = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("Unknown"));
                p.sendMessage(unknown);
                p.playSound(playerLocation, BLOCK_NOTE_BLOCK_BASS, 2, 1);
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("help") && !p.hasPermission("abh.help")) {
            p.sendMessage(noperms);
            p.playSound(playerLocation, BLOCK_NOTE_BLOCK_BASS, 2, 1);
            return true;
        }
        if (args[0].equalsIgnoreCase("reload") && !p.hasPermission("abh.reload")) {
            p.sendMessage(noperms);
            p.playSound(playerLocation, BLOCK_NOTE_BLOCK_BASS, 2, 1);
            return true;
        }
        if (args[0].equalsIgnoreCase("disable") && !p.hasPermission("abh.disable")) {
            p.sendMessage(noperms);
            p.playSound(playerLocation, BLOCK_NOTE_BLOCK_BASS, 2, 1);
            return true;
        }
        if (args[0].equalsIgnoreCase("enable") && !p.hasPermission("abh.enable")) {
            p.sendMessage(noperms);
            p.playSound(playerLocation, BLOCK_NOTE_BLOCK_BASS, 2, 1);
            return true;
        }
        return false;
    }
}