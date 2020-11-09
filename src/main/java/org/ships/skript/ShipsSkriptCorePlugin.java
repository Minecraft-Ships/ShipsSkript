package org.ships.skript;

import org.core.command.CommandRegister;
import org.core.config.ConfigurationStream;
import org.core.platform.Plugin;
import org.ships.implementation.bukkit.configuration.YAMLConfigurationFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

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
    public void registerCommands(CommandRegister register) {

    }

    @Override
    public ShipsSkript getLauncher() {
        return ShipsSkript.getPlugin();
    }

    @Override
    public Optional<ConfigurationStream.ConfigurationFile> createConfig(String configName, File file) {
        InputStream stream = this.getLauncher().getResource(configName);
        if(stream == null){
            System.err.println("Request for '" + configName + "' could not be found");
            return Optional.empty();
        }
        try {
            file.getParentFile().mkdirs();
            Files.copy(stream, file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(new YAMLConfigurationFile(file));
    }
}
