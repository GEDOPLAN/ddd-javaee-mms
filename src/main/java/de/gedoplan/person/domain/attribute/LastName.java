package de.gedoplan.person.domain.attribute;

import de.gedoplan.baselibs.persistence.domain.DomainSingleValue;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Domain value/attribute for a last name.
 *
 * @author dw
 */
@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class LastName extends DomainSingleValue<String> {

  @NotNull
  @Size(min = 1)
  @Column(name = "FIRST_NAME")
  private String value;
}
