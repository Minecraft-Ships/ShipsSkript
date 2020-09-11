package org.ships.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.array.utils.ArrayUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.core.CorePlugin;
import org.core.world.position.impl.sync.SyncBlockPosition;
import org.ships.movement.MovementContext;
import org.ships.plugin.ShipsPlugin;
import org.ships.skript.event.EventListenerWrapper;
import org.ships.vessel.common.types.ShipType;
import org.ships.vessel.common.types.typical.ShipsVessel;
import org.ships.vessel.structure.PositionableShipsStructure;

import java.io.IOException;

public class ShipsSkript extends JavaPlugin {

    private SkriptAddon addon;
    private ShipsSkriptCorePlugin corePlugin;

    private static ShipsSkript plugin;

    @Override
    public void onEnable(){
        plugin = this;
        this.corePlugin = new ShipsSkriptCorePlugin();
        this.addon = Skript.registerAddon(this);
        try {
            this.addon.loadClasses("org.ships.skript.skripts");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CorePlugin.getEventManager().register(this.corePlugin, new EventListenerWrapper());
        Classes.registerClass(new ClassInfo<>(MovementContext.class, "context")
                .user("context")
                .name("Context")
                .description("The context for movement")
                .examples("event-context")
                .parser(new Parser<MovementContext>() {
                    @Override
                    public MovementContext parse(final String s, final ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(final ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(MovementContext shipsVessel, int i) {
                        return this.toVariableNameString(shipsVessel);
                    }

                    @Override
                    public String toVariableNameString(MovementContext shipsVessel) {
                        return "Movement-Context";
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "Movement-Context";
                    }
                })

        );
        Classes.registerClass(new ClassInfo<>(ShipsVessel.class, "vessel")
                .user("vessel")
                .name("Vessel")
                .description("The ship")
                .examples("get the ship at %location%")
                .parser(new Parser<ShipsVessel>() {

                    @Override
                    public ShipsVessel parse(final String s, final ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(final ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(ShipsVessel shipsVessel, int i) {
                        return this.toVariableNameString(shipsVessel);
                    }

                    @Override
                    public String toVariableNameString(ShipsVessel shipsVessel) {
                        return shipsVessel.getId();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "(" + ArrayUtils.toString("|", t -> t.getId(), ShipsPlugin.getPlugin().getAll(ShipType.class)) + ")([a-z0-9]+)$";
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(PositionableShipsStructure.class, "structure")
                .user("structure")
                .name("Structure")
                .description("The ships blocks")
                .examples("event-structure")
                .parser(new Parser<PositionableShipsStructure>() {

                    @Override
                    public PositionableShipsStructure parse(final String s, final ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(final ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(PositionableShipsStructure positionableShipsStructure, int i) {
                        return this.toVariableNameString(positionableShipsStructure);
                    }

                    @Override
                    public String toVariableNameString(PositionableShipsStructure positionableShipsStructure) {
                        SyncBlockPosition position = positionableShipsStructure.getPosition();
                        return "X: " + position.getX() + " Y: " + position.getY() + " Z: " + position.getZ() + " Size: " + positionableShipsStructure.getPositions().size() + " World: " + position.getWorld().getName();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return "X: [0-9] Y: [0-9] Z: [0-9] Size: [0-9] World: ([a-z0-9]+)$";
                    }
                })
        );
    }

    public static ShipsSkript getPlugin(){
        return plugin;
    }
}
