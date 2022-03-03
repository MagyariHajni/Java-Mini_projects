package emailapp;

import java.util.Scanner;

public class EmailApp {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter first name: ");
        String fName = s.next().toUpperCase();
        System.out.println("Enter first name: ");
        String lName = s.next().toUpperCase();

        Email em1 = new Email(fName, lName);

        int choice = -1;
        do{
            System.out.println("""
                    *****************
                    Enter your choice
                    1. Show info
                    2. Change pass
                    3. Change mailbox capacity
                    4. Set alternate email
                    5. Store data in file
                    6. Read data from file
                    7. Exit""");
            choice = s.nextInt();
            switch (choice) {
                case 1 -> em1.getInfo();
                case 2 -> em1.setPassword();
                case 3 -> em1.setMailCapacity();
                case 4 -> em1.alternateEmail();
                case 5 -> em1.storeInfo();
                case 6 -> em1.readFile();
                case 7 -> System.out.println("Exiting system!");
                default -> System.out.println("Invalid choice, chose again\n");
            }
        } while (choice != 7);
    }
}
