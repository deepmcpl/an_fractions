package pl.animekkk.fractions.commands;

import org.bukkit.command.CommandSender;

import java.util.Arrays;

public abstract class Command extends org.bukkit.command.Command{

    private final String name;
    private final String usage;
    private final String permission;
    private final String desc;
    private final String noPermissionMessage;

    public Command(String name, String permission, String usage, String[] aliases, String noPermission) {
        super(name, permission, usage, Arrays.asList(aliases));
        this.name = name;
        this.usage = usage;
        this.permission = permission;
        this.desc = " ";
        this.noPermissionMessage = noPermission;
    }

    public boolean execute(CommandSender commandSender, String label, String[] args)
    {
        if(permission == null || permission.equals(""))
        {
            return this.onExecute(commandSender, args);
        }
        if(!commandSender.hasPermission(this.permission))
        {
            commandSender.sendMessage(this.noPermissionMessage);
            return true;
        }
        return this.onExecute(commandSender, args);
    }

    public abstract boolean onExecute(CommandSender commandSender, String[] args);

    public String getName()
    {
        return this.name;
    }

    public String getUsage()
    {
        return this.usage;
    }

    public String getPermission()
    {
        return this.permission;
    }

    public String getDescription()
    {
        return this.desc;
    }


}