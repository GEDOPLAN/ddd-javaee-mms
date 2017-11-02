package de.gedoplan.common.infrastructure;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, String> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  @Override
  public String convertToDatabaseColumn(ZonedDateTime attribute) {
    return attribute == null ? null : attribute.format(FORMATTER);
  }

  @Override
  public ZonedDateTime convertToEntityAttribute(String dbData) {
    return dbData == null ? null : ZonedDateTime.parse(dbData, FORMATTER);
  }

}
