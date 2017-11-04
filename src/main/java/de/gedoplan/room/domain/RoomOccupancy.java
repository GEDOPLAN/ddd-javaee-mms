package de.gedoplan.room.domain;

import de.gedoplan.baselibs.persistence.domain.DomainValue;
import de.gedoplan.common.domain.attribute.ZonedInterval;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RoomOccupancy extends DomainValue {
  @NotNull
  private ZonedInterval interval;

  @NotNull
  @Size(min = 1)
  private String description;
}
