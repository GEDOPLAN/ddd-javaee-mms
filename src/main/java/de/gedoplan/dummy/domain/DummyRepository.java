package de.gedoplan.dummy.domain;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;

@Repository
public class DummyRepository extends JpaRepository<Long, Dummy> {

}