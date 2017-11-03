package de.gedoplan.common.domain;

import de.gedoplan.baselibs.persistence.domain.DomainSingleValue;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SeatCapacity extends DomainSingleValue<Integer> {

  @NotNull
  @Min(1)
  @Column(name = "CAPACITY")
  private Integer value;
}
