import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Contacts.createContact();
    }
}
package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        runContacts();
    }

    static void runContacts() {

        List<Contact> contactsList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String action;

        while (true) {
            System.out.println("Enter action (add, remove, edit, count, list, exit): ");
            action = scanner.nextLine().trim().toUpperCase();

            switch (action) {
                case "ADD" -> Contacts.createContact(contactsList);
                case "REMOVE" -> Contacts.removeContact(contactsList);
                case "EDIT" -> Contacts.editContact(contactsList);
                case "COUNT" -> System.out.println("The Phone Book has " + contactsList.size() + " records");
                case "LIST" -> Contacts.listContacts(contactsList);
                case "EXIT" -> System.exit(0);
            }
        }
    }
}
