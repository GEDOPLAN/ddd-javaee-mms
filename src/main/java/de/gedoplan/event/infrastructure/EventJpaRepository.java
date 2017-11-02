package de.gedoplan.event.infrastructure;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.event.domain.Event;
import de.gedoplan.event.domain.EventRepository;

@Repository
public class EventJpaRepository extends JpaRepository<Long, Event> implements EventRepository {
}
