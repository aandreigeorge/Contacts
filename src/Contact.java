package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Contact {

    private final String name, surname, number;

    private Contact(ContactBuilder contact) {
        this.name = contact.name;
        this.surname = contact.surname;
        this.number = contact.number;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getNumber() {
        return number.isEmpty() ? "[no number]" : number;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname() + ", " + getNumber();
    }


    static class ContactBuilder {

        private String name;
        private String surname;
        private String number = "";

        public ContactBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ContactBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ContactBuilder setNumber(String number) {

            if (isValidNumber(number)) {
                this.number = number;
            } else {
                this.number = "";
            }
            return this;
        }

        private boolean isValidNumber(String number) {

            String phoneNumberPattern = "^\\+?(?:\\([a-zA-Z0-9]{2,}\\)|[a-zA-Z0-9]+)(?:[\\s-](?:\\([a-zA-Z0-9]{2,}\\)|[a-zA-Z0-9]{2,}))*$";
            Pattern pattern = Pattern.compile(phoneNumberPattern);
            Matcher matcher = pattern.matcher(number);

            if(!matcher.matches()) {
                return false;
            }

            int openParentheses = 0;
            int closeParentheses = 0;
            int wrappedGroups = 0;
            boolean inParentheses = false;

            for(char ch : number.toCharArray()) {
                if(ch == '(') {
                    if(inParentheses) {
                        return false;
                    }
                    inParentheses = true;
                    openParentheses++;
                }
                if(ch == ')') {
                    if(!inParentheses) {
                        return false;
                    }
                    inParentheses = false;
                    closeParentheses++;
                    wrappedGroups++;
                }
            }

            if(openParentheses != closeParentheses) {
                return false;
            }

            return wrappedGroups <= 1;

        }

        public Contact build() {
            return new Contact(this);
        }

    }
}
