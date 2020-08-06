package org.ships.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class ShipsSkript extends JavaPlugin {

    private SkriptAddon addon;

    @Override
    public void onEnable(){
        this.addon = Skript.registerAddon(this);
        try {
            this.addon.loadClasses("org.ships.skript.skripts");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
