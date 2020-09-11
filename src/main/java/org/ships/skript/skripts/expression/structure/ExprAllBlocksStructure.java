package org.ships.skript.skripts.expression.structure;

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
import org.ships.implementation.bukkit.world.position.impl.sync.BBlockPosition;
import org.ships.skript.skripts.expression.ExprShipsStructure;
import org.ships.vessel.structure.PositionableShipsStructure;

import java.util.Collection;

@Name("Ships - All blocks")
@Description("Gets all blocks from the structure")
@Examples({"send \"loop all from structure\""})
public class ExprAllBlocksStructure extends SimpleExpression<Block> {

    static {
        Skript.registerExpression(ExprAllBlocksStructure.class, Block.class, ExpressionType.COMBINED, "[get] [all] (of | from) %structure%");
    }

    private Expression<PositionableShipsStructure> structures;

    @Override
    protected Block[] get(Event event) {
        PositionableShipsStructure pss = this.structures.getSingle(event);
        int A = 0;
        Collection<SyncBlockPosition> positions = pss.getPositions();
        Block[] blocks = new Block[positions.size()];
        for(SyncBlockPosition pos : positions){
            blocks[A] = ((BBlockPosition)pos).getBukkitBlock();
            A++;
        }
        return blocks;
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
        return "Blocks from " + this.structures.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.structures = (Expression<PositionableShipsStructure>) expressions[0];
        return true;
    }
}
