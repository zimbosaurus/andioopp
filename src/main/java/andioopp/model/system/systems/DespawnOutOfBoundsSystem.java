package andioopp.model.system.systems;

import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;
import andioopp.model.util.ModelCoordinate;

/**
 * A class that handles when Objects move out of bounds.
 */
public class DespawnOutOfBoundsSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        World world = model.getWorld();
        despawnOutOfBoundsProjectiles(world);
        despawnOutOfBoundsEnemies(world);
    }

    /**
     * Checks if a projectile is out of bounds and removes it if true.
     */
    private void despawnOutOfBoundsProjectiles(World world) {
        world.getProjectiles().removeIf(projectile -> projectile.getPosition().getX() > world.getNumberOfCellsInLanes());
    }

    /**
     * Checks if a enemy is out of bounds and removes it if true.
     */
    private void despawnOutOfBoundsEnemies(World world) {

        world.getEnemies().removeIf(enemy -> enemy.getPosition().getX() < 0);
    }
}
