package com.rq.week1;
import java.util.Scanner;

/**
 * Created by Faydee on 2018/2/9.
 */

public class Prompter {
    public enum WeaponType {
        DEFAULT("Default", 1),
        FIRE("Fire", 2),
        ICE("Ice", 3);

        private String title;
        private int number;

        WeaponType(String title, int number) {
            this.title = title;
            this.number = number;
        }

        public String title() {
            return title;
        }

        public int number() {
            return number;
        }
    }

    public enum Job {
        HUNTER("Hunter", 1),
        WARRIOR("Warrior", 2),
        MAGE("Mage", 3);

        private String title;
        private int number;

        Job(String title, int number) {
            this.title = title;
            this.number = number;
        }

        public String title() {
            return title;
        }

        public int number() {
            return number;
        }
    }

    private final static Scanner scanner = new Scanner(System.in);
    private WeaponType chosenWeaponType = WeaponType.DEFAULT;
    private Job chosenJob = Job.HUNTER;
    private String name;

    public String askName() {
        System.out.print("What is your Name?  ");
        // 取得輸入內容
        name = scanner.nextLine();
        return name;
    }

    // 選擇職業
    public void chooseJob() {
        boolean isAcceptable = false;
        do {
            try {
                showJobs();
                String jobString = scanner.nextLine();
//                System.out.printf("Your job is: %s%n", jobString);
                int jobNumber = Integer.parseInt(jobString); // 不能轉整數跳到 catch
//                System.out.printf("Your job number is: %d%n", jobNumber);
                // 檢查只能輸入 1,2,3
                isAcceptable = checkNumberIs1to3(jobNumber);

                if (isAcceptable) {
                    for (Job job : Job.values()) {
                        if (jobNumber == job.number()) {
                            chosenJob = job;
                            System.out.printf("Your job is: %s%n", job.title());
                        }
                    }

                    // 選擇角色類型
                    chooseWeaponType();
                }
            } catch (NumberFormatException nfe) {
                System.out.printf("%s.  Please try again and enter number.%n",
                        nfe.getMessage());
            }
        } while (!isAcceptable);
    }

    // 選擇武器類型
    private void chooseWeaponType() {
        boolean isAcceptable = false;
        do {
            try {
                showWeaponTypes();
                String weaponTypeString = scanner.nextLine();
                int weaponTypeNumber = Integer.parseInt(weaponTypeString);
                isAcceptable = checkNumberIs1to3(weaponTypeNumber);

                if (isAcceptable) {
                    for (WeaponType weaponType : WeaponType.values()) {
                        if (weaponTypeNumber == weaponType.number()) {
                            chosenWeaponType = weaponType;
                            System.out.printf("Your weapon type is: %s%n", weaponType.title());
                        }
                    }
                    attackWithJobAndWeaponType();
                }
            } catch (NumberFormatException nfe) {
                System.out.printf("%s.  Please try again and enter number.%n",
                        nfe.getMessage());
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

    private boolean checkNumberIs1to3(int number) {
        // 檢查只能輸入 1,2,3
        if (number == 1 || number == 2 || number == 3) {
            return true;
        } else {
            System.out.printf("Please choose from 1 ~ 3%n");
            return false;
        }
    }

}
