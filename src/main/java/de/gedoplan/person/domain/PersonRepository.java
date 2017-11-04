package de.gedoplan.person.domain;

import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.person.domain.attribute.EMail;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/**
 * (JPA) Repository for {@link Person}.
 *
 * @author dw
 */
@ApplicationScoped
@Transactional(value = TxType.MANDATORY)
public class PersonRepository extends JpaRepository<Long, Person> {

  public Person findByEMail(EMail eMail) {
    return findSingle(
        this.entityManager.createQuery(
            "select p from Person p where p.email=?1", Person.class)
            .setParameter(1, eMail));
  }
}
