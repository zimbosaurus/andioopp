package andioopp.model.domain.tower.attack.attacks;

import andioopp.common.javafx.time.FxClock;
import andioopp.common.math.Vector3f;
import andioopp.common.math.range.FloatRange;
import andioopp.model.Model;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.damage.BaseDamageSource;
import andioopp.model.domain.damage.DamageType;
import andioopp.model.domain.tower.attack.strategies.NonTargeting;

/**
 * An attack (sort of) which generates money
 */
public class DigCoinAttack extends Attack {

    private final FloatRange moneyRange;

    public DigCoinAttack(float cooldown, FloatRange moneyRange) {
        super(cooldown, new NonTargeting(), new BaseDamageSource(DamageType.ANY));
        this.moneyRange = moneyRange;
        timeOfLastAttack = FxClock.nanosToSeconds(FxClock.getNowTimeNanos());
    }

    @Override
    public void performAttack(Model model, Vector3f position) {
        model.getPlayer().giveMoney(getRandomMoney());
    }

    private Money getRandomMoney() {
        return new Money(Math.round(moneyRange.getRandom()));
    }
}