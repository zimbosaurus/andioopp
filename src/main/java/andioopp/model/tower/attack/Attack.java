package andioopp.model.tower.attack;

import andioopp.common.time.Time;
import andioopp.common.storage.ArrayListFactory;
import andioopp.common.transform.Vector3f;
import andioopp.model.damage.DamageSourceType;
import andioopp.model.damage.DamageTargetType;
import andioopp.model.world.World;
import andioopp.model.enemy.Enemy;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An attack.
 * Belongs to a tower.
 */
public abstract class Attack {
    private final float coolDown;
    private float timeSinceLastAttack;
    private final AttackTargetArea targetArea;

    //Enums
    public ArrayList<DamageTargetType> damageTargetTypes = new ArrayList<>();
    public ArrayList<DamageSourceType> immunty = new ArrayList<>();

    public Attack(float coolDown, AttackTargetArea targetArea) {
        this.coolDown = coolDown;
        this.targetArea = targetArea;

    }

    /**
     * Executes the attack. Can vary depending on the attack.
     * Usually spawns a projectile in the game world.
     * @param world
     * @param position position of the tower, or wherever the attack is to be performed
     */
    public abstract void performAttack(World world, Vector3f position);

    /**
     * Checks if the tower has waited long enough since its last attak.
     * @param time the current time
     * @return true if enough time has passed since the last attack.
     */
    public boolean isAvailableForAttack(Time time){
        float deltaSeconds = time.getElapsedSeconds() - timeSinceLastAttack;
        return(deltaSeconds > this.coolDown);
    }

    /**
     * Sets the time of the last attack.
     * @param time the current time
     */
    public void updateTimeSinceLastAttack(Time time) {
        this.timeSinceLastAttack = time.getElapsedSeconds();
    }

    /**
     * Uses the attacks targetArea to make a list of which towers can attack.
     * @param world
     * @param position position of the attacking tower
     * @return a collection of enemies in range
     */
    public Collection<Enemy> getEnemiesInRange(World world, Vector3f position) {
        Collection<Enemy> enemiesInRange = new ArrayListFactory().create();
        for ( Enemy enemy : world.getEnemies() ) {

            if (targetArea.enemyIsInRange(position, enemy.getPosition(), world)) {
                enemiesInRange.add(enemy);
            }
        }
        return enemiesInRange;
    }

    /**
     * Checks if the attack can hit the current enemy.
     * @param enemy the enemy in question.
     * @return true if it can attack the current enemy.
     */
    public boolean hasMatchingRequirements(Enemy enemy) {
        for(int i = 0; i < damageTargetTypes.size(); i++) {
            DamageTargetType r = damageTargetTypes.get(i);
            for(int j = 0; j < enemy.damageTargetTypes.size(); j++){
                DamageTargetType e = enemy.damageTargetTypes.get(j);
                if (r.equals(e)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the enemy is immune to the current attack.
     * @param enemy the enemy in question.
     * @return true if it is immune.
     */
    public boolean isImmune(Enemy enemy) {
        if(enemy.immunities.isEmpty()) { //if enemy damageSourceType list is empty => Its not immune.
            return false;
        } else {
            for (int i = 0; i < immunty.size(); i++) {
                DamageSourceType imm = immunty.get(i);

                for (int j = 0; j < enemy.immunities.size(); j++) {
                    DamageSourceType EnemyImm = enemy.immunities.get(j);

                    if (imm.equals(EnemyImm)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean checkFilters(Enemy enemy) {
        boolean b1 = !isImmune(enemy);
        boolean b2 = hasMatchingRequirements(enemy);
        return b1 && b2;
    }
}
