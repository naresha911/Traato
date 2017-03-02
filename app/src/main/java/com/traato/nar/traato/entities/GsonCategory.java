package com.traato.nar.traato.entities;

import java.util.List;

/**
 * Created by nar on 2/28/2017.
 */

public class GsonCategory
{
    private long id;
    private String id_parent;
    private String level_depth;
    private String name;

    private AssociationItemList associations;

    public void setId(int id)
    {
        this.id = id;
    }

    public void setIdParent(String id_parent)
    {
        this.id_parent = id_parent;
    }

    public void setLevelParent(String level_depth)
    {
        this.level_depth = level_depth;
    }

    public void setAssociations(AssociationItemList associations) { this.associations = associations; }

    public AssociationItemList getAssociations() { return associations; }

    public void setName(String name){ this.name = name; }
    public String getName() { return name; }

    public String toString()
    {
        return name;
    }
}
