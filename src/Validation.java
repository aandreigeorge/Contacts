import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validation {

   static boolean isValidPhoneNumber (String number) {

        String numberFormatFilter = "\\+?(\\([0-9a-zA-Z]+\\)|[0-9a-zA-Z]+([ -][(][0-9a-zA-Z]{2,}[)])?)([ -][0-9a-zA-Z]{2,})*";
        Pattern pattern = Pattern.compile(numberFormatFilter);

        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    static boolean isValidBirthDate(String birthDate) {
        //TO ADD VALIDATION LOGIC
        return false;
    }

    static boolean isValidGender(String gender) {

        gender = gender.toUpperCase();

        return gender.equals("M") || gender.equals("F");
    }


}
