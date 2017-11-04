package de.gedoplan.common.domain.attribute;

import de.gedoplan.baselibs.persistence.domain.DomainValue;

import java.time.ZonedDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Domain value for a timestamp interval.
 *
 * @author dw
 */
@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
public class ZonedInterval extends DomainValue {
  @NotNull
  private ZonedDateTime start;

  @NotNull
  private ZonedDateTime end;

  @AssertTrue(message = "end must not be before start")
  private boolean isEndNotBeforeStart() {
    return this.start == null || this.end == null || !this.end.isBefore(this.start);
  }
}
