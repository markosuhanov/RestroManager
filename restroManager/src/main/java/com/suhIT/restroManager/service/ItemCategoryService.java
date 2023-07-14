package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.ItemCategoryDTO;

import java.util.List;

public interface ItemCategoryService {

    ItemCategoryDTO createItemCategory(ItemCategoryDTO itemDTO);

    void validateUniqueItemCategoryName(String name);

    ItemCategoryDTO updateItemCategory(String name, ItemCategoryDTO itemDTO);

    List<ItemCategoryDTO> getAllItemCategories();

    List<ItemCategoryDTO> getAllActiveItemsCategories();
    ItemCategoryDTO getByName(String name);
    ItemCategoryDTO deactivateItemCategory(String name);

    ItemCategoryDTO activateItemCategory(String name);
}
