package de.gedoplan.person.domain.attribute;

import de.gedoplan.baselibs.persistence.domain.DomainAttribute;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EMail extends DomainAttribute<String> {

  @NotNull
  @Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
  @Column(name = "EMAIL")
  private String value;
}
