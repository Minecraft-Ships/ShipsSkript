package org.ships.skript.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.core.event.Event;
import org.ships.event.vessel.VesselStructureUpdate;
import org.ships.event.vessel.create.VesselCreateEvent;
import org.ships.event.vessel.move.VesselMoveEvent;

public abstract class EventWrapper <E extends Event> extends org.bukkit.event.Event {

    public static class VesselMovePreWrapper extends EventWrapper<VesselMoveEvent.Pre> implements Cancellable {

        public VesselMovePreWrapper(VesselMoveEvent.Pre event) {
            super(event);
        }

        @Override
        public boolean isCancelled() {
            return this.getCoreEvent().isCancelled();
        }

        @Override
        public void setCancelled(boolean b) {
            this.getCoreEvent().setCancelled(b);
        }
    }

    public static class VesselStructureUpdateWrapper extends EventWrapper<VesselStructureUpdate> implements Cancellable{

        public VesselStructureUpdateWrapper(VesselStructureUpdate event) {
            super(event);
        }

        @Override
        public boolean isCancelled() {
            return this.getCoreEvent().isCancelled();
        }

        @Override
        public void setCancelled(boolean b) {
            this.getCoreEvent().setCancelled(b);
        }
    }

    public static class VesselCreatePreBySignWrapper extends EventWrapper<VesselCreateEvent.Pre.BySign> implements Cancellable{

        public VesselCreatePreBySignWrapper(VesselCreateEvent.Pre.BySign event) {
            super(event);
        }

        @Override
        public boolean isCancelled() {
            return this.getCoreEvent().isCancelled();
        }

        @Override
        public void setCancelled(boolean b) {
            this.getCoreEvent().setCancelled(b);
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
