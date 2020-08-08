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
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.ships.exceptions.load.LoadVesselException;
import org.ships.implementation.bukkit.world.position.impl.sync.BBlockPosition;
import org.ships.vessel.common.loader.ShipsIDFinder;
import org.ships.vessel.common.types.Vessel;

@Name("Ships - ships location")
@Description("Gets the location of the ship")
@Examples({"send \"get %ship% location\""})
public class ExprShipsPosition extends SimpleExpression<Block> {

    static {
        Skript.registerExpression(ExprShipsPosition.class, Block.class, ExpressionType.COMBINED, "[get] [the] [Ships] (position | location) of %string%");
    }

    private Expression<String> vessel;

    @Override
    protected Block[] get(Event event) {
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
        return new Block[]{((BBlockPosition)vessel.getPosition()).getBukkitBlock()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<Block> getReturnType() {
        return Block.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "Block position of ship " + vessel.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.vessel = (Expression<String>) expressions[0];
        return true;
    }
}
