package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.Fractions;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.util.SettingsUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class SettingsCommand extends Command {

    public SettingsCommand() {
        super("ustawienia", "", "/ustawienia", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if(!Fractions.getFractionsConfig().getSettingsEnabled()) return ChatUtil.sendMessage(commandSender, "&7Ustawienia są wyłączone.");
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        SettingsUtil.createMenu(player, fraction);
        return true;
    }
}
