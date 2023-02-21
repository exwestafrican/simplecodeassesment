package codetest.services;

import codetest.orgtree.Engagement;
import codetest.orgtree.Enterprise;
import codetest.orgtree.Organisation;
import codetest.orgtree.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindServiceTest {

    FindService findService;


    EnterpriseService enterpriseService;

    Person alice = new Person("Alice Arnold");
    Person bob = new Person("Bob Burns");
    Person james = new Person("James Jones");
    Person pierre = new Person("Pierre Martin");
    Person aliyah = new Person("Aliyah Williams");
    Person sue = new Person("Sue Smith");

    @BeforeEach
    public void setUp() {
        enterpriseService = new EnterpriseService();
        findService = new DefaultFindService(enterpriseService);

    }

    @Nested
    class FindPersonByName {


        @Nested
        class GivenAnEmptyEnterprise {

            Enterprise emptyEnterprise = new Enterprise(emptyList());



            @Test
            public void findsNoPeople() {
                assertTrue(findService.findPersonByName(emptyEnterprise, "any name").isEmpty());
            }
        }

        @Nested
        class GivenAPopulatedEnterprise {

            Enterprise sme = new Enterprise(
                    asList(
                            new Organisation("Executive",
                                    asList(
                                            new Engagement("CEO", alice),
                                            new Engagement("CTO", bob)
                                    ),
                                    asList(
                                            new Organisation("Sales",
                                                    asList (
                                                            new Engagement("Account Manager", james),
                                                            new Engagement("Sales Assistant", sue)
                                                    ),
                                                    emptyList()
                                            ),
                                            new Organisation("Engineering",
                                                    asList (
                                                            new Engagement("Software Engineer", aliyah),
                                                            new Engagement("Operations", pierre),
                                                            new Engagement("QA", sue)
                                                    ),
                                                    emptyList()
                                            )
                                    )
                            )
                    )
            );



            @Test
            public void exactMatchSuccess() {
                assertEquals(alice, findService.findPersonByName(sme, "Alice Arnold").get());
                assertEquals(sue, findService.findPersonByName(sme, "Sue Smith").get());
            }

            @Test
            public void caseInsensitiveMatchSuccess() {
                assertEquals(pierre, findService.findPersonByName(sme, "pierre martin").get());
            }

            @Test
            public void unknownMatchFailure() {
                assertTrue(findService.findPersonByName(sme, "Morticia Addams").isEmpty());
            }

            @Test
            public void partialMatchFailure() {
                assertTrue(findService.findPersonByName(sme, "alice").isEmpty());
            }
        }
    }


    @Nested
    class FindOrganisationsEngagingPerson{

        Person nobody = new Person("Nobody");

        Organisation productEngineering = new Organisation("Engineering",
                asList (
                        new Engagement("Software Engineer", aliyah),
                        new Engagement("Operations", alice),
                        new Engagement("QA", sue)
                ),
                asList(
                        new Organisation("Product Design",
                                asList (
                                        new Engagement("Account Manager", james),
                                        new Engagement("Sales Assistant", sue)
                                ),
                                emptyList()
                        )
                )
        );

        Organisation sales = new Organisation("Sales",
                asList (
                        new Engagement("Account Manager", james),
                        new Engagement("Sales Assistant", sue)
                ),
                emptyList()
        );

        Enterprise engagingSme = new Enterprise(
                asList(
                        new Organisation("Executive",
                                asList(
                                        new Engagement("CEO", alice),
                                        new Engagement("CTO", bob)
                                ),
                                asList(sales, productEngineering)
                        )
                )
        );
        @Test
        public void returnsExpectedNumberOfEngagingOrganisation() {
          Collection<Organisation> organisation = findService.findOrganisationsEngagingPerson(engagingSme, james);
          assertEquals(organisation.size(), 2);
        }


        @Test
        public void returnsAllEngagingOrganisation() {
            Collection<Organisation> organisation = findService.findOrganisationsEngagingPerson(engagingSme, james);
           Collection<String> organisationNames = organisation.stream().map(Organisation::getName).collect(Collectors.toList());
            assertTrue(organisationNames.containsAll(asList("Sales","Product Design" )));
        }


        @Test
        public void userWithNoEngagingOrganisationReturnsEmptyList() {
            Collection<Organisation> organisation = findService.findOrganisationsEngagingPerson(engagingSme, nobody);
            assertEquals(organisation.size(), 0);
        }
    }



    @Nested
    class FindPeopleWithMultipleEngagements{

        Enterprise sme = new Enterprise(
                asList(
                        new Organisation("Executive",
                                asList(
                                        new Engagement("CEO", alice),
                                        new Engagement("CTO", bob)
                                ),
                                asList(
                                        new Organisation("Sales",
                                                asList (
                                                        new Engagement("Account Manager", james),
                                                        new Engagement("Sales Assistant", sue)
                                                ),
                                                emptyList()
                                        ),
                                        new Organisation("Engineering",
                                                asList (
                                                        new Engagement("Software Engineer", aliyah),
                                                        new Engagement("Operations", pierre),
                                                        new Engagement("QA", sue)
                                                ),
                                                emptyList()
                                        )
                                )
                        )
                )
        );


        @Test
        public void returnsAllUsersWithMultipleEngagements() {
            Collection<Person> people = findService.findPeopleWithMultipleEngagements(sme);
            assertEquals(people.size(), 1);
            assertTrue(people.contains(sue));
        }
    }


}