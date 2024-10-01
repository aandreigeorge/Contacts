import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class ContactManager {

    private static final Scanner scanner = new Scanner(System.in);
    private final List<Contact> contactList;
    private final ContactStorage contactStorage;


    ContactManager(String fileName) {
        contactStorage = new ContactStorage(fileName);
        contactList = contactStorage.loadContacts();
        runContacts();
    }

    @SuppressWarnings("InfiniteLoopStatement") /*exit() method handles the InfiniteLoopStatement */
    private void runContacts() {

        String action;

        while (true) {

            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            String recordOrRecords = contactList.size() == 1 ? " record" : " records";
            action = scanner.nextLine().trim().toUpperCase();

            switch (action) {
                case "ADD" -> addContactMenu();
                case "LIST" -> listMenu();
                case "SEARCH" -> searchMenu();
                case "COUNT" -> System.out.println("The Phone Book has " + contactList.size() + recordOrRecords + ".");
                case "EXIT" -> exit();
                default -> System.out.println("Invalid Menu Option!");
            }
        }
    }

    private void addContactMenu() {

        System.out.print("Enter the type: ");
        String type = scanner.nextLine().toUpperCase();
        addContact(type);
    }

    private void listMenu() {

        listContactsByName(contactList);
        if (contactList.isEmpty()) {
            return;
        }

        System.out.print("\n[list] Enter action ([number], back): ");
        String action = scanner.nextLine();

        if (Validation.isValidInteger(action)) {
            Contact selectedContact = findContactByIndex(action);

            if (selectedContact != null) {
                recordMenu(selectedContact);
            } else {
                System.out.println("Invalid record number!");
            }

        } else if (!action.equalsIgnoreCase("BACK")) {
            System.out.println("Wrong command! Going back to menu.");
        }
    }

    private void searchMenu() {

        String contactsToFind, action;
        System.out.print("Enter search query: ");
        contactsToFind = scanner.nextLine().toUpperCase();
        List<Contact> foundContacts = getSearchResults(contactsToFind);
        String recordOrRecords = contactList.size() == 1 ? " result" : " results";
        System.out.println("Found " + foundContacts.size() + recordOrRecords + ":");
        listContactsByName(foundContacts);

        while (true) {
            System.out.print("\n[search] Enter action ([number], back, again): ");
            action = scanner.nextLine().toUpperCase();

            if (Validation.isValidInteger(action)) {
                Contact selectedContact = findContactByIndex(action);

                if (selectedContact != null) {
                    recordMenu(selectedContact);
                } else {
                    System.out.println("Invalid record number!");
                }
                return;

            } else {
                switch (action) {
                    case "AGAIN":
                        System.out.print("Enter search query: ");
                        contactsToFind = scanner.nextLine().toUpperCase();
                        getSearchResults(contactsToFind);
                        break;
                    case "BACK":
                        return;
                }
            }
        }
    }

    private void recordMenu(Contact selectedContact) {

        listFullContactDetails(selectedContact);

        while (true) {
            System.out.print("\n[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine().toUpperCase();

            switch (action) {
                case "EDIT":
                    editMenu(selectedContact);
                    break;
                case "DELETE":
                    deleteContact(selectedContact);
                    return;
                case "MENU":
                    return;
            }
        }
    }

    private void editMenu(Contact contactToEdit) {

        String fieldName, newFieldValue;
        String contactFields = contactToEdit.getModifiableFields();
        System.out.print("Select a field (" + contactFields + "): ");
        fieldName = scanner.nextLine().toLowerCase();

        if (contactFields.contains(fieldName)) {
            System.out.print("Enter " + fieldName + ": ");
            newFieldValue = scanner.nextLine();
            editContact(contactToEdit, fieldName, newFieldValue);
        } else {
            System.out.println("Invalid field!");
        }
    }

    private Contact findContactByIndex(String userInput) {

        int selectedContactIndex = Integer.parseInt(userInput) - 1;
        if (selectedContactIndex >= 0 && selectedContactIndex < contactList.size()) {
            return contactList.get(selectedContactIndex);
        }
        return null;
    }

    private List<Contact> getSearchResults(String contactsToFind) {

        List<Contact> foundContacts = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.exists(contactsToFind)) {
                foundContacts.add(contact);
            }
        }
        return foundContacts;
    }

    private void addContact(String type) {

        Contact newContact;

        if (type.equals("PERSON")) {
            newContact = new Person();

            System.out.print("Enter the name: ");
            String name = scanner.nextLine();
            newContact.setField("NAME", name);

            System.out.print("Enter the surname: ");
            String surname = scanner.nextLine();
            newContact.setField("SURNAME", surname);

            System.out.print("Enter the birth date: ");
            String birthDate = scanner.nextLine();
            newContact.setField("BIRTH", birthDate);

            System.out.print("Enter the gender (M, F): ");
            String gender = scanner.nextLine();
            newContact.setField("GENDER", gender);

            System.out.print("Enter the number: ");
            String phoneNumber = scanner.nextLine();
            newContact.setField("NUMBER", phoneNumber);

            contactList.add(newContact);

        } else if (type.equals("ORGANIZATION")) {
            newContact = new Organization();

            System.out.print("Enter the organization name: ");
            String organizationName = scanner.nextLine();
            newContact.setField("NAME", organizationName);

            System.out.print("Enter the address: ");
            String address = scanner.nextLine();
            newContact.setField("ADDRESS", address);

            System.out.print("Enter the number: ");
            String phoneNumber = scanner.nextLine();
            newContact.setField("NUMBER", phoneNumber);

            contactList.add(newContact);

        } else {
            System.out.println("Invalid contact type!");
        }
    }

    private void editContact(Contact contactToEdit, String fieldName, String fieldValue) {
        contactToEdit.setField(fieldName, fieldValue);
        contactToEdit.updateLastEditTime();
        System.out.println("Contact Updated.");
        listFullContactDetails(contactToEdit);
    }

    private void deleteContact(Contact contactToRemove) {
        contactList.remove(contactToRemove);
        System.out.println("Contact Deleted!");
    }

    private void listContactsByName(List<Contact> contactList) {

        if (contactList.isEmpty()) {
            System.out.println("No records found!");
        } else {
            for (int i = 0; i < contactList.size(); i++) {
                Contact contact = contactList.get(i);
                System.out.println((i + 1) + ". " + contact.getFullName());
            }
        }
    }

    private void listFullContactDetails(Contact contact) {
        System.out.println(contact);
    }

    private void exit() {
        contactStorage.saveContacts(contactList);
        System.exit(0);
    }


}
