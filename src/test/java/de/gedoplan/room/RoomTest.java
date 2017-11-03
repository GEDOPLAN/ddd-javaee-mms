package de.gedoplan.room;

import de.gedoplan.TestBase;
import de.gedoplan.common.domain.SeatCapacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.room.domain.Room;
import de.gedoplan.room.domain.RoomOccupancy;
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
public class RoomTest extends TestBase {

  @Inject
  RoomRepository roomRepository;

  @Inject
  Log log;

  @Test
  public void test_00_clearData() {
    this.log.info("----- test_00_clearData -----");

    this.roomRepository.removeAll();
  }

  @Test
  public void test_01_insertData() throws Exception {
    this.log.info("----- test_01_insertData -----");

    ZoneId zoneId = ZoneId.of("Europe/Berlin");

    Room partenkirchen = new Room(new RoomName("Partenkirchen"), new SeatCapacity(40));

    Room sydney = new Room(new RoomName("Sydney"), new SeatCapacity(100));
    sydney.getRoomOccupancies()
        .add(new RoomOccupancy(new ZonedInterval(ZonedDateTime.of(2017, 11, 6, 7, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 6, 8, 0, 0, 0, zoneId)), "Vorbereitung Workshop-Bestuhlung"));
    sydney.getRoomOccupancies()
        .add(new RoomOccupancy(new ZonedInterval(ZonedDateTime.of(2017, 11, 6, 18, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 6, 19, 0, 0, 0, zoneId)), "Umbau auf Theaterbestuhlung"));

    Room ballsaal = new Room(new RoomName("Ballsaal"), new SeatCapacity(1000));
    ballsaal.getRoomOccupancies()
        .add(new RoomOccupancy(new ZonedInterval(ZonedDateTime.of(2017, 11, 7, 19, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 7, 20, 00, 0, 0, zoneId)), "Aufstellen großer Tische"));

    for (Room room : new Room[] { partenkirchen, sydney, ballsaal }) {
      this.roomRepository.persist(room);

      this.log.debug("Persist: " + room);
    }
  }

  @Test
  public void test_02_findAll() {
    this.log.debug("----- test_02_findAll -----");

    this.roomRepository
        .findAll()
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }
}
