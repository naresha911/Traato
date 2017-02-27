package com.traato.nar.traato.entities;

/**
 * Created by Naresh on 27-02-2017.
 */

public class DrawItemCategory {
    private Catgry category;
    public void setcategory(Catgry category)
    {
        this.category = category;
    }
    public String toString()
    {
        return category.getString();
    }

}

class Catgry
{
    private int id;
    private String id_parent;
    private String level_depth;

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

    public void setAssociations(AssociationItemList associations)
    {
        this.associations = associations;
    }

    public String getString()
    {
        String str = String.format("Id : %d\n IdParent:%s\n LevelParent:%s Association:%s",
                id,id_parent,level_depth,associations);

        System.out.println("ID:" + id);
        return str;
    }
}
