import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * http://winterbe.com/posts/2014/03/16/java-8-tutorial/
 *
 * @author chanwook
 */
public class FunctionalTest {

    @Test
    public void defaultMethod() throws Exception {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        assert formula.calculate(100) == 100.0;
        assert formula.sqrt(16) == 4.0;
    }

    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    @Test
    public void lambdaBasic() throws Exception {

        final List<String> nameList = Arrays.asList("Kim", "Lee", "Park", "Choi", "Hong");
        Collections.sort(nameList, (a, b) -> a.compareTo(b));

        assert nameList.get(0).equals("Choi");
        assert nameList.get(1).equals("Hong");
        assert nameList.get(2).equals("Kim");
        assert nameList.get(3).equals("Lee");
        assert nameList.get(4).equals("Park");
    }

    @Test
    public void functionalInterface() throws Exception {
//        TypeConverter<String, Integer> converter = (from) -> Integer.valueOf(from); // Case 1
        TypeConverter<String, Integer> converter = Integer::valueOf; // Case 2
        final Integer v = converter.convert("123");
        assert v == 123;
    }

    @FunctionalInterface
    public interface TypeConverter<D, T> {
        T convert(D d);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Test
    public void methodAndConstructorReference() throws Exception {
        PersonFactory factory = Person::new;
        final Person p = factory.create("chanwook", 100);

        assert p != null;
        assert p.name.equals("chanwook");
        assert p.age == 100;
    }

    static class Person {
        String name;
        int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.age = age;
            this.name = name;
        }
    }

    interface PersonFactory<T extends Person> {
        T create(String name, int age);
    }

    @Test
    public void lambdaScope() throws Exception {

        final int num1 = 1; // final
        TypeConverter<String, Integer> converter = (f) -> Integer.valueOf(f) + num1;
        final Integer r1 = converter.convert("5");
        assert r1 == 6;

        final int num2 = 1; // implicit final
        TypeConverter<String, Integer> converter2 = (f) -> Integer.valueOf(f) + num2;
        final Integer r2 = converter2.convert("5");
        assert r2 == 6;

        // num2 += 2; compile error

    }
}
