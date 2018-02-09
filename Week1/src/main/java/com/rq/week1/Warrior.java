package com.rq.week1;

/**
 * Created by Faydee on 2018/2/9.
 */

public class Warrior extends Human {
    public final static int DEFAULT_BLADE = 1;
    public final static int FIRE_BLADE = 2;
    public final static int ICE_BLADE = 3;

    private Prompter.WeaponType weaponType = Prompter.WeaponType.DEFAULT;

    public Warrior() {}

    public Warrior(String name) {
        setName(name);
    }

    public Warrior(Prompter.WeaponType roleType) {
        this.weaponType = roleType;
    }

    @Override public void attack() {
        System.out.printf("Warrior %s Attack!%n", getName());
        switch (weaponType) {
            case DEFAULT:
                System.out.printf("Slash damage: %d%n", DEFAULT_BLADE);
                break;
            case FIRE:
                System.out.printf("Fire Slash damage: %d%n", FIRE_BLADE);
                break;
            case ICE:
                System.out.printf("Ice Slash damage: %d%n", ICE_BLADE);
                break;
        }
    }

}

