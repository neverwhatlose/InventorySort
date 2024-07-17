package org.nwtls.inventorysort;

import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.nwtls.inventorysort.sorting.SortCommand;

import java.util.function.Function;

public class InventorySortPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        PaperCommandManager<CommandSender> commandManager;
        try {
            commandManager = new PaperCommandManager<>(
                    this,
                    CommandExecutionCoordinator.simpleCoordinator(),
                    Function.identity(),
                    Function.identity()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        new SortCommand(commandManager);
    }

    @Override
    public void onDisable() {

    }
}
