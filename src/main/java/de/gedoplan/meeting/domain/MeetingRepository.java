package de.gedoplan.meeting.domain;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.meeting.domain.attribute.MeetingName;

import java.time.ZonedDateTime;

import javax.persistence.TypedQuery;

@Repository
public class MeetingRepository extends JpaRepository<Long, Meeting> {

  public Meeting findByNameAndStart(MeetingName meetingName, ZonedDateTime start) {
    TypedQuery<Meeting> query = this.entityManager.createQuery(
        "select m "
            + "from Meeting m "
            + "where m.name=?1 "
            + "and m.interval.start=?2",
        Meeting.class)
        .setParameter(1, meetingName)
        .setParameter(2, start);
    return findSingle(query);
  }
}
