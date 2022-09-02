package de.kyleonaut.evovillager.evolution.passive;

import de.kyleonaut.evovillager.villager.EvoVillager;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ZombieRevengePassiveEvolution extends PassiveEvolution {
    public ZombieRevengePassiveEvolution() {
        setName("ZombieRevengePassiveEvolution");
    }

    @Override
    public void invoke(EvoVillager evoVillager) {
        final Villager villager = evoVillager.getVillager();
        final List<Zombie> zombies = new ArrayList<>();
        for (Entity entity : villager.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof Zombie zombie) {
                zombies.add(zombie);
            }
        }
        Zombie nearestZombie = null;
        for (Zombie zombie : zombies) {
            if (nearestZombie == null) {
                nearestZombie = zombie;
                continue;
            }
            if (zombie.getLocation().distance(villager.getLocation()) <
                    nearestZombie.getLocation().distance(villager.getLocation())) {
                nearestZombie = zombie;
            }
        }
        if (nearestZombie == null) {
            return;
        }
        drawLine(nearestZombie.getEyeLocation(), villager.getEyeLocation());
        nearestZombie.damage(10);


    }

    private void drawLine(Location firstLoc, Location secondLoc) {
        final World world = firstLoc.getWorld();
        double distance = firstLoc.distance(secondLoc);
        final Vector firstVector = firstLoc.toVector();
        final Vector secondVector = secondLoc.toVector();
        final Vector vector = secondVector.clone().subtract(firstVector).normalize().multiply(0.2);
        double length = 0;
        for (; length < distance; firstVector.add(vector)) {
            if (world != null) {
                world.spawnParticle(Particle.DUST_COLOR_TRANSITION, firstVector.getX(), firstVector.getY(), firstVector.getZ(), 1, new Particle.DustTransition(Color.RED, Color.RED, 1));
            }
            length += 0.2;
        }
    }
}
