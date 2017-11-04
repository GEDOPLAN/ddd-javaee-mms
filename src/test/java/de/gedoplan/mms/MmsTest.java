package de.gedoplan.mms;

import de.gedoplan.TestBase;
import de.gedoplan.common.domain.attribute.Capacity;
import de.gedoplan.common.domain.attribute.ZonedInterval;
import de.gedoplan.meeting.application.ParticipantAppSvc;
import de.gedoplan.meeting.domain.Meeting;
import de.gedoplan.meeting.domain.MeetingFactory;
import de.gedoplan.meeting.domain.MeetingOptimizeService;
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
  private static final MeetingName DDD_NAME = MeetingName.of("DDD mit Java EE");

  private static final EMail DONALD_EMAIL = EMail.of("donald.duck@entenhausen.city");

  private static final FirstName DONALD_FIRST_NAME = FirstName.of("Donald");

  private static final LastName DUCK_LAST_NAME = LastName.of("Duck");

  private static final MeetingName CASINO_NIGHT_NAME = MeetingName.of("Casino Night");

  private static final ZoneId ZONE_ID = ZoneId.of("Europe/Berlin");

  private static final ZonedDateTime DDD_START = ZonedDateTime.of(2017, 11, 7, 15, 0, 0, 0, ZONE_ID);

  private static final ZonedDateTime CASINO_NIGHT_START = ZonedDateTime.of(2017, 11, 7, 20, 0, 0, 0, ZONE_ID);

  @Inject
  PersonRepository personRepository;

  @Inject
  RoomRepository roomRepository;

  @Inject
  MeetingFactory meetingFactory;
  @Inject
  MeetingRepository meetingRepository;
  @Inject
  ParticipantAppSvc participantAppSvc;

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
        new Person(DUCK_LAST_NAME, FirstName.of("Dagobert"), EMail.of("dagobert.duck@entenhausen.city")),
        new Person(DUCK_LAST_NAME, DONALD_FIRST_NAME, DONALD_EMAIL)
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

    Room partenkirchen = new Room(RoomName.of("Partenkirchen"), Capacity.of(40));

    Room sydney = new Room(RoomName.of("Sydney"), Capacity.of(100));
    sydney.addOccupancy(ZonedInterval.of(ZonedDateTime.of(2017, 11, 6, 7, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 6, 8, 0, 0, 0, ZONE_ID)), "Vorbereitung Workshop-Bestuhlung");
    sydney.addOccupancy(ZonedInterval.of(ZonedDateTime.of(2017, 11, 6, 18, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 6, 19, 0, 0, 0, ZONE_ID)), "Umbau auf Theaterbestuhlung");

    Room ballsaal = new Room(RoomName.of("Ballsaal"), Capacity.of(1000));
    ballsaal.addOccupancy(ZonedInterval.of(ZonedDateTime.of(2017, 11, 7, 19, 0, 0, 0, ZONE_ID), CASINO_NIGHT_START), "Aufstellen großer Tische");

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
        .findFree(ZonedInterval.of(ZonedDateTime.of(2017, 11, 6, 17, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 6, 17, 10, 0, 0, ZONE_ID)), Capacity.of(60))
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }

  @Test
  public void test_21_insertMeetings() throws Exception {
    this.log.info("----- test_21_insertMeetings -----");

    Meeting javaEeWorkshop = this.meetingFactory.create(
        MeetingName.of("Java-EE-Workshop"),
        Capacity.of(40),
        ZonedInterval.of(ZonedDateTime.of(2017, 11, 6, 9, 0, 0, 0, ZONE_ID), ZonedDateTime.of(2017, 11, 6, 17, 0, 0, 0, ZONE_ID)));
    Meeting dddMitJavaEe = this.meetingFactory.create(
        DDD_NAME,
        Capacity.of(30),
        ZonedInterval.of(DDD_START, ZonedDateTime.of(2017, 11, 7, 16, 0, 0, 0, ZONE_ID)));
    Meeting fortran = this.meetingFactory.create(
        MeetingName.of("FORTRAN in der Historie"),
        Capacity.of(10),
        ZonedInterval.of(DDD_START, ZonedDateTime.of(2017, 11, 7, 16, 0, 0, 0, ZONE_ID)));
    Meeting casinoNight = this.meetingFactory.create(
        CASINO_NIGHT_NAME,
        Capacity.of(500),
        ZonedInterval.of(CASINO_NIGHT_START, ZonedDateTime.of(2017, 11, 7, 21, 30, 0, 0, ZONE_ID)));

    for (Meeting meeting : new Meeting[] { javaEeWorkshop, dddMitJavaEe, fortran, casinoNight }) {
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

  @Test
  public void test_31_registerParticipants() {
    this.log.debug("----- test_31_registerParticipants -----");

    this.participantAppSvc.register(
        LastName.of("Wacker"),
        FirstName.of("Willi"),
        EMail.of("willi.wacker@mail.org"),
        DDD_NAME,
        DDD_START);

    this.participantAppSvc.register(
        DUCK_LAST_NAME,
        DONALD_FIRST_NAME,
        DONALD_EMAIL,
        DDD_NAME,
        DDD_START);
  }

  @Inject
  MeetingOptimizeService meetingOptimizeService;

  @Test
  public void test_41_optimizeRoomAssignments() {
    this.log.debug("----- test_41_optimizeRoomAssignments -----");

    this.meetingOptimizeService.optimizeRoomAssignments();
  }
}
