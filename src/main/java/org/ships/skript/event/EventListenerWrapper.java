package org.ships.skript.event;

import org.bukkit.Bukkit;
import org.core.event.EventListener;
import org.core.event.HEvent;
import org.ships.event.vessel.VesselStructureUpdate;
import org.ships.event.vessel.create.VesselCreateEvent;

public class EventListenerWrapper implements EventListener {

    @HEvent
    public void OnPreSignCreateVesselEvent(VesselCreateEvent.Pre.BySign event){
        Bukkit.getPluginManager().callEvent(new EventWrapper.VesselCreatePreBySignWrapper(event));
    }

    @HEvent
    public void OnPostSignCreateVesselEvent(VesselCreateEvent.Post.BySign event){
        Bukkit.getPluginManager().callEvent(new EventWrapper.VesselCreatePostBySignWrapper(event));
    }

    @HEvent
    public void OnVesselStructureUpdate(VesselStructureUpdate event){
        Bukkit.getPluginManager().callEvent(new EventWrapper.VesselStructureUpdateWrapper(event));
    }
}
