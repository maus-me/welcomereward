package org.maus.welcomereward.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import org.bukkit.entity.Player;
import org.maus.welcomereward.utils.CC;
import org.maus.welcomereward.welcomereward;

public class ReloadCommand {
    public ReloadCommand() {
    }

    @Command(
            name = "reload",
            desc = "Reload Plugin Command",
            async = true
    )

    @Require("welcomereward.reload")
    public static void onCommand(@Sender Player sender) {
        long time = System.currentTimeMillis();
        welcomereward.getInstance().reloadConfig();
        welcomereward.getInstance().setActive(welcomereward.getInstance().getConfig().getBoolean("enabled"));
        sender.sendMessage(CC.translate("&cPlugin reloaded in " + (System.currentTimeMillis() - time) + " ms."));
    }
}
