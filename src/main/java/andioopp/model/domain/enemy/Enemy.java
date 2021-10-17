package andioopp.model.domain.enemy;

import andioopp.common.graphics.Sprite;
import andioopp.common.graphics.SpriteFactory;
import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.MutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.time.Time;
import andioopp.model.domain.money.Money;
import andioopp.model.util.ModelCoordinate;
import andioopp.model.domain.damage.DamageFilter;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.stats.Health;

/**
 * An enemy.
 */
public abstract class Enemy implements DamageFilter {

    private final Health health;
    private final String spritePath;
    private final float attackCooldown;
    private final float speed;
    private final Money reward;

    private boolean towerAhead = false;
    private float timeOfLastAttack;
    private final MutableRectangle rectangle;

    private final DamageFilter damageFilter;

    protected Enemy(String spritePath, Health health, Rectangle rectangle, float speed, float attackCooldown, DamageFilter damageFilter, Money reward) {
        this.spritePath = spritePath;
        this.health = health;
        this.speed = speed; // Negative speed since enemies come from the left
        this.attackCooldown = attackCooldown;
        this.damageFilter = damageFilter;
        this.reward = reward;
        this.rectangle = new MutableRectangle(rectangle);
    }

    @Override
    public boolean canBeDamagedBy(DamageSource src) {
        return damageFilter.canBeDamagedBy(src);
    }

    /**
     * Sets the time of the enemy's latest attack.
     * It is used to calculate when its next attack can be performed.
     * @param time the current time of the attack
     */
    public void setTimeOfLastAttack(Time time) {
        timeOfLastAttack = time.getTime();
    }

    /**
     * Calculates if the tower is able to attack depending on when it attacked the last time.
     * @param time the current time.
     * @return true if the tower is able to attack.
     */
    public boolean canAttack(Time time) {
        float deltaTime = time.getTime() - timeOfLastAttack;
        return (deltaTime > attackCooldown);
    }

    public <S extends Sprite<?>> S getSprite(SpriteFactory<S> spriteFactory) {
        return spriteFactory.get(spritePath);
    }

    public Health getHealth() {
        return health;
    }

    public ModelCoordinate getPosition() {
        return new ModelCoordinate(rectangle.getPosition());
    }

    public Dimension getSize() { return rectangle.getSize(); }

    public void move(Time time) {
        rectangle.translate(new ModelCoordinate(-speed * time.getDeltaTime()));
    }

    public boolean isDead() {
        return getHealth().isZero();
    }

    public void setTowerAhead(boolean state) {
        towerAhead = state;
    }

    public boolean isTowerAhead() {return towerAhead;}

    public Money getReward() {
        return reward;
    }
}
