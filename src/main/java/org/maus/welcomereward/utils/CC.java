package org.maus.welcomereward.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CC {
    private static final Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");

    public CC() {
    }

    public static List<String> translate(List<String> message) {
        return message.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static String translate(String string) {
        for (Matcher match = pattern.matcher(string); match.find(); match = pattern.matcher(string)) {
            String color = string.substring(match.start() + 1, match.end());
            string = string.replace("&" + color, ChatColor.of(color) + "");
        }

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String toColorHex(String message) {
        Pattern hexPattern = Pattern.compile("<#([A-Fa-f0-9]){6}>");

        for (Matcher matcher = hexPattern.matcher(message); matcher.find(); matcher = hexPattern.matcher(message)) {
            org.bukkit.ChatColor hexColor = org.bukkit.ChatColor.valueOf(matcher.group().substring(1, matcher.group().length() - 1));
            String before = message.substring(0, matcher.start());
            String after = message.substring(matcher.end());
            message = before + hexColor + after;
        }

        return translate(message);
    }
}
