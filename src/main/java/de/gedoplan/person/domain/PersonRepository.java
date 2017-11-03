package de.gedoplan.person.domain;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;

@Repository
public class PersonRepository extends JpaRepository<Long, Person> {
}
