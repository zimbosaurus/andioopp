package andioopp.model.domain.tower.towers;

import andioopp.common.storage.ArrayListFactory;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.stats.Health;
import andioopp.model.domain.tower.attack.Attack;
import andioopp.model.domain.tower.Tower;
import andioopp.model.util.ModelCoordinate;

import java.util.List;
/**
 * Tower Rosalina with specific properties.
 */
public class Rosalina extends Tower {
    private static final String SPRITE_PATH = "rosalina-removebg-preview.png";

    public Rosalina(ModelCoordinate position) {
        super(position, SPRITE_PATH, "Rosalina", new Money(500), new Health(10));
    }

    @Override
    protected List<Attack> createAttacks() {
        return new ArrayListFactory().create();
    }
}
