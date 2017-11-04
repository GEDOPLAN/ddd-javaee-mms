package de.gedoplan.meeting.domain.attribute;

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
 * Domain value/attribute for a meeting name.
 *
 * @author dw
 */
@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class MeetingName extends DomainSingleValue<String> {

  @NotNull
  @Size(min = 1)
  @Column(name = "NAME")
  private String value;
}
