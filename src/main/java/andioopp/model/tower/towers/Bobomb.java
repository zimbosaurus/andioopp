package andioopp.model.tower.towers;

import andioopp.model.tower.Tower;
import andioopp.model.tower.attack.Attack;
import andioopp.model.tower.attack.attacks.FireballAttack;

import java.util.ArrayList;
import java.util.Arrays;

public class Bobomb extends Tower {

    private static final String SPRITE_PATH = "bobomb-removebg-preview.png";
    private static final Attack[] attackList = {new FireballAttack(0.1f)};



    public Bobomb() {
        super(SPRITE_PATH, "Bobomb", 200, 5, new ArrayList<>(Arrays.asList(new FireballAttack(0.1f))));

    }
}
