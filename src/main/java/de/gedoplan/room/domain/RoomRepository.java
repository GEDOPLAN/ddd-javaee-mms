package de.gedoplan.room.domain;

import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.common.domain.Capacity;
import de.gedoplan.common.domain.ZonedInterval;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class RoomRepository extends JpaRepository<Long, Room> {

  public List<Room> findFree(ZonedInterval interval, Capacity capacity) {
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