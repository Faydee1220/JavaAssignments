package com.rq.week1;
import java.util.Scanner;

/**
 * Created by Faydee on 2018/2/9.
 */

public class Prompter {
    public enum WeaponType {
        DEFAULT(1),
        FIRE(2),
        ICE(3);

        private String title;
        private int number;

        WeaponType(int number) {
            this.number = number;
        }

        public String title() {
            String lowerCased = this.name().toLowerCase();
            title = lowerCased.substring(0, 1).toUpperCase() +  lowerCased.substring(1);
            return title;
        }

        public int number() {
            return number;
        }
    }

    public enum Job {
        HUNTER(1),
        WARRIOR(2),
        MAGE(3);

        private String title;
        private int number;

        Job(int number) {
            this.number = number;
        }

        public String title() {
            String lowerCased = this.name().toLowerCase();
            title = lowerCased.substring(0, 1).toUpperCase() +  lowerCased.substring(1);
            return title;
        }

        public int number() {
            return number;
        }
    }

    public enum ChooseType {
        JOB, WEAPONTYPE;
    }

    private final static Scanner scanner = new Scanner(System.in);
    private WeaponType chosenWeaponType = WeaponType.DEFAULT;
    private Job chosenJob = Job.HUNTER;
    private String name;

    public String askName() {
        boolean isAcceptable = false;
        do {
            System.out.print("What is your Name?  ");
            // 取得輸入內容
            name = scanner.nextLine();
            try {
                // 檢查排除沒有輸入
                checkInputIsNotEmpty(name);
                isAcceptable = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%s.  Please try again and enter your name.%n",
                        iae.getMessage());
            }
        } while (!isAcceptable);

        return name;
    }

    public void choose(ChooseType chooseType) {
        boolean isAcceptable = false;
        do {
            try {
                switch (chooseType) {
                    case JOB:
                        showJobs();
                        break;
                    case WEAPONTYPE:
                        showWeaponTypes();
                        break;
                }
                String input = scanner.nextLine();
                checkInputIsNotEmpty(input);  // 檢查排除沒有輸入
                int number = Integer.parseInt(input); // 不能轉整數跳到 catch
                isAcceptable = checkNumberIs1to3(number); // 檢查只能輸入 1,2,3
                if (isAcceptable) {
                    showChosen(chooseType, number);
                    switch (chooseType) {
                        case JOB:
                            choose(ChooseType.WEAPONTYPE); // 選擇武器類型
                            break;
                        case WEAPONTYPE:
                            attackWithJobAndWeaponType();
                            break;
                    }
                }
            } catch (IllegalArgumentException iae) {
                System.out.printf("%s.  Please try again and enter number.%n",
                        iae.getMessage());
            }
        } while (!isAcceptable);
    }

    private void attackWithJobAndWeaponType() {
        switch (chosenJob) {
            case HUNTER:
                Hunter hunter = new Hunter(chosenWeaponType);
                hunter.setName(name);
                hunter.attack();
                break;
            case WARRIOR:
                Warrior warrior = new Warrior(chosenWeaponType);
                warrior.setName(name);
                warrior.attack();
                break;
            case MAGE:
                Mage mage = new Mage(chosenWeaponType);
                mage.setName(name);
                mage.attack();
                break;
        }
    }

    private void showJobs() {
        System.out.printf("Please choose your job below and enter number:  %n");
        System.out.printf("%d.%s%n", Job.HUNTER.number(), Job.HUNTER.title(), Job.HUNTER.name());
        System.out.printf("%d.%s%n", Job.WARRIOR.number(), Job.WARRIOR.title());
        System.out.printf("%d.%s%n", Job.MAGE.number(), Job.MAGE.title());
    }

    private void showWeaponTypes() {
        System.out.printf("Please choose your weapon type below and enter number:  %n");
        System.out.printf("%d.%s%n", WeaponType.DEFAULT.number(), WeaponType.DEFAULT.title());
        System.out.printf("%d.%s%n", WeaponType.FIRE.number(), WeaponType.FIRE.title());
        System.out.printf("%d.%s%n", WeaponType.ICE.number(), WeaponType.ICE.title());
    }

    private void showChosen(ChooseType chooseType, int number) {
        switch (chooseType) {
            case JOB:
                for (Job job : Job.values()) {
                    if (number == job.number()) {
                        chosenJob = job;
                        System.out.printf("Your job is: %s%n", job.title());
                    }
                }
                break;
            case WEAPONTYPE:
                for (WeaponType weaponType : WeaponType.values()) {
                    if (number == weaponType.number()) {
                        chosenWeaponType = weaponType;
                        System.out.printf("Your weapon type is: %s%n", weaponType.title());
                    }
                }
                break;
        }

    }

    private boolean checkNumberIs1to3(int number) {
        // 檢查只能輸入 1,2,3
        if (number == 1 || number == 2 || number == 3) {
            return true;
        } else {
            System.out.printf("Please choose from 1 ~ 3%n");
            return false;
        }
    }

    private void checkInputIsNotEmpty(String string) {
        // 檢查排除沒有輸入
        if (string.length() == 0) {
            throw new IllegalArgumentException("No input found");
        }
    }

}
