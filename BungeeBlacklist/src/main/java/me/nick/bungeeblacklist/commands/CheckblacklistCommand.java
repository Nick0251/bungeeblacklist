package me.nick.bungeeblacklist.commands;

import me.nick.bungeeblacklist.BungeeBlacklist;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class CheckblacklistCommand extends Command {

    private BungeeBlacklist plugin = BungeeBlacklist.getInstance();
    private Configuration configuration = plugin.configuration;
    public CheckblacklistCommand() {
        super("checkblacklist");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            if (configuration.contains(args[0])) {
                String reason = configuration.getString(args[0] + ".reason");
                String date = configuration.getString(args[0] + ".date");
                commandSender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------")));
                commandSender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&fBlacklisted: &aYes")));
                commandSender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&fReason: &a" + reason)));
                commandSender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&fDate: &a" + date)));
                commandSender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------")));
            } else {
                commandSender.sendMessage(new TextComponent(ChatColor.RED + "Player is not blacklisted"));
            }
        } else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "/checkblacklist <player>"));
        }
    }
}
