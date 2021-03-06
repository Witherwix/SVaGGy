package tests.SvgElements;

import com.michalso.svaggy.display.Utils.SvgUtils;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestUtils {

    @Test
    public void testQuoteValueFloat() {
        String strQuote = SvgUtils.quoteValue(1.45);

        assertEquals("\"1.45\"", strQuote);
    }

    @Test
    public void testQuoteValueString() {
        String strQuote = SvgUtils.quoteValue("ala ma kota");

        assertEquals("\"ala ma kota\"", strQuote);
    }

    @Test
    public void testOptionalStringConcatenation() {
        //String strConcat = SvgUtils.optStringConcat(Optional.of("ala"), Optional.of("ma"), Optional.of(1000));

        //assertEquals(strConcat, "\"ala\" \"ma\" \"1000\"");
    }

    @Test
    public void testOptionalStringConcatenationWithFixes() {
        //String strConcat = SvgUtils.optStringConcat(Optional.of("ala"), Optional.of("ma"), Optional.of(1000));

        //assertEquals(strConcat, "\"ala\" \"ma\" \"1000\"");
    }
}
