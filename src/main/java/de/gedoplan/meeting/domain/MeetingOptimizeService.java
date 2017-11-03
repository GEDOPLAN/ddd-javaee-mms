package de.gedoplan.meeting.domain;

import de.gedoplan.common.domain.EventService;
import de.gedoplan.meeting.domain.attribute.MeetingName;
import de.gedoplan.meeting.event.RoomChangedEvent;
import de.gedoplan.room.domain.Room;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MeetingOptimizeService {

  @Inject
  MeetingRepository meetingRepository;

  @Inject
  EventService eventService;

  public void optimizeRoomAssignments() {
    ZoneId ZONE_ID = ZoneId.of("Europe/Berlin");
    Meeting dddMitJavaEe = this.meetingRepository.findByNameAndStart(MeetingName.of("DDD mit Java EE"), ZonedDateTime.of(2017, 11, 7, 15, 0, 0, 0, ZONE_ID));
    Meeting fortran = this.meetingRepository.findByNameAndStart(MeetingName.of("FORTRAN in der Historie"), ZonedDateTime.of(2017, 11, 7, 15, 0, 0, 0, ZONE_ID));

    Room tmpRoom = dddMitJavaEe.getRoom();
    dddMitJavaEe.changeRoom(fortran.getRoom());
    fortran.changeRoom(tmpRoom);

    this.eventService.fire(RoomChangedEvent.of(dddMitJavaEe));
  }
}
