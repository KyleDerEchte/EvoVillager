package de.kyleonaut.evovillager.evolution.active;

import de.kyleonaut.evovillager.event.EvolutionEvent;
import de.kyleonaut.evovillager.handler.EvolutionHandler;
import de.kyleonaut.evovillager.villager.EvoVillager;
import lombok.Getter;
import lombok.Setter;

public abstract class Evolution {
    @Setter
    @Getter
    private String name;

    public abstract void invoke(EvoVillager evoVillager);

    public void bind(Class<? extends EvolutionEvent> event) {
        EvolutionHandler.getInstance().bind(this, event);
    }
}
