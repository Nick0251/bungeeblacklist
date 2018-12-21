package me.nick.bungeeblacklist.commands;

import me.nick.bungeeblacklist.BungeeBlacklist;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlacklistCommand extends Command {

    private BungeeBlacklist plugin = BungeeBlacklist.getInstance();
    private Configuration configuration = plugin.configuration;
    public BlacklistCommand() {
        super("blacklist");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length > 1) {
            if (args[0] != null || args[0] != "") {
                StringBuilder stringBuilder = new StringBuilder();
                for (String arg : args) {
                    if (arg != args[0]) stringBuilder.append(arg).append(" ");
                }
                String newString = stringBuilder.toString();
                newString = newString.substring(0, newString.length() - 1);
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String date = df.format(new Date());
                for (ProxiedPlayer player : BungeeBlacklist.getInstance().getProxy().getPlayers()) {
                    if (player.getName().equalsIgnoreCase(args[0])) player.disconnect(new TextComponent(ChatColor.RED + newString));
                }
                configuration.set(args[0] + ".reason", newString);
                configuration.set(args[0] + ".date", date);
                plugin.saveConfig();
                commandSender.sendMessage(new TextComponent(ChatColor.GREEN + "You successfully blacklisted " + args[0]));
            } else {
                commandSender.sendMessage(new TextComponent(ChatColor.RED + "/blacklist <player> <reason>"));
            }
        } else {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "/blacklist <player> <reason>"));
        }
    }
}
