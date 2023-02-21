package codetest.services;

import codetest.orgtree.Engagement;
import codetest.orgtree.Enterprise;
import codetest.orgtree.Organisation;
import codetest.orgtree.Person;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class EnterpriseServiceTest {

    Person alice = new Person("Alice Arnold");
    Person bob = new Person("Bob Burns");

    Person aliyah = new Person("Aliyah Williams");
    Person sue = new Person("Sue Smith");

    Person james = new Person("James Jones");
    Person pierre = new Person("Pierre Martin");

    EnterpriseService service = new EnterpriseService();


    Enterprise sme = new Enterprise(
            List.of(
                    new Organisation("Executive",
                            asList(
                                    new Engagement("CEO", alice),
                                    new Engagement("CTO", bob)
                            ),
                            asList(
                                    new Organisation("Sales",
                                            asList(
                                                    new Engagement("Account Manager", james),
                                                    new Engagement("Sales Assistant", sue)
                                            ),
                                            asList(
                                                    new Organisation("Sales A",
                                                            asList(
                                                                    new Engagement("Software Engineer", aliyah),
                                                                    new Engagement("Operations", pierre),
                                                                    new Engagement("QA", sue)
                                                            ),
                                                            asList(
                                                                    new Organisation("Sales A1",
                                                                            asList(
                                                                                    new Engagement("Software Engineer", aliyah),
                                                                                    new Engagement("Operations", pierre),
                                                                                    new Engagement("QA", sue)
                                                                            ),
                                                                            emptyList()
                                                                    )
                                                            )
                                                    ),
                                                    new Organisation("Sales B",
                                                            asList(
                                                                    new Engagement("Software Engineer", aliyah),
                                                                    new Engagement("Operations", pierre),
                                                                    new Engagement("QA", sue)
                                                            ),
                                                            emptyList()
                                                    )
                                            )
                                    ),
                                    new Organisation("Engineering",
                                            asList(
                                                    new Engagement("Software Engineer", aliyah),
                                                    new Engagement("Operations", pierre),
                                                    new Engagement("QA", sue)
                                            ),
                                            asList(
                                                    new Organisation("Engineering A",
                                                            asList(
                                                                    new Engagement("Software Engineer", aliyah),
                                                                    new Engagement("Operations", pierre),
                                                                    new Engagement("QA", sue)
                                                            ),
                                                            emptyList()
                                                    )
                                            )
                                    )
                            )
                    )
            ));


    @Test
    public void allEnterpriseOrganisationIsSaved() {
        Collection<Organisation> store =  service.findAllOrganizationsInEnterprise(sme);
        assertSame(store.size(),7);
    }





}