package net.thundercode.block.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public final class ChatUtil {

    public static String colour(final String toColour){
        return ChatColor.translateAlternateColorCodes('&', toColour);
    }

    public static List<String> colour(final List<String> toColour){
        return toColour.stream()
                .map(ChatUtil::colour)
                .collect(Collectors.toList());
    }

    public static void sendMessage(final Player player, String message){
        player.sendMessage(colour(message));
    }

    private ChatUtil() {
    }
}
