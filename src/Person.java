import java.io.Serializable;
import java.util.regex.Pattern;


class Person extends Contact implements Serializable {

    private String name, surname, birthDate, gender;


    @Override
    String getFullName() {
        return this.name + " " + this.surname;
    }

    private void setOrUpdateName(String name) {
        this.name = name;
    }

    private void setOrUpdateSurname(String surname) {
        this.surname = surname;
    }

    private void setOrUpdateGender(String gender) {

        if (Validation.isValidGender(gender)) {
            this.gender = gender;
        } else {
            this.gender = "[no data]";
            System.out.println("Bad gender!");
        }
    }

    private void setOrUpdateBirthdate(String birthdate) {

        if (Validation.isValidBirthDate(birthdate)) {
            this.birthDate = birthdate;
        } else {
            this.birthDate = "[no data]";
            System.out.println("Bad birth date!");
        }
    }

    @Override
    String getModifiableFields() {
        return "name, surname, birth, gender, number";
    }

    @Override
    void setField(String fieldName, String fieldValue) {

        fieldName = fieldName.toUpperCase();

        switch (fieldName) {
            case "NAME" -> setOrUpdateName(fieldValue);
            case "SURNAME" -> setOrUpdateSurname(fieldValue);
            case "BIRTH" -> setOrUpdateBirthdate(fieldValue);
            case "GENDER" -> setOrUpdateGender(fieldValue);
            case "NUMBER" -> setOrUpdatePhoneNumber(fieldValue);
        }
    }

    @Override
    String getFieldValue(String fieldName) {

        fieldName = fieldName.toUpperCase();
        String fieldValue = "[no data]";

        switch (fieldName) {
            case "NAME" -> fieldValue = this.name;
            case "SURNAME" -> fieldValue = this.surname;
            case "BIRTH" -> fieldValue = this.birthDate;
            case "GENDER" -> fieldValue = this.gender;
            case "NUMBER" -> fieldValue = getPhoneNumber();
        }
        return fieldValue;
    }

    @Override
    boolean exists(String query) {

        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(this.name).find() || pattern.matcher(this.surname).find() || pattern.matcher(getPhoneNumber()).find();
    }


    @Override
    public String toString() {

        String phoneNumberCreationAndEditTime = super.toString();
        return """
                Name: %s
                Surname: %s
                Birth date: %s
                Gender: %s
                %s""".formatted(this.name, this.surname, this.birthDate, this.gender, phoneNumberCreationAndEditTime);
    }


}
