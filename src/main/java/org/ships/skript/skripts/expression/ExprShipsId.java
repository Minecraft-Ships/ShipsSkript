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
import org.core.utils.Identifable;
import org.ships.exceptions.load.LoadVesselException;
import org.ships.vessel.common.loader.ShipsIDFinder;
import org.ships.vessel.common.types.Vessel;


@Name("Ships - Id of Ship")
@Description("Returns the id of the ship")
@Examples({"send \"%get the ships id%\""})
public class ExprShipsId extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprShipsId.class, String.class, ExpressionType.COMBINED, "[the] [ship] id of %string%");
    }

    private Expression<String> vesselId;

    @Override
    protected String[] get(Event event) {
        String vesselId = this.vesselId.getSingle(event);
        if(vesselId == null){
            return null;
        }
        Vessel vessel;
        try {
            vessel = new ShipsIDFinder(vesselId).load();
        } catch (LoadVesselException e) {
            return null;
        }
        if(!(vessel instanceof Identifable)){
            return null;
        }
        Identifable identifable = (Identifable) vessel;
        return new String[]{identifable.getId()};
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
        return this.vesselId.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.vesselId = (Expression<String>) expressions[0];
        return false;
    }
}
