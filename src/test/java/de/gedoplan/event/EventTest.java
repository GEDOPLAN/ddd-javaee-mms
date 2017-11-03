package de.gedoplan.event;

import de.gedoplan.TestBase;
import de.gedoplan.common.domain.SeatCapacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.event.domain.Event;
import de.gedoplan.event.domain.EventRepository;
import de.gedoplan.event.domain.attribute.EventName;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventTest extends TestBase {

  @Inject
  EventRepository eventRepository;

  @Inject
  Log log;

  @Test
  public void test_00_clearData() {
    this.log.info("----- test_00_clearData -----");

    this.eventRepository.removeAll();
  }

  @Test
  public void test_01_insertData() throws Exception {
    this.log.info("----- test_01_insertData -----");

    ZoneId zoneId = ZoneId.of("Europe/Berlin");

    Event javaEeWorkshop = new Event(new EventName("Java-EE-Workshop"), new SeatCapacity(50),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 6, 9, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 6, 17, 0, 0, 0, zoneId)));
    Event dddMitJavaEe = new Event(new EventName("DDD mit Java EE"), new SeatCapacity(30),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 7, 15, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 7, 16, 0, 0, 0, zoneId)));
    Event casinoNight = new Event(new EventName("Casino Night"), new SeatCapacity(500),
        new ZonedInterval(ZonedDateTime.of(2017, 11, 7, 20, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 7, 21, 30, 0, 0, zoneId)));

    for (Event event : new Event[] { javaEeWorkshop, dddMitJavaEe, casinoNight }) {
      this.eventRepository.persist(event);

      this.log.debug("Persist: " + event);
    }
  }

  @Test
  public void test_02_findAll() {
    this.log.debug("----- test_02_findAll -----");

    this.eventRepository
        .findAll()
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }
}
