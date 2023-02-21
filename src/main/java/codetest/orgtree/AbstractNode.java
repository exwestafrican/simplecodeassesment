package codetest.orgtree;

import java.util.UUID;

abstract class AbstractNode implements Node {

    private final UUID uuid = UUID.randomUUID();

    @Override
    public UUID getNodeUid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractNode)) return false;

        AbstractNode that = (AbstractNode) o;

        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

}
