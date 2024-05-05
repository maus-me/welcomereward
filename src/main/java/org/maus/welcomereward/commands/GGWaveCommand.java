package org.maus.welcomereward.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.maus.welcomereward.utils.CC;
import org.maus.welcomereward.welcomereward;

public class GGWaveCommand {
    public GGWaveCommand() {
    }

    @Command(
            name = "",
            desc = "GG Wave Command",
            async = true
    )
    @Require("welcomereward.ggwave")
    public static void onCommand(@Sender Player sender, String name) {
        if (!welcomereward.getInstance().isActive()) {
            sender.sendMessage(CC.translate("&cPlugin disabled."));
        } else {
            welcomereward.getInstance().getRewardHandler().processNewGGWave();
            welcomereward.getInstance().getConfig().getStringList("ggwave.wave-messages").forEach((str) -> {
                Bukkit.broadcastMessage(CC.translate(str.replaceAll("%name%", name)));
            });
        }
    }
}
