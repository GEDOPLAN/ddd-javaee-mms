package de.gedoplan.dummy.persistence;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.dummy.entity.Dummy;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class DummyRepository extends SingleIdEntityRepository<Long, Dummy> {

}
