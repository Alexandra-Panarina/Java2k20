package Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("b", "b", 10));
        personList.add(new Person("b", "b", 20));
        personList.add(new Person("a", "b", 30));
        personList.add(new Person("b", "a", 40));
        personList.add(new Person("a", "a", 20));
        personList.add(new Person("c", "a", 10));
        personList.add(new Person("b", "c", 50));
        personList.add(new Person("a", "b", 30));

        for(Person p : personList) {
            System.out.println("Name: " + p.getName() + ", Surname: " + p.getSurname() + ", Age: " + p.getAge());
        }

        Collections.sort(personList);
        System.out.println("Sorted:");

        for(Person p : personList) {
            System.out.println("Name: " + p.getName() + ", Surname: " + p.getSurname() + ", Age: " + p.getAge());
        }
    }

}
