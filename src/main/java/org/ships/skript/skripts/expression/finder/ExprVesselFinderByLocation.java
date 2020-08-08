package org.ships.skript.skripts.expression.finder;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.core.utils.Identifable;
import org.core.world.position.impl.sync.SyncBlockPosition;
import org.ships.exceptions.load.LoadVesselException;
import org.ships.implementation.bukkit.world.position.impl.sync.BBlockPosition;
import org.ships.vessel.common.loader.ShipsBlockFinder;
import org.ships.vessel.common.types.Vessel;

@Name("Ships - Ship at Location")
@Description("Returns the ship at location")
@Examples({"send \"%get the ship at location%\""})
public class ExprVesselFinderByLocation extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprVesselFinderByLocation.class, String.class, ExpressionType.COMBINED, "[get] [the] [Ships] ship at %location%");
    }

    private Expression<Location> location;

    protected String[] get(Event event) {
        Location location = this.location.getSingle(event);
        if(location == null){
            return null;
        }
        SyncBlockPosition position = new BBlockPosition(location.getBlock());
        try {
            Vessel vessel = new ShipsBlockFinder(position).load();
            if(vessel instanceof Identifable){
                return new String[]{((Identifable) vessel).getId()};
            }
            return new String[] {vessel.getName()};
        } catch (LoadVesselException e) {
        }
        return null;
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends String> getReturnType() {
        return String.class;
    }

    public String toString(Event event, boolean b) {
        return "Ship at location " + location.toString(event, b);
    }

    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.location = (Expression<Location>) expressions[0];
        return true;
    }
}
