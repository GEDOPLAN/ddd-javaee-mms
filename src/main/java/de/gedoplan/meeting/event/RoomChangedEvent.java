package de.gedoplan.meeting.event;

import de.gedoplan.meeting.domain.Meeting;

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
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
public class RoomChangedEvent {

  private Meeting meeting;

}
