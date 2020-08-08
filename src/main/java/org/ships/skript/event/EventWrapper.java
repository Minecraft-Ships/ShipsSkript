package org.ships.skript.event;

import org.bukkit.event.HandlerList;
import org.core.event.Event;
import org.ships.event.vessel.VesselStructureUpdate;
import org.ships.event.vessel.create.VesselCreateEvent;

public abstract class EventWrapper <E extends Event> extends org.bukkit.event.Event {

    public static class VesselStructureUpdateWrapper extends EventWrapper<VesselStructureUpdate> {

        public VesselStructureUpdateWrapper(VesselStructureUpdate event) {
            super(event);
        }
    }

    public static class VesselCreatePreBySignWrapper extends EventWrapper<VesselCreateEvent.Pre.BySign>{

        public VesselCreatePreBySignWrapper(VesselCreateEvent.Pre.BySign event) {
            super(event);
        }
    }

    public static class VesselCreatePostBySignWrapper extends EventWrapper<VesselCreateEvent.Post.BySign>{

        public VesselCreatePostBySignWrapper(VesselCreateEvent.Post.BySign event) {
            super(event);
        }
    }

    private static final HandlerList HANDLER = new HandlerList();

    private E event;

    public EventWrapper(E event){
        this.event = event;
    }

    public E getCoreEvent(){
        return this.event;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER;
    }

    public static HandlerList getHandlerList(){
        return HANDLER;
    }


}
