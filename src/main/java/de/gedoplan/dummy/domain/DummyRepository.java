package de.gedoplan.dummy.domain;

import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class DummyRepository extends JpaRepository<Long, Dummy> {

}