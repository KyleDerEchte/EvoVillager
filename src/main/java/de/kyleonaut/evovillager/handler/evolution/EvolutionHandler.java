package de.kyleonaut.evovillager.handler.evolution;

import de.kyleonaut.evovillager.EvoVillagerPlugin;
import de.kyleonaut.evovillager.event.EvolutionEvent;
import de.kyleonaut.evovillager.evolution.api.*;
import de.kyleonaut.evovillager.handler.handler.EvoVillagerHandler;
import de.kyleonaut.evovillager.villager.EvoVillager;
import org.bukkit.Bukkit;

import java.util.*;
import java.util.logging.Level;

public class EvolutionHandler {
    private static EvolutionHandler instance;
    private final Map<Class<? extends EvolutionEvent>, List<Evolution>> activeEvolutions = new HashMap<>();
    private final Map<String, Integer> passiveEvolutions = new HashMap<>();

    public static EvolutionHandler getInstance() {
        if (instance == null) {
            instance = new EvolutionHandler();
        }
        return instance;
    }

    private EvolutionHandler() {
    }

    public void register(Evolution evolution) {
        if (!evolution.getClass().isAnnotationPresent(Type.class)) {
            Bukkit.getLogger().log(Level.WARNING, "[EvoVillager] Tried to register an evolution with no type provided.");
            return;
        }
        if (evolution.getClass().getAnnotation(Type.class).value().equals(EvolutionType.ACTIVE)) {
            if (!evolution.getClass().isAnnotationPresent(Bind.class)) {
                Bukkit.getLogger().log(Level.WARNING, "[EvoVillager] Tried to register an active evolution with no event provided.");
                return;
            }
            if (!evolution.getClass().isAnnotationPresent(Name.class)) {
                Bukkit.getLogger().log(Level.WARNING, "[EvoVillager] Tried to register an active evolution with no name provided.");
                return;
            }
            final String providedName = evolution.getClass().getAnnotation(Name.class).value();
            final Class<? extends EvolutionEvent> providedEvent = evolution.getClass().getAnnotation(Bind.class).value();
            if (!this.activeEvolutions.containsKey(providedEvent)) {
                this.activeEvolutions.put(providedEvent, new ArrayList<>());
            }
            final List<Evolution> activeEvolutions = this.activeEvolutions.get(providedEvent);
            final List<String> evolutionNames = activeEvolutions.stream()
                    .map(it -> it.getClass().getAnnotation(Name.class).value())
                    .toList();
            if (!evolutionNames.contains(providedName)) {
                activeEvolutions.add(evolution);
            }
            return;
        }
        if (evolution.getClass().getAnnotation(Type.class).value().equals(EvolutionType.PASSIVE)) {
            if (!evolution.getClass().isAnnotationPresent(Name.class)) {
                Bukkit.getLogger().log(Level.WARNING, "[EvoVillager] Tried to register an passive evolution with no name provided.");
                return;
            }
            if (!evolution.getClass().isAnnotationPresent(Schedule.class)) {
                Bukkit.getLogger().log(Level.WARNING, "[EvoVillager] Tried to register an passive evolution with no schedule interval provided.");
                return;
            }
            final int interval = evolution.getClass().getAnnotation(Schedule.class).value();
            final String providedName = evolution.getClass().getAnnotation(Name.class).value();
            if (this.passiveEvolutions.containsKey(providedName)) {
                return;
            }
            this.passiveEvolutions.put(
                    providedName,
                    Bukkit.getScheduler().scheduleSyncRepeatingTask(
                            EvoVillagerPlugin.getPlugin(EvoVillagerPlugin.class),
                            () -> getEvoVillagersWithEvolution(evolution).forEach(evolution::execute),
                            0,
                            interval
                    )
            );
        }
    }

    public void execute(EvolutionEvent event) {
        if (this.activeEvolutions.containsKey(event.getClass())) {
            final List<Evolution> evolutions = this.activeEvolutions.get(event.getClass());
            evolutions.forEach(evolution -> getEvoVillagersWithEvolution(evolution).forEach(evolution::execute));
            return;
        }
        Bukkit.getLogger().log(Level.WARNING, "[EvoVillager] An event was called with no evolutions to execute");
    }

    private List<EvoVillager> getEvoVillagersWithEvolution(Evolution evolution) {
        final String name = evolution.getClass().getAnnotation(Name.class).value();
        final Collection<EvoVillager> evoVillagers = EvoVillagerHandler.getInstance().getAllEvoVillagers();
        final List<EvoVillager> evoVillagersWithEvolution = new ArrayList<>();
        if (evolution.getClass().getAnnotation(Type.class).value().equals(EvolutionType.ACTIVE)) {
            for (EvoVillager evoVillager : evoVillagers) {
                for (Evolution activeEvolution : evoVillager.getActiveEvolutions()) {
                    if (activeEvolution.getClass().getAnnotation(Name.class).value().equals(name)) {
                        evoVillagersWithEvolution.add(evoVillager);
                    }
                }
            }
            return evoVillagersWithEvolution;
        }
        if (evolution.getClass().getAnnotation(Type.class).value().equals(EvolutionType.PASSIVE)) {
            for (EvoVillager evoVillager : evoVillagers) {
                for (Evolution passiveEvolution : evoVillager.getPassiveEvolutions()) {
                    if (passiveEvolution.getClass().getAnnotation(Name.class).value().equals(name)) {
                        evoVillagersWithEvolution.add(evoVillager);
                    }
                }
            }
        }
        return evoVillagersWithEvolution;
    }


}
