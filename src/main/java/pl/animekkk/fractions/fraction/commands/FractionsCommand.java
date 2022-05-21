package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import pl.animekkk.fractions.Fractions;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.user.util.ChatUtil;

public class FractionsCommand extends Command {

    public FractionsCommand() {
        super("frakcje", "", "/frakcje", new String[0], "");
    }

    public boolean onExecute(CommandSender commandSender, String[] args) {
        return ChatUtil.sendMessage(commandSender,
                (Fractions.getFractionsConfig().getSettingsEnabled() ? "&3/settings &8- &7Fraction settings.\n" : "") +
                "&3/stworz <tag> <nazwa> &8- &7Tworzenie frakcji.\n" +
                "&3/zapros <nazwa> &8- &7Zapraszanie osoby do frakcji.\n" +
                "&3/wyrzuc <nazwa> &8- &7Wyrzucanie osoby z frakcji.\n" +
                "&3/dolacz <tag> &8- &7Dołączanie do frakcji.\n" +
                "&3/baza &8- &7Teleportowanie do bazy frakcji.\n" +
                "&3/ustawbaze &8- &7Ustawianie bazy frakcji.\n" +
                "&3/przedluz &8- &7Przedłużanie ważnosci frakcji.\n" +
                "&3/lider <nazwa> &8- &7Przekazywanie lidera frakcji.\n" +
                "&3/sojusz <tag> &8- &7Zapraszanie do sojuszu.\n" +
                "&3/zerwijsojusz <tag> &8- &7Zrywanie sojuszu.\n" +
                "&3/opusc &8- &7Opuszczanie frakcji.\n" +
                "&7Aby usunać frakcję należy użyć komendy &3/opusc&7.");
    }
}
