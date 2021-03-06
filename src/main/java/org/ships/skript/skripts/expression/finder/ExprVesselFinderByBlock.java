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
import org.core.world.position.impl.sync.SyncBlockPosition;
import org.ships.exceptions.load.LoadVesselException;
import org.ships.implementation.bukkit.world.position.impl.sync.BBlockPosition;
import org.ships.vessel.common.loader.ShipsBlockFinder;
import org.ships.vessel.common.types.Vessel;
import org.ships.vessel.common.types.typical.ShipsVessel;

@Name("Ships - Ship at Block")
@Description("Returns the ship at Block")
@Examples({"send \"%get the ship at Block%\""})
public class ExprVesselFinderByBlock extends SimpleExpression<ShipsVessel> {

    static {
        Skript.registerExpression(ExprVesselFinderByBlock.class, ShipsVessel.class, ExpressionType.COMBINED, "[get] [the] [Ships] ship at %block%");
    }

    private Expression<Block> block;

    protected ShipsVessel[] get(Event event) {
        Block block = this.block.getSingle(event);
        if(block == null){
            return null;
        }
        SyncBlockPosition position = new BBlockPosition(block);
        try {
            Vessel vessel = new ShipsBlockFinder(position).load();
            if(vessel instanceof ShipsVessel){
                return new ShipsVessel[]{(ShipsVessel) vessel};
            }
            return null;
        } catch (LoadVesselException e) {
        }
        return null;
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends ShipsVessel> getReturnType() {
        return ShipsVessel.class;
    }

    public String toString(Event event, boolean b) {
        return "Ship at block " + this.block.toString(event, b);
    }

    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.block = (Expression<Block>) expressions[0];
        return true;
    }
}
