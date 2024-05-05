package org.maus.welcomereward.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.maus.welcomereward.utils.CC;
import org.maus.welcomereward.welcomereward;

public class TestCommand {
    public TestCommand() {
    }

    @Command(
            name = "test",
            desc = "Send a fake new player join message",
            async = true
    )

    @Require("welcomereward.test")
    public static void onCommand(@Sender Player sender) {
        if (!welcomereward.getInstance().isActive()) {
            sender.sendMessage(CC.translate("&cPlugin disabled."));
        } else {
            welcomereward.getInstance().getRewardHandler().processNewJoin();
            welcomereward.getInstance().getConfig().getStringList("welcome.join-messages").forEach((str) -> {
                Bukkit.broadcastMessage(
                        CC.translate(
                                str.replaceAll("%player%", "Test_Event_Player")
                                        .replaceAll("%num%", String.valueOf(6969))
                        )
                );
            });

        }
    }
}
