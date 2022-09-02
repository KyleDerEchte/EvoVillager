package de.kyleonaut.evovillager.handler;

import de.kyleonaut.evovillager.event.EvolutionEvent;
import de.kyleonaut.evovillager.evolution.active.Evolution;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class EvolutionHandler {
    private static EvolutionHandler instance;
    private final Map<Class<? extends EvolutionEvent>, List<Evolution>> classEvolutionEventMap = new HashMap<>();


    public static EvolutionHandler getInstance() {
        if (instance == null) {
            instance = new EvolutionHandler();
        }
        return instance;
    }

    private EvolutionHandler() {

    }

    private void register(Class<? extends EvolutionEvent> event) {
        this.classEvolutionEventMap.put(event, new ArrayList<>());
    }

    public void bind(Evolution evolution, Class<? extends EvolutionEvent> event) {
        if (!this.classEvolutionEventMap.containsKey(event)) {
            register(event);
            bind(evolution, event);
        }
        final List<Evolution> evolutions = this.classEvolutionEventMap.get(event);
        final List<String> evolutionNames = evolutions.stream()
                .map(Evolution::getName).toList();
        if (!evolutionNames.contains(evolution.getName())) {
            evolutions.add(evolution);
        }
    }

    public void invoke(EvolutionEvent event) {
        if (this.classEvolutionEventMap.containsKey(event.getClass())) {
            this.classEvolutionEventMap.get(event.getClass()).forEach(evolution -> evolution.invoke(event.getEvoVillager()));
            return;
        }
        Bukkit.getLogger().log(Level.WARNING, "[EvoVillager] An event was called, with no evolutions to invoke");
    }
}
