package de.gedoplan.person.domain.attribute;

import de.gedoplan.baselibs.persistence.domain.DomainAttribute;

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

@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FirstName extends DomainAttribute<String> {

  @NotNull
  @Size(min = 1)
  @Column(name = "LAST_NAME")
  private String value;
}
