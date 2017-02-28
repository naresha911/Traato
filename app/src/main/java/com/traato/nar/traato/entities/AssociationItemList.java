package com.traato.nar.traato.entities;

import java.util.List;

/**
 * Created by nar on 2/27/2017.
 */

public class AssociationItemList {

    private List<AssociationItem> categories;

    public void setCategories(List<AssociationItem> categories)
    {
        this.categories = categories;
    }
    public List<AssociationItem> getCategories() { return categories; };

    public String toString()
    {
        String str = "";
        for(int ii = 0; ii < categories.size(); ++ii)
        {
            String str2 = String.format("CatId : %d\n", categories.get(ii).getid());
            str += str2;
        }
        return str;
    }
}
