package de.gedoplan.person;

import de.gedoplan.baselibs.persistence.domain.GeneratedLongIdEntity;
import de.gedoplan.person.attribute.EMail;
import de.gedoplan.person.attribute.FirstName;
import de.gedoplan.person.attribute.LastName;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Access(AccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Person extends GeneratedLongIdEntity {

  @NotNull
  @Valid
  private LastName lastName;

  @Valid
  private FirstName firstName;

  @Valid
  private EMail email;
}
