package com.rq.week1;
import com.rq.week1.Prompter.ChooseType;

import jdk.nashorn.internal.scripts.JO;

public class Human {
    private String name;
//    public final static int FIRE_BOW = 0x01; // 1
//    public final static int ICE_BOW = 0x02; // 2

    public static void main(String[] args) {
        Prompter prompter = new Prompter();
        String name = prompter.askName();

        Human human = new Human(name);
        prompter.choose(ChooseType.JOB); // 選擇職業
//        prompter.chooseJob();
    }

    public Human() {
//        System.out.printf("fire bow: %d %n", FIRE_BOW);
//        System.out.printf("ice bow: %d %n", ICE_BOW);
    }

    public Human(String name) {
        this.name = name;
        System.out.printf("Your name is: %s%n", this.name);
    }

    public void attack() {
        System.out.println("Fist Attack!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
