package de.gedoplan.room.domain;

import de.gedoplan.baselibs.persistence.domain.DomainRepository;
import de.gedoplan.common.domain.SeatCapacity;
import de.gedoplan.common.domain.ZonedInterval;

import java.util.List;

public interface RoomRepository extends DomainRepository<Long, Room> {
  public List<Room> findFree(ZonedInterval interval, SeatCapacity capacity);
}
