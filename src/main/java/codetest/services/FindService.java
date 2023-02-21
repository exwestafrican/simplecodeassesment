package codetest.services;

import codetest.orgtree.Enterprise;
import codetest.orgtree.Organisation;
import codetest.orgtree.Person;

import java.util.Collection;
import java.util.Optional;

public interface FindService {

    /**
     *  Find a person node by name
     *
     * @param name the name of the person
     * @return a person with this name, or Empty if not present in the organisation
     */
    Optional<Person> findPersonByName(Enterprise enterprise, String name);

    /** List all organisations that person is directly engaged in
     *
     * @return a collection of organisations, which may be empty
     */
    Collection<Organisation> findOrganisationsEngagingPerson(Enterprise enterprise, Person person);

    /** List all people who have more than one engagement in this enterprise
     *
     * @return a collection of multiple-engaged people, which may be empty
     */
    Collection<Person> findPeopleWithMultipleEngagements(Enterprise enterprise);
}
