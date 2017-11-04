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

/**
 * Domain value for room occupancies.
 *
 * @author dw
 */
@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RoomOccupancy extends DomainValue {
  @NotNull
  private ZonedInterval interval;

  // In a full blown model, this would be a specialized attribute type instead of String.
  @NotNull
  @Size(min = 1)
  private String description;
}
