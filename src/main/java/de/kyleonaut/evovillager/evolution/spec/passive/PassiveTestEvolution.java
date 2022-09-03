package de.kyleonaut.evovillager.evolution.spec.passive;

import de.kyleonaut.evovillager.evolution.api.*;
import de.kyleonaut.evovillager.villager.EvoVillager;

@Name("PassiveTestEvolution")
@Schedule(5)
@Type(EvolutionType.PASSIVE)
public class PassiveTestEvolution implements Evolution {
    @Override
    public void execute(EvoVillager evoVillager) {
    }
}
