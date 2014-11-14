package common.language;

import static org.junit.Assert.*;

import org.junit.Test;

public class LanguageTest {

    @Test
    public void testHandleGetObjectString() {
        assertEquals("Dies ist ein Test",Language.getInstance().getString("test"));
    }
}
