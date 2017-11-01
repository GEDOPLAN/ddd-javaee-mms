package de.gedoplan.person.persistence;

import de.gedoplan.baselibs.persistence.domain.Repository;
import de.gedoplan.baselibs.persistence.domain.impl.JpaRepository;
import de.gedoplan.person.Person;

@Repository
public class PersonJpaRepository extends JpaRepository<Long, Person> implements PersonRepository {
}
