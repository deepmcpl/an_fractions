package pl.animekkk.fractions.fraction.util;

import net.minecraft.server.v1_16_R3.*;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class TagUtil {

    private static final Scoreboard scoreboard = new Scoreboard();

    public static ScoreboardTeam createBoard(Player player) {
        ScoreboardTeam team = scoreboard.getPlayerTeam(player.getName());
        if(team == null) team = scoreboard.createTeam(player.getName());
        scoreboard.addPlayerToTeam(player.getName(), team);
        PacketPlayOutScoreboardTeam teamPacket = new PacketPlayOutScoreboardTeam(team, 0);
        Bukkit.getOnlinePlayers().forEach(players -> {
            PlayerConnection connection = ((CraftPlayer)players).getHandle().playerConnection;
            connection.sendPacket(teamPacket);
            if(players != player) {
                ScoreboardTeam playersTeam = scoreboard.getPlayerTeam(players.getName());
                if(playersTeam != null)
                    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutScoreboardTeam(playersTeam, 0));
            }
        });
        return team;
    }

    public static void removeBoard(Player player) {
        ScoreboardTeam team = scoreboard.getPlayerTeam(player.getName());
        if(team == null) return;
        scoreboard.removePlayerFromTeam(player.getName(), team);
        PacketPlayOutScoreboardTeam teamPacket = new PacketPlayOutScoreboardTeam(team, 1);
        Bukkit.getOnlinePlayers().forEach(players -> {
            PlayerConnection connection = ((CraftPlayer)players).getHandle().playerConnection;
            connection.sendPacket(teamPacket);
            ScoreboardTeam playersTeam = scoreboard.getPlayerTeam(players.getName());
            if(playersTeam != null) connection.sendPacket(new PacketPlayOutScoreboardTeam(playersTeam, 1));
        });
        scoreboard.removeTeam(team);
    }

    public static void updateBoard(Player player) {
        ScoreboardTeam team = scoreboard.getPlayerTeam(player.getName());
        if(team == null) team = createBoard(player);
        User user = UserManager.getUser(player.getUniqueId());
        for(Player players : Bukkit.getOnlinePlayers()) {
            String color = getColor(user, UserManager.getUser(players.getUniqueId()));
            team.setPrefix(IChatBaseComponent.ChatSerializer.a(
                    "{\"text\": \"" + getPrefix(user, color) + "\"}"
            ));
            PacketPlayOutScoreboardTeam teamPacket = new PacketPlayOutScoreboardTeam(team, 2);
            ((CraftPlayer)players).getHandle().playerConnection.sendPacket(teamPacket);
        }
    }

    public static void updateAll() {
        Bukkit.getOnlinePlayers().forEach(TagUtil::updateBoard);
    }

    private static String getPrefix(User user, String color) {
        if(user.getFraction() == null) return "";
        return ChatUtil.format(color + user.getFraction().getTag());
    }

    private static String getColor(User user, User other) {
        return ChatUtil.format(RelationUtil.getRelation(user, other).getColor());
    }

}
