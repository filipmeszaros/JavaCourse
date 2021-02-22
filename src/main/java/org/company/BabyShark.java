package org.company;

import java.util.concurrent.TimeUnit;

public class BabyShark {
    private static String[] lyrics = {"Baby shark", "Mommy shark", "Daddy shark",
            "Grandma shark", "Grandpa shark", "Let's go hunt",
            "Run away", "Safe at last", "It's the end" };

    private static final int MAX_REPETITION = 3;

    public static void main(String[] args) {
        for (String lyric : lyrics) {
            int repetition = 0;
            pause(lyric.length() * 100);

            while (repetition < MAX_REPETITION) {
                System.out.print(lyric);
                pause(lyric.length() * 50);
                doo();
                repetition++;
            }
            System.out.println(lyric.toUpperCase() + "!!!\n");
            pause(lyric.length() * 100);
        }
    }

    private static void doo() {
        int doos = 0;
        while (doos < 6) {
            System.out.print(", doo");
            if (doos % 2 == 0)
                pause(200);
            else
                pause(350);
            doos++;
        }
        System.out.println();
    }

    private static void pause(int waitTime) {
        try {
            TimeUnit.MILLISECONDS.sleep(waitTime);
        } catch (InterruptedException e) {
            System.out.println("Baby shark song failed");
        }
    }
}
