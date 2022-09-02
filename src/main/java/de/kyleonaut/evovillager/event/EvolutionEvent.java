package de.kyleonaut.evovillager.event;

import de.kyleonaut.evovillager.villager.EvoVillager;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class EvolutionEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private final EvoVillager evoVillager;

    public EvolutionEvent(EvoVillager evoVillager) {
        this.evoVillager = evoVillager;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
