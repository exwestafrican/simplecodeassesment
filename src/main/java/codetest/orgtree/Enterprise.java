package codetest.orgtree;

import java.util.Collection;

/** The root node - represents an enterprise / business / endeavour of some kind*/
public class Enterprise extends AbstractNode {

    private Collection<Organisation> organisations;

    public Enterprise(Collection<Organisation> organisations) {
        this.organisations = organisations;
    }

    public Collection<Organisation> getOrganisations() {
        return organisations;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "organisationList=" + organisations +
                '}';
    }
}
