import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
 *
 * @author chanwook
 */
public class StreamTest {

    @Test
    public void tryFirst() throws Exception {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // sum
        final int sum1 = Arrays.stream(arr).sum();
        assert sum1 == 55;

        // average
        final OptionalDouble sum2 = Arrays.stream(arr).average();
        assert sum2.getAsDouble() == 5.5;

        // sum even num
        final int sum3 = Arrays.stream(arr).filter(i -> i % 2 == 0).sum();
        assert sum3 == 30;

        // foreach
        Arrays.stream(arr).forEach(i -> System.out.println("loop value is " + i)); // forEach() return type is void...@@

        // map

        // reduce

        // parallel

        // min/max
    }

    @Test
    public void takeTwo() throws Exception {

        // map - group
        List<Person> people = new ArrayList<>();
        people.add(new Person("Park", 300));
        people.add(new Person("Lee", 300));
        people.add(new Person("Kim", 300));

        final List<String> nameList = people.stream().map(Person::getName).collect(Collectors.toList());
        assert nameList.size() == 3;
        assert nameList.get(0) == "Park";
        assert nameList.get(1) == "Lee";
        assert nameList.get(2) == "Kim";

        // collectors - joinging
        final String merged = people.stream().map(Person::getName).collect(Collectors.joining(", "));
        System.out.println("m: " + merged);
        assert merged.equals("Park, Lee, Kim");

        List<Person> people2 = new ArrayList<>();
        people2.add(new Person("Park", 30, 10000, "A"));
        people2.add(new Person("Lee", 25, 2000, "B"));
        people2.add(new Person("Kim", 36, 3000, "A"));
        people2.add(new Person("Son", 33, 4000, "A"));
        people2.add(new Person("Koo", 39, 5000, "C"));
        people2.add(new Person("Hong", 45, 6000, "B"));

        // collectors - sum
        final Integer ageSum = people2.stream().collect(Collectors.summingInt(Person::getAge));
        System.out.println("age sum: " + ageSum);
        assert ageSum == 208;

        // collectors - groupby
        final Map<String, Integer> group = people2.stream().collect(
                Collectors.groupingBy(Person::getDepartment, Collectors.summingInt(Person::getSalary)));
        System.out.println("groupby: " + group);
        assert group.get("A") == 17000;
        assert group.get("B") == 8000;
        assert group.get("C") == 5000;

        // collectors - partitioning
        final Map<Boolean, List<Person>> partition =
                people2.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 33));
        System.out.println("partitioning: " + partition);
        assert partition.get(Boolean.TRUE).size() == 3;
        assert partition.get(Boolean.FALSE).size() == 3;
    }

    static class Person {
        String name;
        int age;
        int salary;
        String department;

        public Person(String name, int age) {
            this.age = age;
            this.name = name;
        }

        public Person(String name, int age, int salary, String department) {
            this.age = age;
            this.department = department;
            this.name = name;
            this.salary = salary;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }
    }
}
