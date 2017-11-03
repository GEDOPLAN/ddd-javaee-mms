package de.gedoplan.meeting;

import de.gedoplan.TestBase;
import de.gedoplan.common.domain.SeatCapacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.meeting.domain.Meeting;
import de.gedoplan.meeting.domain.MeetingRepository;
import de.gedoplan.meeting.domain.attribute.MeetingName;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeetingTest extends TestBase {

  @Inject
  MeetingRepository meetingRepository;

  @Inject
  Log log;

  @Test
  public void test_00_clearData() {
    this.log.info("----- test_00_clearData -----");

    this.meetingRepository.removeAll();
  }

  @Test
  public void test_01_insertData() throws Exception {
    this.log.info("----- test_01_insertData -----");

    ZoneId zoneId = ZoneId.of("Europe/Berlin");

    Meeting javaEeWorkshop = new Meeting(new MeetingName("Java-EE-Workshop"), new SeatCapacity(50),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 6, 9, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 6, 17, 0, 0, 0, zoneId)));
    Meeting dddMitJavaEe = new Meeting(new MeetingName("DDD mit Java EE"), new SeatCapacity(30),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 7, 15, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 7, 16, 0, 0, 0, zoneId)));
    Meeting casinoNight = new Meeting(new MeetingName("Casino Night"), new SeatCapacity(500),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 7, 20, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 7, 21, 30, 0, 0, zoneId)));

    for (Meeting meeting : new Meeting[] { javaEeWorkshop, dddMitJavaEe, casinoNight }) {
      this.meetingRepository.persist(meeting);

      this.log.debug("Persist: " + meeting);
    }
  }

  @Test
  public void test_02_findAll() {
    this.log.debug("----- test_02_findAll -----");

    this.meetingRepository
        .findAll()
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }
}
