package de.gedoplan.meeting.infrastructure;

import de.gedoplan.meeting.domain.Meeting;
import de.gedoplan.meeting.event.RoomChangedEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;

import org.apache.commons.logging.Log;

@ApplicationScoped
public class ParticipantMailAdapter {

  @Inject
  Log log;

  void notifyAboutRoomChange(@ObservesAsync RoomChangedEvent roomChangedEvent) {
    Meeting meeting = roomChangedEvent.getMeeting();
    String text = String.format("Room change: %s at %s will take place in room %s", meeting.getName(), meeting.getInterval().getStart(), meeting.getRoom().getName());
    meeting.getParticipants().forEach(p -> this.log.info("mailto:" + p.getEmail() + ": " + text));
  }
}
