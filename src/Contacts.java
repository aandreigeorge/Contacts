import java.util.List;
import java.util.Scanner;

public class Contacts {

    private static final Scanner scanner = new Scanner(System.in);

    static void createContact(List<Contact> contactsList) {

        System.out.println("Enter the name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter the number: ");
        String number = scanner.nextLine();

        Contact contact = new Contact.ContactBuilder().setName(name).setSurname(surname).setNumber(number).build();
        contactsList.add(contact);
        System.out.println("The record added.");
    }

    static void removeContact(List<Contact> contactsList) {

        if (contactsList.isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }

        listContacts(contactsList);
        System.out.println("Select a record: ");
        int slectedContact = Integer.parseInt(scanner.nextLine()) - 1;

        if (slectedContact >= 0 && slectedContact < contactsList.size()) {
            contactsList.remove(slectedContact);
            System.out.println("The record removed!");
        } else {
            System.out.println("Invalid record number");
        }
    }

    static void editContact(List<Contact> contactList) {

        if (contactList.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        listContacts(contactList);
        System.out.println("Select a record: ");
        int selectedContact = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedContact >= 0 && selectedContact < contactList.size()) {

            Contact contact = contactList.get(selectedContact);
            Contact.ContactBuilder contactToUpdate = new Contact.ContactBuilder().
                    setName(contact.getName()).setSurname(contact.getSurname()).setNumber(contact.getNumber());

            System.out.println("Select a field (name, surname, number): ");
            String field = scanner.nextLine().trim().toUpperCase();

            switch (field) {
                case "NAME":
                    System.out.println("Enter name: ");
                    String newName = scanner.nextLine();
                    contactToUpdate.setName(newName);
                    break;
                case "SURNAME":
                    System.out.println("Enter surname: ");
                    String newSurname = scanner.nextLine();
                    contactToUpdate.setSurname(newSurname);
                    break;
                case "NUMBER":
                    System.out.println("Enter number: ");
                    String newNumber = scanner.nextLine();
                    contactToUpdate.setNumber(newNumber);
                    break;
            }
            Contact updatedContact = contactToUpdate.build();
            contactList.set(selectedContact, updatedContact);
            System.out.println("The record updated!");
        } else {
            System.out.println("Invalid record number!");
        }
    }

    static void listContacts(List<Contact> contactList) {

        for(int i=0; i< contactList.size(); i++) {
            System.out.println((i+1) + ". " + contactList.get(i));
        }
    }

}
