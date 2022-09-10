import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void removeNonAlphanumericChars() {
        String str = "!h$e%l:l(o w$o@r.l|d*123";
        assertEquals("helloworld123", Utils.RemoveNonAlphanumericChars(str));
    }

    @Test
    void uniqueCharacters() {
        String str = "aabbbbcdeffgghh!";
        assertEquals("abcdefgh!", String.valueOf(Utils.UniqueCharacters(str)));
    }
}