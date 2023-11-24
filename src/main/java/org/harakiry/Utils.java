package org.harakiry;

import org.bukkit.ChatColor;

public class Utils {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static String getString(String path) {
        return AntiBHOP.getInstance().getConfig().getString(path);
    }
}
