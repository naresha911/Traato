package com.traato.nar.traato.entities;

import com.traato.nar.traato.EntitiesMap;

import java.util.List;

/**
 * Created by Naresh on 27-02-2017.
 */

public class CategoryEntity {
    private GsonCategory category;

    public void setcategory(GsonCategory category)
    {
        this.category = category;
    }
    public GsonCategory getGsonCategory()
    {
        return category;
    }

    public String toString()
    {
        return category.getName();
    }

    public List<CategoryEntity> getSubCategories()
    {
        AssociationItemList assocItemList = category.getAssociations();
        List<AssociationItem> subGsonCategories = assocItemList.getCategories();
        if(subGsonCategories.isEmpty())
            return null;

        List<CategoryEntity> subCategoryEntities = null;

        int count = subGsonCategories.size();
        for(int ii = 0; ii < count; ++ii)
        {
            long id = subGsonCategories.get(ii).getid();
            subCategoryEntities.add(EntitiesMap.getCategoryGivenIndex(id));
        }

        return subCategoryEntities;
    }

}

