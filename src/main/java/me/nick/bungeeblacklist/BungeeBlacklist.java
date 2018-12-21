package me.nick.bungeeblacklist;

import me.nick.bungeeblacklist.commands.BlacklistCommand;
import me.nick.bungeeblacklist.commands.CheckblacklistCommand;
import me.nick.bungeeblacklist.commands.UnblacklistCommand;
import me.nick.bungeeblacklist.events.PreLoginEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BungeeBlacklist extends Plugin {

    private static BungeeBlacklist instance;
    private File file;
    public Configuration configuration;

    @Override
    public void onEnable() {
        setInstance(this);
        loadConfig();
        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        getProxy().getPluginManager().registerCommand(this, new BlacklistCommand());
        getProxy().getPluginManager().registerCommand(this, new UnblacklistCommand());
        getProxy().getPluginManager().registerCommand(this, new CheckblacklistCommand());
    }

    private void registerListeners() {
        getProxy().getPluginManager().registerListener(this, new PreLoginEvent());
    }

    private void loadConfig() {
        file = new File(ProxyServer.getInstance().getPluginsFolder(), "/BungeeBlacklist/blacklists.yml");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BungeeBlacklist getInstance() {
        return instance;
    }

    public static void setInstance(BungeeBlacklist instance) {
        BungeeBlacklist.instance = instance;
    }

}
