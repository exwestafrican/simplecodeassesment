package codetest.services;

import codetest.orgtree.Enterprise;
import codetest.orgtree.Organisation;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class EnterpriseService{

    private Collection<Organisation> fetchAllOrganization(Collection<Organisation> enterpriseOrganisation, Collection<Organisation> org) {
        if (org.isEmpty()) {
            return enterpriseOrganisation;
        }
        for (Organisation organisation : org) {
            enterpriseOrganisation.add(organisation);
            fetchAllOrganization(enterpriseOrganisation, organisation.getChildOrganisations());
        }
        return enterpriseOrganisation;
    }


    public Collection<Organisation> findAllOrganizationsInEnterprise(Enterprise enterprise){
        // make the organizations linear.
        return fetchAllOrganization(new ArrayList<>(Collections.emptyList()),enterprise.getOrganisations());

    }

    public boolean hasMultipleEngagements(int engagementCount){
        return engagementCount > 1;
    }



}
