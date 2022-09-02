package de.kyleonaut.evovillager.event;

import de.kyleonaut.evovillager.villager.EvoVillager;
import lombok.Getter;
import lombok.Setter;

public class EvoVillagerDamageByEntityEvent extends EvolutionEvent {
    @Getter
    @Setter
    private boolean canceled = false;

    public EvoVillagerDamageByEntityEvent(EvoVillager evoVillager) {
        super(evoVillager);
    }
}
