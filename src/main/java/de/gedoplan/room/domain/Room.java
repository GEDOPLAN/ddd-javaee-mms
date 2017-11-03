package de.gedoplan.room.domain;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;
import de.gedoplan.common.domain.Capacity;
import de.gedoplan.common.domain.ZonedInterval;
import de.gedoplan.room.domain.attribute.RoomName;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends GeneratedLongIdEntity {

  @NotNull
  @Valid
  private RoomName name;

  @NotNull
  @Valid
  private Capacity capacity;

  @Setter(AccessLevel.NONE)
  @ElementCollection(fetch = FetchType.EAGER)
  private Set<RoomOccupancy> occupancies;

  public Set<RoomOccupancy> getOccupancies() {
    return Collections.unmodifiableSet(this.occupancies);
  }

  public void addOccupancy(ZonedInterval interval, String description) {
    this.occupancies.add(new RoomOccupancy(interval, description));
  }

  public void removeOccupancy(ZonedInterval interval) {
    this.occupancies.removeIf(r -> r.getInterval().equals(interval));
  }

  public Room(RoomName name, Capacity capacity) {
    this.name = name;
    this.capacity = capacity;
    this.occupancies = new HashSet<>();
  }

}
