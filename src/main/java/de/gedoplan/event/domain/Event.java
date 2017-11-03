package de.gedoplan.event.domain;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;
import de.gedoplan.common.domain.SeatCapacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.event.domain.attribute.EventName;
import de.gedoplan.person.domain.Person;
import de.gedoplan.room.domain.Room;
import de.gedoplan.room.domain.RoomOccupancy;
import de.gedoplan.room.domain.RoomRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.jboss.weld.exceptions.IllegalArgumentException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends GeneratedLongIdEntity {

  @NotNull
  @Valid
  private EventName name;

  @NotNull
  @Valid
  private ZonedInterval interval;

  @NotNull
  @Valid
  private SeatCapacity capacity;

  @ManyToMany
  @Setter(AccessLevel.NONE)
  private Set<Person> participants;

  @ManyToOne
  private Room room;

  @Inject
  @Transient
  RoomRepository roomRepository;

  public Event(EventName name, SeatCapacity capacity, ZonedInterval interval) {
    List<Room> freeRooms = this.roomRepository.findFree(interval, capacity);
    if (freeRooms.isEmpty()) {
      throw new IllegalArgumentException("No room available");
    }

    this.name = name;
    this.interval = interval;
    this.capacity = capacity;

    this.participants = new HashSet<>();

    this.room = freeRooms.stream().collect(Collectors.minBy((Room r1, Room r2) -> r1.getCapacity().compareTo(r2.getCapacity()))).get();

    // TODO: Änderung in Room OK?
    this.room.getOccupancies().add(new RoomOccupancy(this.interval, name.getValue()));
  }

}
