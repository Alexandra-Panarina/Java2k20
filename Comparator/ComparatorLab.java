package Comparator;

import java.util.Comparator;

class Person implements Comparable<Person> {
    private String name;
    private String surname;
    private Integer age;

    public Person() {
    }

    public Person(String __name, String __surname, Integer __age) {
        name = __name;
        surname = __surname;
        age = __age;

    }

    public String getName() { return this.name; }
    public String getSurname() { return this.surname; }
    public Integer getAge() { return this.age; }

    @Override
    public int compareTo(Person otherPerson) {
        return Comparator.comparing(Person::getName)
                .thenComparing(Person::getSurname)
                .thenComparing(Person::getAge)
                .compare(this, otherPerson);
    }
}
