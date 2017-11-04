This package contains an aggregate of two entities.
Room is aggregate root and is accompanied by a repository.
RoomOccupancy is a non-root entity controlled by Room. Well - it could have been. But RoomOccupancy does not need an identity,
so it is just a domain value kept in an @ElementCollection within Room.

In a real life application this package could become a separate depoloyment unit.  