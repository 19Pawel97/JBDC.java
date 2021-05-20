package pl.sda.main;

import pl.sda.model.Person;
import pl.sda.service.PersonService;

public class PersonMain {
    public static void main(String[] args) {
        PersonService personService = new PersonService();

        personService.getPersonBornBefore1990().stream()
                .forEach(System.out::println);

        personService.getKowalscy().stream()
                .forEach(person -> System.out.println(person.getFirstName() + " " + person.getLastName()));

        Person person1 = new Person(1, "Michał", "Suwała", "98765432110");
        Person person2 = new Person(2, "Antoni", "Spawacz", "95020229777");
        Person person3 = new Person(3, "Anna", "Kowalski", "85296374118");
        Person person4 = new Person(4, "Michał", "Nowak", "95020229776");

        if (personService.isPersonInDB(person4)) {
            System.out.println("The person is in the data base");
        } else {
            System.out.println("The person is not in the data base");
        }
    }

}
