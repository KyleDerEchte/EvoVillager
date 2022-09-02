package de.kyleonaut.evovillager.evolution.active;

import de.kyleonaut.evovillager.EvoVillagerPlugin;
import de.kyleonaut.evovillager.event.EvoVillagerDamageByEntityEvent;
import de.kyleonaut.evovillager.villager.EvoVillager;
import org.bukkit.*;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RunOnDamageEvolution extends Evolution {
    public RunOnDamageEvolution() {
        setName("RunOnDamageEvolution");
        bind(EvoVillagerDamageByEntityEvent.class);
    }

    @Override
    public void invoke(EvoVillager evoVillager) {
        final Villager villager = evoVillager.getVillager();
        villager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 4, 2));
        villager.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 4, 1));
        final EvoVillagerPlugin plugin = EvoVillagerPlugin.getPlugin(EvoVillagerPlugin.class);
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            createRunEffect(villager.getLocation());
        }, 0, 1);
        Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.getScheduler().cancelTask(taskId), 20 * 4);
    }

    private void createRunEffect(Location location) {
        final Location loc = location.clone();
        loc.setY(loc.getY() + 1.5);
        final World world = loc.getWorld();
        if (world != null) {
            world.spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, 2, new Particle.DustTransition(Color.PURPLE,Color.BLUE,1));
        }
    }
}
