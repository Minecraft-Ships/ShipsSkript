package org.ships.skript.event;

import org.bukkit.Bukkit;
import org.core.event.EventListener;
import org.core.event.HEvent;
import org.ships.event.vessel.VesselStructureUpdate;
import org.ships.event.vessel.create.VesselCreateEvent;
import org.ships.event.vessel.move.VesselMoveEvent;

public class EventListenerWrapper implements EventListener {

    @HEvent
    public void onPreMoveVesselEvent(VesselMoveEvent.Pre event){
        Bukkit.getPluginManager().callEvent(new EventWrapper.VesselMovePreWrapper(event));
    }

    @HEvent
    public void onPreSignCreateVesselEvent(VesselCreateEvent.Pre.BySign event){
        Bukkit.getPluginManager().callEvent(new EventWrapper.VesselCreatePreBySignWrapper(event));
    }

    @HEvent
    public void onPostSignCreateVesselEvent(VesselCreateEvent.Post.BySign event){
        Bukkit.getPluginManager().callEvent(new EventWrapper.VesselCreatePostBySignWrapper(event));
    }

    @HEvent
    public void onVesselStructureUpdate(VesselStructureUpdate event){
        Bukkit.getPluginManager().callEvent(new EventWrapper.VesselStructureUpdateWrapper(event));
    }
}
