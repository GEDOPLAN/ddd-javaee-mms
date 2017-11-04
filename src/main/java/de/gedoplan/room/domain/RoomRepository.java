package de.gedoplan.room.domain;

import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.common.domain.attribute.Capacity;
import de.gedoplan.common.domain.attribute.ZonedInterval;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/**
 * (JPA) Repository for {@link Room}.
 *
 * @author dw
 */
@ApplicationScoped
@Transactional(value = TxType.MANDATORY)
public class RoomRepository extends JpaRepository<Long, Room> {

  public List<Room> findFree(ZonedInterval interval, Capacity capacity) {
    // TODO: Die u. a. Query scheint günstiger, wird aber von EclipseLink abgelehnt
    return this.entityManager.createQuery(
        "select r "
            + "from Room r "
            + "where r.capacity.value>:capa "
            + "and r not in  ( "
            + "select distinct r2 "
            + "from Room r2 "
            + "join r2.occupancies o "
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

    // return this.entityManager.createQuery(
    // "select distinct r "
    // + "from Room r "
    // + "where r.capacity.value>:capa "
    // + "and not exists ("
    // + "select o "
    // + "from r.occupancies o "
    // + "where :start>=o.interval.start and :start<o.interval.end "
    // + "or :end>=o.interval.start and :end<o.interval.end "
    // + "or o.interval.start>=:start and o.interval.start<:end "
    // + "or o.interval.end>:start and o.interval.end<=:end "
    // + ")",
    // Room.class)
    // .setParameter("capa", capacity.getValue())
    // .setParameter("start", interval.getStart())
    // .setParameter("end", interval.getEnd())
    // .getResultList();
  }
}