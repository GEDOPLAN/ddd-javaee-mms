package de.gedoplan.dummy.domain;

import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/**
 * (JPA) Repository for {@link Dummy}.
 *
 * @author dw
 */
@ApplicationScoped
@Transactional(value = TxType.MANDATORY)
public class DummyRepository extends JpaRepository<Long, Dummy> {

}