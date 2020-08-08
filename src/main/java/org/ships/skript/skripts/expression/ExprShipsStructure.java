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
import org.core.world.position.impl.BlockPosition;
import org.core.world.position.impl.sync.SyncBlockPosition;
import org.ships.exceptions.load.LoadVesselException;
import org.ships.implementation.bukkit.world.position.impl.sync.BBlockPosition;
import org.ships.vessel.common.loader.ShipsIDFinder;
import org.ships.vessel.common.types.Vessel;

import java.util.Collection;

@Name("Ships - get ships structure")
@Description("Gets the structure of the ship in the form of multiple block positions")
@Examples({"send \"get blocks of Ship\""})
public class ExprShipsStructure extends SimpleExpression<Block> {

    static {
        Skript.registerExpression(ExprShipsStructure.class, Block.class, ExpressionType.COMBINED, "[get] [the] [Ships] (blocks | positions | structure) of %string%");
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
        Collection<SyncBlockPosition> positions = vessel.getStructure().getPositions();
        Block[] array = new Block[positions.size()];
        int A = 0;
        for(BlockPosition position : positions){
            array[A] = ((BBlockPosition)position).getBukkitBlock();
            A++;
        }
        return array;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Block> getReturnType() {
        return Block.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "The structure of " + this.vessel.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.vessel = (Expression<String>) expressions[0];
        return true;
    }
}
