import com.email.validator.EmailValidator;

public class Main {
    public static void main(String[] args) {
        EmailValidator emailValidator = new EmailValidator ();
        String email1 = "user@";

        System.out.println ("Has local part? " + emailValidator.hasLocalPart (email1) );
    }
}