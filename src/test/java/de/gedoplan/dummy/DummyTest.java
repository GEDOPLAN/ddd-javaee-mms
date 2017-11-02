package de.gedoplan.dummy;

import de.gedoplan.TestBase;
import de.gedoplan.dummy.domain.Dummy;
import de.gedoplan.dummy.domain.DummyRepository;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DummyTest extends TestBase {
  @Inject
  DummyRepository dummyRepository;

  @Inject
  Log log;

  @Test
  public void test_00_clearData() {
    this.log.info("----- test_00_clearData -----");

    this.dummyRepository.removeAll();
  }

  @Test
  public void test_01_insertData() throws Exception {
    this.log.info("----- test_01_insertData -----");

    for (int i = 1; i <= 10; ++i) {
      Dummy dummy = new Dummy(String.format("Dummy %03d", i));
      this.dummyRepository.persist(dummy);

      this.log.debug("Persist: " + dummy);
    }
  }

  @Test
  public void test_02_findAll() {
    this.log.debug("----- test_02_findAll -----");

    this.dummyRepository
        .findAll()
        .stream()
        .map(x -> "Found: " + x)
        .forEach(this.log::debug);
  }

  @Test
  @Ignore
  public void test_03_instantiateForEver() throws Exception {
    for (int i = 1; true; ++i) {
      Dummy dummy = new Dummy(String.format("Dummy %03d", i));

      this.log.debug("Instantiated: " + dummy);
    }
  }
}
