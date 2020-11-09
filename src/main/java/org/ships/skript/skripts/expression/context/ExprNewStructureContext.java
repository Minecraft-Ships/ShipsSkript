package org.ships.skript.skripts.expression.context;

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
import org.ships.implementation.bukkit.world.position.impl.sync.BBlockPosition;
import org.ships.movement.MovementContext;

import java.util.HashSet;
import java.util.Set;

@Name("Ships - get new structure")
@Description("Gets the new potential structure")
@Examples({"send \"loop all new blocks from %context%\""})
public class ExprNewStructureContext extends SimpleExpression<Block> {

    static {
        Skript.registerExpression(ExprNewStructureContext.class, Block.class, ExpressionType.COMBINED, "[get] [the] [new] (blocks | structure) (in | from) [Ships] %context%");
    }

    private Expression<MovementContext> context;

    @Override
    protected Block[] get(Event event) {
        MovementContext context = this.context.getSingle(event);
        if(context == null){
            return null;
        }
        Set<Block> set = new HashSet<>();
        context.getMovingStructure().forEach(m -> set.add(((BBlockPosition)m).getBukkitBlock()));
        return set.toArray(new Block[0]);
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
        return "new structure of " + this.context.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.context = (Expression<MovementContext>) expressions[0];
        return true;
    }
}
