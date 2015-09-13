import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.Assert.fail;

/**
 * @author chanwook
 */
public class OptionalTest {
    @Test
    public void nonNull() throws Exception {

        final Optional<String> optional = Optional.of("chanwook");

        assert optional.isPresent() == true;
        assert optional.get() == "chanwook";
    }

    @Test
    public void nullable() throws Exception {
        try {
            String nullVar = null;
            Optional.of(nullVar);
            fail();
        } catch (NullPointerException npe) {
            // throw NPE
        }

        String nullVar = null;
        final Optional<String> optionalWithNull = Optional.ofNullable(nullVar);
        assert optionalWithNull.isPresent() == false;
        try {
            optionalWithNull.get();
            fail();
        } catch (NoSuchElementException nsee) {
        }

        assert optionalWithNull.orElse("default-value") == "default-value";
    }

    @Test
    public void filter() throws Exception {

        final Predicate<String> predicate = p -> p.length() > 5;

        final Optional<String> optional1 = Optional.of("chanwook");
        final Optional<String> result1 = optional1.filter(predicate);
        assert result1.get() == "chanwook";

        // null == return this
        final Optional<String> optional2 = Optional.ofNullable(null);
        final Optional<String> result2 = optional2.filter(predicate);
        assert result2 == optional2;

        // false == Optional.empty()
        final Optional<String> optional3 = Optional.of("short");
        final Optional<String> result3 = optional3.filter(predicate);
        assert result3.isPresent() == false;
    }

}
