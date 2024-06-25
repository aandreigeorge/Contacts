import java.util.Scanner;

public class Contacts {

    static void createContact() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the person:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname of the person:");
        String surname = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        Contact contact = new Contact(name, surname, number);

        System.out.println("A record created!");
        System.out.println("A Phone Book with a single record created!");
    }


}
