package me.nick.bungeeblacklist.events;

import me.nick.bungeeblacklist.BungeeBlacklist;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PreLoginEvent implements Listener {

    private BungeeBlacklist plugin = BungeeBlacklist.getInstance();

    @EventHandler
    public void loginEvent(LoginEvent e) {
        String name = e.getConnection().getName();
        if (plugin.configuration.contains(name)) {
            String reason = plugin.configuration.getString(name + ".reason");
            e.setCancelReason(reason);
            e.setCancelled(true);
            return;
        }
    }

}
