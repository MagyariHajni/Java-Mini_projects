package emailapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Email {
    public Scanner scan = new Scanner(System.in);
    private String firstName;
    private String lastname;
    private String dept;
    private String email;
    private String pass;
    private int mailCapacity = 500;
    private String alterMail;

    public Email(String firstName, String lastname) {
        this.firstName = firstName;
        this.lastname = lastname;
        System.out.println("New employee: " + this.firstName + " " + this.lastname);
        this.dept = this.setDept();
        this.pass = this.generatePass(8);
        this.email = this.generateEmail();

    }

    private String generateEmail() {
        return this.firstName.toLowerCase() + "." + this.lastname.toLowerCase()
                + "@" + this.dept.toLowerCase() + (Objects.equals(this.dept.toLowerCase(), "") ? "" : ".") + "company.com";
    }

    public String setDept() {
        System.out.println("Department codes: \n1 for Sales\n2 for Development\n3 for Accounting\n0 for none");
        boolean flag = false;
        do {
            System.out.println("Enter department code:");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    return "Sales";
                case 2:
                    return "Development";
                case 3:
                    return "Accounting";
                case 0:
                    return "";
                default:
                    System.out.println("Invalid choice, please chose again!");
            }
        } while (true);
    }

    private String generatePass(int length) {
        Random r = new Random();
        String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%&?";
        String values = capitalChars + lowerChars + numbers + symbols;
        String pass = "";
        for (int i = 0; i < length; i++) {
            pass = pass + values.charAt(r.nextInt(values.length()));
        }
        return pass;
    }

    public void setPassword() {
        boolean flag = false;
        do {
            System.out.println("Do you want to change pass? Y/N");
            char choice = scan.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                flag = true;
                System.out.println("Enter current pass:");
                String temp = scan.next();
                if (temp.equals(this.pass)) {
                    System.out.println("Enter new pass:");
                    this.pass = scan.next();
                    System.out.println("Password changed successfully");
                } else {
                    System.out.println("Incorrect pass");
                }
            } else if (choice == 'N' || choice == 'n') {
                flag = true;
                System.out.println("Password change option cancelled");
            } else {
                System.out.println("Please enter valid choice Y/N");
            }
        } while (!flag);
    }

    public void setMailCapacity() {
        System.out.println("Current capacity: " + this.mailCapacity + " mb");
        System.out.println("Enter new mailbox capacity: ");
        this.mailCapacity = scan.nextInt();
        System.out.println("Mailbox capacity successfully set to " + this.mailCapacity + " mb");
    }

    public void alternateEmail() {
        System.out.println("Enter alternate email:");
        this.alterMail = scan.next();
        System.out.println("Alternate email is set!");
    }

    public void getInfo() {
        System.out.println("New employee name: " + this.firstName + " " + this.lastname);
        System.out.println("Department: " + this.dept);
        System.out.println("Email: " + this.email);
        System.out.println("Password: " + this.pass);
        System.out.println("Mailbox capacity: " + this.mailCapacity + " mb");
        System.out.println("Alternate email: " + this.alterMail);
    }

    public void storeInfo() {
        Path fileIn = new File("src/emailapp/savedData.txt").toPath();
        System.out.println(fileIn.toAbsolutePath());
        try (BufferedWriter writeToFile = Files.newBufferedWriter(fileIn)) {
            writeToFile.write("First name: " + this.firstName);
            writeToFile.append("\nLast name: ").append(this.lastname);
            writeToFile.append("\nEmail: ").append(this.email);
            writeToFile.append("\nPassword: ").append(this.pass);
            writeToFile.append("\nMail capacity: ").append(String.valueOf(this.mailCapacity));
            writeToFile.append("\nAlternate email: ").append(this.alterMail);
            System.out.println("Data stored in: " + fileIn.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error handling: " + e);
        }
    }

    public void readFile() {
        Path fileIn = new File("src/emailapp/savedData.txt").toPath();
        System.out.println(fileIn.toAbsolutePath());
        String line;
        try (BufferedReader read = Files.newBufferedReader(fileIn)) {
            while ((line = read.readLine()) != null)
                System.out.println(line);
        } catch (Exception e) {
            System.out.println("Error handling: " + e);
        }
    }
}
