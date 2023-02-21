package codetest.services;

import codetest.orgtree.Engagement;
import codetest.orgtree.Enterprise;
import codetest.orgtree.Organisation;
import codetest.orgtree.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultFindService implements FindService{


    EnterpriseService enterpriseService;


    public DefaultFindService(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @Override
    public Optional<Person> findPersonByName(Enterprise enterprise, String name) {
        Collection<Organisation> organisations = enterpriseService.findAllOrganizationsInEnterprise(enterprise);
        Collection<Engagement> engagements = organisations.stream().map(Organisation::getMembers)
                .flatMap(Collection::stream).collect(Collectors.toList());
        Optional<Engagement> personEngagement = engagements.stream().filter(engagement -> engagement.getPerson().getName().equalsIgnoreCase(name)).findFirst();
        return personEngagement.map(Engagement::getPerson);
    }

    @Override
    public Collection<Organisation> findOrganisationsEngagingPerson(Enterprise enterprise, Person person) {
        Collection<Organisation> organisations = enterpriseService.findAllOrganizationsInEnterprise(enterprise);
        Collection<Organisation> organisationEngagingPerson =  new ArrayList<>(Collections.emptyList());
        for (Organisation organisation: organisations){
            Collection<Engagement> engagements = organisation.getMembers();
            Collection<Person> people = engagements.stream().map(Engagement::getPerson).collect(Collectors.toList());
            if (people.contains(person)){
                organisationEngagingPerson.add(organisation);
            }
        }
        return organisationEngagingPerson;
    }


    @Override
    public Collection<Person> findPeopleWithMultipleEngagements(Enterprise enterprise) {
        Collection<Organisation> organisations = enterpriseService.findAllOrganizationsInEnterprise(enterprise);
        Collection<Engagement> engagements = organisations.stream().map(Organisation::getMembers)
                    .flatMap(Collection::stream).collect(Collectors.toList());
        Map<Person,Integer> personEngagementCache =  new HashMap<>();

        for (Engagement engagement: engagements){
            Person person = engagement.getPerson();
            Integer engagementCount = personEngagementCache.getOrDefault(person, 0);
            engagementCount = engagementCount + 1;
            personEngagementCache.put(person ,engagementCount);
        }

        return  personEngagementCache.keySet().stream()
                .filter( person -> enterpriseService.hasMultipleEngagements(personEngagementCache.get(person)))
                .collect(Collectors.toList());

    }
}
