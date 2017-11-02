package de.gedoplan.room;

import de.gedoplan.TestBase;
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

    Room sydney = new Room(new RoomName("Sydney"));
    sydney.getRoomOccupancies().add(new RoomOccupancy(ZonedDateTime.of(2017, 11, 6, 9, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 6, 17, 0, 0, 0, zoneId), "Java-EE-Workshop"));
    sydney.getRoomOccupancies().add(new RoomOccupancy(ZonedDateTime.of(2017, 11, 7, 15, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 7, 16, 0, 0, 0, zoneId), "DDD mit Java EE"));

    Room ballsaal = new Room(new RoomName("Ballsaal"));
    ballsaal.getRoomOccupancies().add(new RoomOccupancy(ZonedDateTime.of(2017, 11, 7, 20, 0, 0, 0, zoneId), ZonedDateTime.of(2017, 11, 7, 21, 30, 0, 0, zoneId), "Casino Night"));

    for (Room room : new Room[] { sydney, ballsaal }) {
      this.roomRepository.persist(room);

      this.log.debug("Persist: " + room);
    }
  }

}
