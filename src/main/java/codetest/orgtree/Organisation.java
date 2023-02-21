package codetest.orgtree;

import java.util.Collection;

/** An organization contains zero or more members, and zero or more sub-organisations */
public class Organisation extends AbstractNode {
    private final String name;
    private final Collection<Engagement> members;
    private final Collection<Organisation> childOrganisations;

    public Organisation(String name, Collection<Engagement> members, Collection<Organisation> childOrganisations) {
        this.name = name;
        this.members = members;
        this.childOrganisations = childOrganisations;
    }

    public String getName() {
        return name;
    }

    public Collection<Engagement> getMembers() {
        return members;
    }

    public Collection<Organisation> getChildOrganisations() {
        return childOrganisations;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "name='" + name + '\'' +
                ", members=" + members +
                ", childOrganisations=" + childOrganisations +
                '}';
    }
}
