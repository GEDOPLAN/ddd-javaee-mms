package de.gedoplan.meeting.domain;

import de.gedoplan.common.domain.attribute.Capacity;
import de.gedoplan.common.domain.attribute.ZonedInterval;
import de.gedoplan.meeting.domain.attribute.MeetingName;
import de.gedoplan.room.domain.Room;
import de.gedoplan.room.domain.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Factory for {@link Meeting}.
 *
 * @author dw
 */
@ApplicationScoped
public class MeetingFactory {

  @Inject
  RoomRepository roomRepository;

  public Meeting create(MeetingName name, Capacity capacity, ZonedInterval interval) {
    Meeting meeting = new Meeting(name, capacity, interval);

    List<Room> freeRooms = this.roomRepository.findFree(interval, capacity);
    if (freeRooms.isEmpty()) {
      throw new IllegalArgumentException("No room available");
    }

    Room room = freeRooms.stream().collect(Collectors.minBy((Room r1, Room r2) -> r1.getCapacity().compareTo(r2.getCapacity()))).get();
    meeting.changeRoom(room);

    return meeting;
  }

}
