package techno.soft.regex;

import junit.framework.Assert;
import junit.framework.TestCase;
import techno.soft.regex.impl.TelephoneRegex;

import java.util.List;

public class TelephoneRegexTest extends TestCase {
    private final ITelephoneRegex regex = new TelephoneRegex();

    public void test_isValid() {
        List<String> samples = List.of(
                "+79122222222",
                "89122222222",
                "+7(912)2222222",
                "8 (912) 222 22 22",
                "8 (912) 2222222",
                "8 912 222 2222",
                "+7 912 222 2222"
        );

        for (String sample : samples) {
            boolean result = regex.isValid(sample);
            Assert.assertTrue("Phone regex is incorrect for value = %s (result = %s)".formatted(sample, result), result);
        }
    }

    public void test_isValid_negative_case() {
        List<String> samples = List.of(
                "79122222222",
                "9122222222",
                "(912)2222222",
                "8 (912)",
                "(912) 2222222",
                "8 91222222221",
                "791222222222"
        );

        for (String sample : samples) {
            boolean result = regex.isValid(sample);
            Assert.assertFalse("Phone regex is incorrect for value = %s (result = %s)".formatted(sample, result), result);
        }
    }
}
