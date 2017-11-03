package de.gedoplan.person.domain;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.person.domain.attribute.EMail;

@Repository
public class PersonRepository extends JpaRepository<Long, Person> {

  public Person findByEMail(EMail eMail) {
    return findSingleByProperty(Person_.email, eMail);
  }
}
