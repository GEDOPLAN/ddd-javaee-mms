package de.gedoplan.person.infrastructure;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.person.domain.Person;
import de.gedoplan.person.domain.PersonRepository;

@Repository
public class PersonJpaRepository extends JpaRepository<Long, Person> implements PersonRepository {
}
