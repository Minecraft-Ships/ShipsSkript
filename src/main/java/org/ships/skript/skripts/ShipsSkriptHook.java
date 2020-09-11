package org.ships.skript.skripts;


import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import org.core.utils.Identifable;
import org.ships.implementation.bukkit.entity.living.human.player.live.BLivePlayer;
import org.ships.movement.MovementContext;
import org.ships.skript.event.EventWrapper;
import org.ships.skript.event.SkriptShipsGenericCustomEvent;
import org.ships.vessel.common.types.Vessel;
import org.ships.vessel.common.types.typical.ShipsVessel;
import org.ships.vessel.structure.PositionableShipsStructure;

public class ShipsSkriptHook {

    static {
        Skript
                .registerEvent("Ships - Pre move", SkriptShipsGenericCustomEvent.class, EventWrapper.VesselMovePreWrapper.class, "[Ships] ship pre move")
                .description("Called when a ship is about to move")
                .examples("on ship pre move");
        EventValues.registerEventValue(EventWrapper.VesselMovePreWrapper.class, ShipsVessel.class, new Getter<ShipsVessel, EventWrapper.VesselMovePreWrapper>() {
            @Override
            public ShipsVessel get(EventWrapper.VesselMovePreWrapper event) {
                Vessel vessel = event.getCoreEvent().getVessel();
                if(vessel instanceof ShipsVessel){
                    return (ShipsVessel)vessel;
                }
                return null;
            }
        }, 0);
        EventValues.registerEventValue(EventWrapper.VesselMovePreWrapper.class, MovementContext.class, new Getter<MovementContext, EventWrapper.VesselMovePreWrapper>() {
            @Override
            public MovementContext get(EventWrapper.VesselMovePreWrapper event) {
                return event.getCoreEvent().getContext();
            }
        }, 0);
        Skript
                .registerEvent("Ships - Pre Create By Sign", SkriptShipsGenericCustomEvent.class, EventWrapper.VesselCreatePreBySignWrapper.class, "[Ships] ship pre create")
                .description("Called when a ship is about to be created")
                .examples("on ship pre create");
        EventValues.registerEventValue(EventWrapper.VesselCreatePreBySignWrapper.class, ShipsVessel.class, new Getter<ShipsVessel, EventWrapper.VesselCreatePreBySignWrapper>() {

            @Override
            public ShipsVessel get(EventWrapper.VesselCreatePreBySignWrapper event) {
                Vessel vessel = event.getCoreEvent().getVessel();
                if(vessel instanceof ShipsVessel){
                    return (ShipsVessel)vessel;
                }
                return null;
            }
        }, 0);
        EventValues.registerEventValue(EventWrapper.VesselCreatePreBySignWrapper.class, Player.class, new Getter<Player, EventWrapper.VesselCreatePreBySignWrapper>() {
            @Override
            public Player get(EventWrapper.VesselCreatePreBySignWrapper event) {
                return ((BLivePlayer)event.getCoreEvent().getEntity()).getBukkitEntity();
            }
        }, 0);
        Skript
                .registerEvent("Ships - Post Create By Sign", SkriptShipsGenericCustomEvent.class, EventWrapper.VesselCreatePostBySignWrapper.class, "[Ships] ship [post] creat(ion | e)")
                .description("Called when a ship is created")
                .examples("on ship creation");
        EventValues.registerEventValue(EventWrapper.VesselCreatePostBySignWrapper.class, ShipsVessel.class, new Getter<ShipsVessel, EventWrapper.VesselCreatePostBySignWrapper>() {
            @Override
            public ShipsVessel get(EventWrapper.VesselCreatePostBySignWrapper event) {
                Vessel vessel = event.getCoreEvent().getVessel();
                if(vessel instanceof ShipsVessel){
                    return (ShipsVessel)vessel;
                }
                return null;
            }
        }, 0);
        EventValues.registerEventValue(EventWrapper.VesselCreatePostBySignWrapper.class, Player.class, new Getter<Player, EventWrapper.VesselCreatePostBySignWrapper>() {
            @Override
            public Player get(EventWrapper.VesselCreatePostBySignWrapper event) {
                return ((BLivePlayer)event.getCoreEvent().getEntity()).getBukkitEntity();
            }
        }, 0);
        Skript
                .registerEvent("Ships - structure update", SkriptShipsGenericCustomEvent.class, EventWrapper.VesselStructureUpdateWrapper.class, "[Ships] [ship] structure update")
                .description("Called when a ships structure is update")
                .examples("on ship structure update");
        EventValues.registerEventValue(EventWrapper.VesselStructureUpdateWrapper.class, ShipsVessel.class, new Getter<ShipsVessel, EventWrapper.VesselStructureUpdateWrapper>() {
            @Override
            public ShipsVessel get(EventWrapper.VesselStructureUpdateWrapper event) {
                Vessel vessel = event.getCoreEvent().getVessel();
                if(vessel instanceof ShipsVessel){
                    return (ShipsVessel)vessel;
                }
                return null;
            }
        }, 0);
        EventValues.registerEventValue(EventWrapper.VesselStructureUpdateWrapper.class, PositionableShipsStructure.class, new Getter<PositionableShipsStructure, EventWrapper.VesselStructureUpdateWrapper>() {

            @Override
            public PositionableShipsStructure get(EventWrapper.VesselStructureUpdateWrapper event) {
                return event.getCoreEvent().getNewStructure();
            }
        }, 0);
    }
}
