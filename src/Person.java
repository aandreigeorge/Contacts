class Person extends Contact {

    private String name, surname, birthDate, gender;

    Person(String name, String surname, String birthDate, String gender, String phoneNumber) {
        super(phoneNumber, true);
        setOrUpdateName(name);
        setOrUpdateSurname(surname);
        setOrUpdateBirthdate(birthDate);
        setOrUpdateGender(gender);
    }

    String getName() {
        return this.name;
    }

    String getSurname() {
        return this.surname;
    }

    private String getBirthDate() {
        return this.birthDate;
    }

    private String getGender() {
        return this.gender;
    }

    void setOrUpdateName(String name) {
        this.name = name;
    }

    void setOrUpdateSurname(String surname) {
        this.surname = surname;
    }

    void setOrUpdateGender(String gender) {

        if (Validation.isValidGender(gender)) {
            this.gender = gender;
        } else {
            this.gender = "[no data]";
        }
    }

    void setOrUpdateBirthdate(String birthdate) {

        if(Validation.isValidBirthDate(birthdate)){
            this.birthDate = birthdate;
        } else {
            this.birthDate = "[no data]";
        }

    }

    @Override
    public String toString() {
        String numberCreationAndEditTime = super.toString();
        return """
                Name: %s
                Surname: %s
                Birth date: %s
                Gender: %s
                %s
                """.formatted(getName(), getSurname(), getBirthDate(), getGender(), numberCreationAndEditTime);
    }


}
