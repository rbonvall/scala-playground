package interop;

import interop.Date;

public class App {
    public static void main(String[] args) {
        dates();
    }
    public static void dates() {
        Date today = new Date(1998, 9, 18);
        Date tomorrow = today.next();
        System.out.println("Tomorrow is " + tomorrow);
        Date xmas = new Date(1998, 12, 25);
        System.out.println("There are " + (xmas.$minus(today)) + " days left to Christmas.");
    }
}
