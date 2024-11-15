import com.email.validator.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class EmailValidatorTest {
    private EmailValidator emailValidator;

    @BeforeEach
    public void setUp() {
        emailValidator = new EmailValidator ();
    }

    @Test
    public void testHasLocalPart() {
        assertTrue (emailValidator.hasLocalPart ("user@example.com"));
        assertFalse (emailValidator.hasLocalPart ("@example.com"));
    }

    @Test
    public void testHasDomainPart() {
        assertTrue (emailValidator.hasDomainPart ("user@example.com"));
        assertFalse (emailValidator.hasDomainPart ("user@"));
        assertTrue (emailValidator.hasDomainPart ("user@.com"));
        assertTrue (emailValidator.hasDomainPart ("user@example"));
        assertFalse (emailValidator.hasDomainPart ("userexample.com"));
    }

    @Test
    public void testHasDisallowedSymbols() {
        assertFalse (emailValidator.hasDisallowedSymbols ("user@example.com"));
        assertTrue (emailValidator.hasDisallowedSymbols ("user!@example,com"));
        assertTrue (emailValidator.hasDisallowedSymbols ("user;@example.com"));
    }

    @Test
    public void testIsLengthValid() {
        assertTrue (emailValidator.isLengthValid ("user123@example.com"));
        assertFalse (emailValidator.isLengthValid ("u".repeat(255) + "@example.com"));
    }

    @Test
    public void testIsDomainPartValid() {
        assertTrue (emailValidator.isDomainPartValid ("user@example.com"));
        assertFalse (emailValidator.isDomainPartValid ("user@examp;le.com"));
        assertFalse (emailValidator.isDomainPartValid ("user@examplecom"));
        assertTrue (emailValidator.isDomainPartValid ("user@[192.168.0.1]"));
        assertFalse (emailValidator.isDomainPartValid ("user@[192.168.0.1"));
        assertFalse (emailValidator.isDomainPartValid ("user@[192.168.0.01]"));
    }

    @Test
    public void testIsLocalPartValid() {
        assertTrue (emailValidator.isLocalPartValid ("user@example.com"));
        assertFalse (emailValidator.isLocalPartValid ("user!@example.com"));
        assertFalse (emailValidator.isLocalPartValid ("u".repeat (67) + "@example.com"));
        assertFalse (emailValidator.isLocalPartValid ("@example.com"));
    }

    @Test
    public void testHasIpAddressInDomain() {
        assertTrue (emailValidator.hasIpAddressInDomain("user@[192.168.0.1]"));
    }

    // testIsEmailValid TO DO

}
