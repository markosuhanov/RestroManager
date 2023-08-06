package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.ItemCategoryDTO;
import com.suhIT.restroManager.exception.ItemCategoryNotFound;
import com.suhIT.restroManager.model.ItemCategory;
import com.suhIT.restroManager.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ItemCategoryMapper implements Mapper<ItemCategory, ItemCategoryDTO> {

    private final ItemCategoryRepository itemCategoryRepository;

    @Autowired
    public ItemCategoryMapper(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }
    @Override
    public ItemCategory toEntity(ItemCategoryDTO itemCategoryDTO) {
        ItemCategory itemCategory = itemCategoryRepository.findByName(itemCategoryDTO.getName()).orElseThrow(
                () -> new ItemCategoryNotFound(HttpStatus.NOT_FOUND,
                        "Item category with name " + itemCategoryDTO.getName() + " doesn't exist!"
                ));
        itemCategory.setName(itemCategory.getName());
        itemCategory.setActive(itemCategory.isActive());
        return itemCategory;

    }

    @Override
    public ItemCategoryDTO toDTO(ItemCategory itemCategory) {
        return ItemCategoryDTO.builder()
                .id(itemCategory.getId())
                .name(itemCategory.getName())
                .active(itemCategory.isActive())
                .build();
    }
}

