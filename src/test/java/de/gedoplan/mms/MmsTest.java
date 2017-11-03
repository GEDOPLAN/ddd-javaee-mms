package de.gedoplan.mms;

import de.gedoplan.TestBase;
import de.gedoplan.common.domain.SeatCapacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.meeting.domain.Meeting;
import de.gedoplan.meeting.domain.MeetingFactory;
import de.gedoplan.meeting.domain.MeetingRepository;
import de.gedoplan.meeting.domain.attribute.MeetingName;
import de.gedoplan.person.domain.Person;
import de.gedoplan.person.domain.PersonRepository;
import de.gedoplan.person.domain.attribute.EMail;
import de.gedoplan.person.domain.attribute.FirstName;
import de.gedoplan.person.domain.attribute.LastName;
import de.gedoplan.room.domain.Room;
import de.gedoplan.room.domain.RoomRepository;
import de.gedoplan.room.domain.attribute.RoomName;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MmsTest extends TestBase {
  private static final ZoneId ZONE_ID = ZoneId.of("Europe/Berlin");

  @Inject
  PersonRepository personRepository;

  @Inject
  RoomRepository roomRepository;

  @Inject
  MeetingFactory meetingFactory;
  @Inject
  MeetingRepository meetingRepository;

  @Inject
  Log log;

  @Test
  public void test_00_clearData() {
    this.log.info("----- test_00_clearData -----");

    this.meetingRepository.removeAll();
    this.personRepository.removeAll();
    this.roomRepository.removeAll();
  }

  @Test
  public void test_01_insertPersons() throws Exception {
    this.log.info("----- test_01_insertPersons -----");

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
  public void test_02_findAllPersons() {
    this.log.debug("----- test_02_findAllPersons -----");

    this.personRepository
        .findAll()
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }

  @Test
  public void test_11_insertRooms() {
    this.log.info("----- test_11_insertRooms -----");

    Room partenkirchen = new Room(new RoomName("Partenkirchen"), new SeatCapacity(40));

    Room sydney = new Room(new RoomName("Sydney"), new SeatCapacity(100));
    sydney.addOccupancy(new ZonedInterval(ZonedDateTime.of(2017, 11, 6, 7, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 6, 8, 0, 0, 0, ZONE_ID)), "Vorbereitung Workshop-Bestuhlung");
    sydney.addOccupancy(new ZonedInterval(ZonedDateTime.of(2017, 11, 6, 18, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 6, 19, 0, 0, 0, ZONE_ID)), "Umbau auf Theaterbestuhlung");

    Room ballsaal = new Room(new RoomName("Ballsaal"), new SeatCapacity(1000));
    ballsaal.addOccupancy(new ZonedInterval(ZonedDateTime.of(2017, 11, 7, 19, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 7, 20, 0, 0, 0, ZONE_ID)), "Aufstellen großer Tische");

    for (Room room : new Room[] { partenkirchen, sydney, ballsaal }) {
      this.roomRepository.persist(room);

      this.log.debug("Persist: " + room);
    }
  }

  @Test
  public void test_12_findAllRooms() {
    this.log.debug("----- test_12_findAllRooms -----");

    this.roomRepository
        .findAll()
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }

  @Test
  public void test_13_findFreeRooms() {
    this.log.debug("----- test_13_findFreeRooms -----");

    this.roomRepository
        .findFree(new ZonedInterval(ZonedDateTime.of(2017, 11, 6, 9, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 6, 17, 0, 0, 0, ZONE_ID)), new SeatCapacity(60))
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }

  @Test
  public void test_21_insertMeetings() throws Exception {
    this.log.info("----- test_21_insertMeetings -----");

    Meeting javaEeWorkshop = this.meetingFactory.create(
        new MeetingName("Java-EE-Workshop"),
        new SeatCapacity(50),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 6, 9, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 6, 17, 0, 0, 0, ZONE_ID)));
    Meeting dddMitJavaEe = this.meetingFactory.create(
        new MeetingName("DDD mit Java EE"),
        new SeatCapacity(30),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 7, 15, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 7, 16, 0, 0, 0, ZONE_ID)));
    Meeting casinoNight = this.meetingFactory.create(
        new MeetingName("Casino Night"),
        new SeatCapacity(500),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 7, 20, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 7, 21, 30, 0, 0, ZONE_ID)));

    for (Meeting meeting : new Meeting[] { javaEeWorkshop, dddMitJavaEe, casinoNight }) {
      this.meetingRepository.persist(meeting);

      this.log.debug("Persist: " + meeting);
    }
  }

  @Test
  public void test_22_findAllMeetings() {
    this.log.debug("----- test_02_findAllMeetings -----");

    this.meetingRepository
        .findAll()
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }
}
