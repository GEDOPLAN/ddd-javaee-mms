package de.gedoplan.meeting.infrastructure;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.meeting.domain.Meeting;
import de.gedoplan.meeting.domain.MeetingRepository;

@Repository
public class MeetingJpaRepository extends JpaRepository<Long, Meeting> implements MeetingRepository {
}
