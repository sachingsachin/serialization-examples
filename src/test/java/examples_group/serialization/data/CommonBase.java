package examples_group.serialization.data;

import java.util.UUID;

public abstract class CommonBase
{
    private volatile UUID id = UUID.randomUUID();
    private Long dbLockVersion = 1L;

    public UUID getId()
    {
        return id;
    }
    public Long getDbLockVersion()
    {
        return dbLockVersion;
    }
    public void setDbLockVersion(Long dbLockVersion)
    {
        this.dbLockVersion = dbLockVersion;
    }
}

