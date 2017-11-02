package de.gedoplan.dummy.infrastructure;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.dummy.domain.Dummy;
import de.gedoplan.dummy.domain.DummyRepository;

@Repository
public class DummyJpaRepository extends JpaRepository<Long, Dummy> implements DummyRepository {

}
