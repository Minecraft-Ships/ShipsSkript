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
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.core.utils.Identifable;
import org.core.world.position.impl.sync.SyncBlockPosition;
import org.ships.exceptions.load.LoadVesselException;
import org.ships.implementation.bukkit.world.position.impl.sync.BBlockPosition;
import org.ships.vessel.common.loader.ShipsBlockFinder;
import org.ships.vessel.common.types.Vessel;

@Name("Ships - Ship at Block")
@Description("Returns the ship at Block")
@Examples({"send \"%get the ship at Block%\""})
public class ExprVesselFinderByBlock extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprVesselFinderByBlock.class, String.class, ExpressionType.COMBINED, "[get] [the] [Ships] ship at %block%");
    }

    private Expression<Block> block;

    protected String[] get(Event event) {
        Block block = this.block.getSingle(event);
        if(block == null){
            return null;
        }
        SyncBlockPosition position = new BBlockPosition(block);
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
        return "Ship at block " + this.block.toString(event, b);
    }

    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.block = (Expression<Block>) expressions[0];
        return true;
    }
}
