package de.gedoplan.dummy.persistence;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.dummy.entity.Dummy;

@Repository
public class DummyJpaRepository extends JpaRepository<Long, Dummy> implements DummyRepository {

}
