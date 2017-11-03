package de.gedoplan.meeting.domain;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;

@Repository
public class MeetingRepository extends JpaRepository<Long, Meeting> {
}
