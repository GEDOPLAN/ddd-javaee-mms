package de.gedoplan.meeting.application;

import de.gedoplan.meeting.domain.Meeting;
import de.gedoplan.meeting.domain.MeetingRepository;
import de.gedoplan.meeting.domain.attribute.MeetingName;
import de.gedoplan.meeting.event.RoomChangedEvent;
import de.gedoplan.person.domain.attribute.EMail;
import de.gedoplan.person.domain.attribute.FirstName;
import de.gedoplan.person.domain.attribute.LastName;

import java.time.ZonedDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class ParticipantAppSvc {

  @Inject
  MeetingRepository meetingRepository;

  @Inject
  Log log;

  public void register(LastName lastName, FirstName firstName, EMail eMail, MeetingName meetingName, ZonedDateTime start) {

    Meeting meeting = this.meetingRepository.findByNameAndStart(meetingName, start);
    if (meeting == null) {
      throw new IllegalArgumentException("No such meeting");
    }

    meeting.registerParticipant(lastName, firstName, eMail);
  }

  void notifyAboutRoomChange(@Observes RoomChangedEvent roomChangedEvent) {
    Meeting meeting = roomChangedEvent.getMeeting();
    String text = String.format("Room change: %s at %s will take place in room %s", meeting.getName(), meeting.getInterval().getStart(), meeting.getRoom().getName());
    meeting.getParticipants().forEach(p -> this.log.info("mailto:" + p.getEmail() + ": " + text));
  }
}
