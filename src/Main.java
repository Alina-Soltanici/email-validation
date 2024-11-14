import com.email.validator.EmailValidator;

public class Main {
    public static void main(String[] args) {
        EmailValidator emailValidator = new EmailValidator ();
        String email1 = "soltanicialina777@gmail.net";

        System.out.println ("Is email 1 valid?: " + emailValidator.isEmailValid (email1));
    }
}