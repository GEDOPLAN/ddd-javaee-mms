package de.gedoplan.meeting.event;

import de.gedoplan.meeting.domain.Meeting;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Domain event for room changes of a meeting.
 * 
 * @author dw
 */
@Embeddable
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
public class RoomChangedEvent {

  private Meeting meeting;

}
