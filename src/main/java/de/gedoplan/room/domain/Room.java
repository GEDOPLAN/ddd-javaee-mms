package de.gedoplan.room.domain;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;
import de.gedoplan.room.domain.attribute.RoomName;

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

  @Setter(AccessLevel.NONE)
  @ElementCollection(fetch = FetchType.EAGER)
  private Set<RoomOccupancy> roomOccupancies;

  public Room(RoomName name) {
    this.name = name;
    this.roomOccupancies = new HashSet<>();
  }

}
