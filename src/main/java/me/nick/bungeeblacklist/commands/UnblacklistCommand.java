package me.nick.bungeeblacklist.commands;

import me.nick.bungeeblacklist.BungeeBlacklist;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class UnblacklistCommand extends Command {

    private BungeeBlacklist plugin = BungeeBlacklist.getInstance();
    private Configuration configuration = plugin.configuration;
    public UnblacklistCommand() {
        super("unblacklist");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length == 1) {
            if (configuration.contains(args[0])) {
                configuration.set(args[0], null);
                plugin.saveConfig();
                commandSender.sendMessage(new TextComponent(ChatColor.GREEN + "You successfully unblacklisted " + args[0]));
            } else {
                commandSender.sendMessage(new TextComponent(ChatColor.RED + "Player is not blacklisted"));
            }
        } else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "/unblacklist <player>"));
        }
    }
}
