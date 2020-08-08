package org.ships.skript.skripts.expression;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.ships.exceptions.load.LoadVesselException;
import org.ships.vessel.common.loader.ShipsIDFinder;
import org.ships.vessel.common.types.Vessel;

@Name("Ships - get ship type")
@Description("Gets the id of the ship type from the provided ship")
@Examples({"send \"ship type of ship\""})
public class ExprShipsType extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprShipsType.class, String.class, ExpressionType.COMBINED, "[get] [the] [Ships] (ship type | shiptype) of %string%");
    }

    private Expression<String> vessel;

    @Override
    protected String[] get(Event event) {
        String shipsId = this.vessel.getSingle(event);
        if(shipsId == null){
            return null;
        }
        Vessel vessel;
        try {
            vessel = new ShipsIDFinder(shipsId).load();
        } catch (LoadVesselException e) {
            return null;
        }
        return new String[]{vessel.getType().getId()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "The type of ship that is " + this.vessel.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
