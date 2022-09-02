package de.kyleonaut.evovillager.evolution.passive;

import de.kyleonaut.evovillager.villager.EvoVillager;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class PassiveEvolution {
    @Setter
    private String name;

    public abstract void invoke(EvoVillager evoVillager);
}
