import java.util.List;
import java.util.Scanner;

public class Contacts {

    private static final Scanner scanner = new Scanner(System.in);

    static void createContact(List<Contact> contactsList) {

        Contact newContact;
        System.out.print("Enter the type: ");
        String type = scanner.nextLine().toUpperCase();

        if (type.equals("PERSON")) {

            System.out.print("Enter the name: ");
            String name = scanner.nextLine();

            System.out.print("Enter the surname: ");
            String surname = scanner.nextLine();

            System.out.print("Enter the birth date: ");
            String birthDate = scanner.nextLine();
            if (!Validation.isValidBirthDate(birthDate)) {
                System.out.println("Bad birth date!");
            }

            System.out.print("Enter the gender (M, F): ");
            String gender = scanner.nextLine();
            if (!Validation.isValidGender(gender)) {
                System.out.println("Bad gender!");
            }

            System.out.print("Enter the number: ");
            String phoneNumber = scanner.nextLine();
            if (!Validation.isValidPhoneNumber(phoneNumber)) {
                System.out.println("Wrong number format!");
            }

            newContact = new Person(name, surname, birthDate, gender, phoneNumber);
            contactsList.add(newContact);

        } else if (type.equals("ORGANIZATION")) {

            System.out.print("Enter the organization name: ");
            String organizationName = scanner.nextLine();

            System.out.print("Enter the address: ");
            String address = scanner.nextLine();

            System.out.print("Enter the number: ");
            String phoneNumber = scanner.nextLine();
            if (!Validation.isValidPhoneNumber(phoneNumber)) {
                System.out.println("Wrong number format!");
            }

            newContact = new Organization(organizationName, address, phoneNumber);
            contactsList.add(newContact);

        }

        System.out.println("The record added.\n");

    }

    static void removeContact(List<Contact> contactsList) {

        if (contactsList.isEmpty()) {
            System.out.println("No records to remove!\n");
            return;
        }

        getInfo(contactsList);
        System.out.println("Select a record: ");
        int selectedContact = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedContact >= 0 && selectedContact < contactsList.size()) {
            contactsList.remove(selectedContact);
            System.out.println("The record removed!\n");
        } else {
            System.out.println("Invalid record number!\n");
        }
    }

    static void editContact(List<Contact> contactList) {

        if (contactList.isEmpty()) {
            System.out.println("No records to edit!\n");
            return;
        }

        listContactEntries(contactList);
        System.out.println("Select a record: ");
        int selectedContact = Integer.parseInt(scanner.nextLine()) - 1;


        if (selectedContact >= 0 && selectedContact < contactList.size()) {

            String fieldToUpdate, newFieldInfo;
            Contact contactToUpdate = contactList.get(selectedContact);

            if (contactToUpdate.isPerson()) {

                System.out.println("Select a field (name, surname, birth, gender, number: ");
                fieldToUpdate = scanner.nextLine().toUpperCase();

                switch (fieldToUpdate) {

                    case "NAME":
                        System.out.println("Enter name: ");
                        newFieldInfo = scanner.nextLine();
                        ((Person) contactToUpdate).setOrUpdateName(newFieldInfo);
                        break;
                    case "SURNAME":
                        System.out.println("Enter surname: ");
                        newFieldInfo = scanner.nextLine();
                        ((Person) contactToUpdate).setOrUpdateSurname(newFieldInfo);
                        break;
                    case "BIRTH":
                        System.out.println("Enter birth: ");
                        newFieldInfo = scanner.nextLine();
                        ((Person) contactToUpdate).setOrUpdateBirthdate(newFieldInfo);
                        break;
                    case "GENDER":
                        System.out.println("Enter gender: ");
                        newFieldInfo = scanner.nextLine();
                        ((Person) contactToUpdate).setOrUpdateGender(newFieldInfo);
                        break;
                    case "NUMBER":
                        System.out.println("Enter number: ");
                        newFieldInfo = scanner.nextLine();
                        contactToUpdate.setOrUpdatePhoneNumber(newFieldInfo);
                        break;
                }

            } else {

                System.out.println("Select a field (address, number): ");
                fieldToUpdate = scanner.nextLine().toUpperCase();

                switch (fieldToUpdate) {
                    case "ADDRESS":
                        System.out.print("Enter address: ");
                        newFieldInfo = scanner.nextLine();
                        ((Organization) contactToUpdate).setOrUpdateAddress(newFieldInfo);
                        break;
                    case "NUMBER":
                        System.out.print("Enter number: ");
                        newFieldInfo = scanner.nextLine();
                        contactToUpdate.setOrUpdatePhoneNumber(newFieldInfo);
                        break;
                }

            }
            contactToUpdate.updateLastEditTime();
            System.out.println("The record updated!\n");

        } else {
            System.out.println("Invalid record number!\n");
        }
    }


    static void getInfo(List<Contact> contactList) {

        listContactEntries(contactList);

        System.out.println("Enter index to show info: ");
        int selectedContact = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println(contactList.get(selectedContact) + "\n");
    }

    private static void listContactEntries(List<Contact> contactList) {

        for (int i = 0; i < contactList.size(); i++) {

            String name;
            Contact contact = contactList.get(i);

            if (contact.isPerson()) {
                Person person = (Person) contact;
                name = person.getName() + " " + person.getSurname();
            } else {
                Organization organization = (Organization) contact;
                name = organization.getName();
            }
            System.out.println((i + 1) + ". " + name);
        }
    }

}
