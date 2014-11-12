package interop;

import interop.Date;
import interop.dateStuff;

public class InteropApp {
    public static void main(String[] args) {
        dates();
    }
    public static void dates() {
        Date today = new Date(1998, 9, 18);
        System.out.println("Welcome to the year " + today.year());

        if (dateStuff.isLeap(today.year())) {
            System.out.println("It's a leap year!");
        } else {
            System.out.println("Not like there's anything special with it.");
        }

        Date tomorrow = today.next();
        System.out.println("Tomorrow is " + tomorrow);

        Date xmas = new Date(1998, 12, 25);
        System.out.println("There are " + (xmas.$minus(today)) + " days left to Christmas.");
    }
}
