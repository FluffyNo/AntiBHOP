package org.harakiry;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.harakiry.Commands.AbhCommand;
import org.harakiry.Commands.CMDCompliter;
import org.harakiry.Event.PluginListener;


public final class AntiBHOP extends JavaPlugin {
    private static AntiBHOP instance;
    public AntiBHOP(){
        instance = this;
    }
    public static AntiBHOP getInstance(){
        return instance;
    }
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PluginListener(), this);
        Bukkit.getPluginCommand("abh").setTabCompleter(new CMDCompliter());
        Bukkit.getPluginCommand("abh").setExecutor(new AbhCommand());
        Config config = new Config();
        config.loadYamlFile(this);
        reloadConfig();
    }



    @Override
    public void onDisable() {
    }
}
