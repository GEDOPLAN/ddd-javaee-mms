package de.gedoplan.room.infrastructure;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.common.domain.SeatCapacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.room.domain.Room;
import de.gedoplan.room.domain.RoomRepository;

import java.util.List;

@Repository
public class RoomJpaRepository extends JpaRepository<Long, Room> implements RoomRepository {

  @Override
  public List<Room> findFree(ZonedInterval interval, SeatCapacity capacity) {
    // TODO: Query austesten
    return this.entityManager.createQuery(
        "select distinct r "
            + "from Room r "
            + "where r.capacity.value>:capa "
            + "and not exists ("
            + "select o "
            + "from r.occupancies o "
            + "where :start>=o.interval.start and :start<o.interval.end "
            + "or :end>=o.interval.start and :end<o.interval.end "
            + "or o.interval.start>=:start and o.interval.start<:end "
            + "or o.interval.end>:start and o.interval.end<=:end "
            + ")",
        Room.class)
        .setParameter("capa", capacity.getValue())
        .setParameter("start", interval.getStart())
        .setParameter("end", interval.getEnd())
        .getResultList();
  }
}
