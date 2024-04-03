package org.maus.welcomereward.listeners;

import org.maus.welcomereward.welcomereward;
import org.maus.welcomereward.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MessageListener implements Listener {
    public MessageListener() {
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            if (welcomereward.getInstance().isActive()) {
                welcomereward.getInstance().getRewardHandler().processNewJoin();
                welcomereward.getInstance().getConfig().set("welcome.num-of-joins", welcomereward.getInstance().getConfig().getInt("welcome.num-of-joins") + 1);
                int joins = welcomereward.getInstance().getConfig().getInt("welcome.num-of-joins");
                welcomereward.getInstance().getConfig().getStringList("welcome.join-messages").forEach((str) -> {
                    Bukkit.broadcastMessage(CC.translate(str.replaceAll("%player%", event.getPlayer().getName()).replaceAll("%num%", String.valueOf(joins))));
                });
            }
        }
    }

    @EventHandler
    public static void onChat(AsyncPlayerChatEvent event) {
        if (welcomereward.getInstance().isActive()) {
            String message = ChatColor.stripColor(event.getMessage().toLowerCase());
            long time;
            if (welcomereward.getInstance().getConfig().getStringList("welcome.acceptable-messages").contains(message)) {
                time = (long)welcomereward.getInstance().getConfig().getInt("welcome.time") * 1000L;
                if (welcomereward.getInstance().getRewardHandler().getLastJoinedTime() != 0L && System.currentTimeMillis() - time <= welcomereward.getInstance().getRewardHandler().getLastJoinedTime() && !welcomereward.getInstance().getRewardHandler().getPlayerWelcomeList().contains(event.getPlayer())) {
                    welcomereward.getInstance().getConfig().getStringList("welcome.rewards.messages").forEach((str) -> {
                        event.getPlayer().sendMessage(CC.translate(str));
                    });
                    welcomereward.getInstance().getConfig().getStringList("welcome.rewards.commands").forEach((cmd) -> {
                        Bukkit.getScheduler().runTaskLater(welcomereward.getInstance(), () -> {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("%player%", event.getPlayer().getName()));
                        }, 0L);
                    });
                    welcomereward.getInstance().getRewardHandler().getPlayerWelcomeList().add(event.getPlayer());
                } else {
                    welcomereward.getInstance().getConfig().getStringList("welcome.failed-messages").forEach((str) -> {
                        event.getPlayer().sendMessage(CC.translate(str));
                    });
                }
            }

            if (welcomereward.getInstance().getConfig().getStringList("ggwave.acceptable-messages").contains(message)) {
                time = (long)welcomereward.getInstance().getConfig().getInt("ggwave.time") * 1000L;
                if (welcomereward.getInstance().getRewardHandler().getLastGGTime() != 0L && System.currentTimeMillis() - time <= welcomereward.getInstance().getRewardHandler().getLastGGTime() && !welcomereward.getInstance().getRewardHandler().getPlayerGGList().contains(event.getPlayer())) {
                    welcomereward.getInstance().getConfig().getStringList("ggwave.rewards.messages").forEach((str) -> {
                        event.getPlayer().sendMessage(CC.translate(str));
                    });
                    welcomereward.getInstance().getConfig().getStringList("ggwave.rewards.commands").forEach((cmd) -> {
                        Bukkit.getScheduler().runTaskLater(welcomereward.getInstance(), () -> {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("%player%", event.getPlayer().getName()));
                        }, 0L);
                    });
                    welcomereward.getInstance().getRewardHandler().getPlayerGGList().add(event.getPlayer());
                } else {
                    welcomereward.getInstance().getConfig().getStringList("ggwave.failed-messages").forEach((str) -> {
                        event.getPlayer().sendMessage(CC.translate(str));
                    });
                }
            }

        }
    }
}