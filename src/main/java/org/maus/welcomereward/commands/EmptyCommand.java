package org.maus.welcomereward.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Require;
import com.jonahseguin.drink.annotation.Sender;
import org.bukkit.entity.Player;
import org.maus.welcomereward.utils.CC;
import org.maus.welcomereward.welcomereward;

public class EmptyCommand {
    public EmptyCommand() {
    }

    @Command(
            name = "",
            desc = "Plugin Command",
            async = true
    )

    @Require("welcomereward.usage")
    public static void onCommand(@Sender Player sender) {
        sender.sendMessage(CC.translate("&cUsage: /wr <reload|test>"));
    }
}