import java.io.Serializable;
import java.util.regex.Pattern;


class Organization extends Contact implements Serializable {

    private String name, address;


    @Override
    String getFullName() {
        return this.name;
    }

    private void setOrUpdateName(String name) {
        this.name = name;
    }

    private void setOrUpdateAddress(String address) {
        this.address = address;
    }

    @Override
    String getModifiableFields() {
        return "name, address, number";
    }

    @Override
    void setField(String fieldName, String fieldValue) {

        fieldName = fieldName.toUpperCase();

        switch (fieldName) {
            case "NAME" -> setOrUpdateName(fieldValue);
            case "ADDRESS" -> setOrUpdateAddress(fieldValue);
            case "NUMBER" -> setOrUpdatePhoneNumber(fieldValue);
        }
    }

    @Override
    String getFieldValue(String fieldName) {

        fieldName = fieldName.toUpperCase();
        String value = "[no data]";

        switch (fieldName) {
            case "NAME" -> value = this.name;
            case "ADDRESS" -> value = this.address;
            case "NUMBER" -> value = getPhoneNumber();
        }
        return value;
    }

    @Override
    boolean exists(String query) {

        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(this.name).find() || pattern.matcher(getPhoneNumber()).find();
    }

    @Override
    public String toString() {

        String phoneNumberCreationAndEditTime = super.toString();
        return """
                Organization name: %s
                Address: %s
                %s
                """.formatted(this.name, this.address, phoneNumberCreationAndEditTime);
    }


}
