package com.rq.week1;

/**
 * Created by Faydee on 2018/2/9.
 */

public class Hunter extends Human {
    public final static int DEFAULT_BOW = 1;
    public final static int FIRE_BOW = 2;
    public final static int ICE_BOW = 3;

    private Prompter.WeaponType roleType = Prompter.WeaponType.DEFAULT;

    public Hunter() {}

    public Hunter(String name) {
        setName(name);
    }

    public Hunter(Prompter.WeaponType roleType) {
        this.roleType = roleType;
    }

    @Override public void attack() {
        System.out.printf("Hunter %s Attack!%n", getName());
        switch (roleType) {
            case DEFAULT:
                System.out.printf("Arrow damage: %d%n", DEFAULT_BOW);
                break;
            case FIRE:
                System.out.printf("Fire Arrow damage: %d%n", FIRE_BOW);
                break;
            case ICE:
                System.out.printf("Ice Arrow damage: %d%n", ICE_BOW);
                break;
        }
    }
}
