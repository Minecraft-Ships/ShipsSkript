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
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.ships.implementation.bukkit.entity.BLiveEntity;
import org.ships.movement.MovementContext;

import java.util.HashSet;
import java.util.Set;

@Name("Ships - get entities")
@Description("The entities on the ship")
@Examples({"send \"loop all entities in %movement%\""})
public class ExprEntityContext extends SimpleExpression<Entity> {

    static {
        Skript.registerExpression(ExprEntityContext.class, Entity.class, ExpressionType.COMBINED, "[get] [all] [the] entities (in | from) [Ships] %context%");
    }

    private Expression<MovementContext> context;

    @Override
    protected Entity[] get(Event event) {
        MovementContext context = this.context.getSingle(event);
        if(context == null){
            return null;
        }
        Set<Entity> set = new HashSet<>();
        context.getEntities().keySet().stream().filter(e -> e.getCreatedFrom().isPresent()).forEach(e -> set.add(((BLiveEntity<?>)e.getCreatedFrom().get()).getBukkitEntity()));
        return set.toArray(new Entity[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "entities found in " + this.context.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.context = (Expression<MovementContext>) expressions[0];
        return true;
    }
}
