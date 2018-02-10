package com.rq.week1;

/**
 * Created by Faydee on 2018/2/9.
 */

public class Mage extends Human {
    public final static int DEFAULT_STAFF = 1;
    public final static int FIRE_STAFF = 2;
    public final static int ICE_STAFF = 3;

    private Prompter.WeaponType weaponType = Prompter.WeaponType.DEFAULT;

    public Mage() {}

    public Mage(String name) {
        setName(name);
    }

    public Mage(Prompter.WeaponType roleType) {
        this.weaponType = roleType;
    }

    @Override public void attack() {
        System.out.printf("Mage %s Attack!%n", getName());
        switch (weaponType) {
            case DEFAULT:
                System.out.printf("Default Staff damage: %d%n", DEFAULT_STAFF);
                break;
            case FIRE:
                System.out.printf("Fire Staff damage: %d%n", FIRE_STAFF);
                break;
            case ICE:
                System.out.printf("Ice Staff damage: %d%n", ICE_STAFF);
                break;
        }
    }

}
