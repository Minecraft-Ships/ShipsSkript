package org.ships.skript.skripts.expression;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;

public class ShipsSkriptMoveEvent extends SkriptEvent {
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return false;
    }

    @Override
    public boolean check(Event event) {
        return false;
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }
}
