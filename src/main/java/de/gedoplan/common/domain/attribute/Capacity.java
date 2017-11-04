package de.gedoplan.common.domain.attribute;

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

/**
 * Domain value/attribute for a capacity.
 * 
 * @author dw
 */
@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class Capacity extends DomainSingleValue<Integer> {

  @NotNull
  @Min(1)
  @Column(name = "CAPACITY")
  private Integer value;
}
