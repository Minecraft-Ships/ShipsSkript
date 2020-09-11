package org.ships.skript;

import org.core.platform.Plugin;

public class ShipsSkriptCorePlugin implements Plugin {
    @Override
    public String getPluginName() {
        return "ShipsSkript";
    }

    @Override
    public String getPluginId() {
        return "shipsskript";
    }

    @Override
    public String getPluginVersion() {
        return "1.0.0.0";
    }

    @Override
    public Object getLauncher() {
        return ShipsSkript.getPlugin();
    }
}
