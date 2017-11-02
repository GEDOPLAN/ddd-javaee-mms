package de.gedoplan.room.infrastructure;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.room.domain.Room;
import de.gedoplan.room.domain.RoomRepository;

@Repository
public class RoomJpaRepository extends JpaRepository<Long, Room> implements RoomRepository {
}
