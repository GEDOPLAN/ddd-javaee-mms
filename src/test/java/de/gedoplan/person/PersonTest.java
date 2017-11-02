package de.gedoplan.person;

import de.gedoplan.TestBase;
import de.gedoplan.person.domain.Person;
import de.gedoplan.person.domain.PersonRepository;
import de.gedoplan.person.domain.attribute.EMail;
import de.gedoplan.person.domain.attribute.FirstName;
import de.gedoplan.person.domain.attribute.LastName;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonTest extends TestBase {
  @Inject
  PersonRepository personRepository;

  @Inject
  Log log;

  @Test
  public void test_00_clearData() {
    this.log.info("----- test_00_clearData -----");

    this.personRepository.removeAll();
  }

  @Test
  public void test_01_insertData() throws Exception {
    this.log.info("----- test_01_insertData -----");

    Person[] persons = {
        new Person(new LastName("Duck"), new FirstName("Dagobert"), new EMail("dagobert.duck@entenhausen.city")),
        new Person(new LastName("Duck"), new FirstName("Donald"), new EMail("donald.duck@entenhausen.city"))
    };
    for (Person person : persons) {
      this.personRepository.persist(person);

      this.log.debug("Persist: " + person);
    }
  }

  @Test
  public void test_02_findAll() {
    this.log.debug("----- test_02_findAll -----");

    this.personRepository
        .findAll()
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }

}
