package de.gedoplan.common;

import de.gedoplan.TestBase;
import de.gedoplan.common.service.ApplicationVersionService;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.junit.Test;

public class ApplicationVersionServiceTest extends TestBase {

  @Inject
  ApplicationVersionService applicationVersionService;

  @Inject
  Log log;

  @Test
  public void testGetGav() throws Exception {
    this.log.info(this.applicationVersionService.getGav());
  }
}
