package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.ItemCategoryDTO;
import com.suhIT.restroManager.exception.ItemCategoryNotFound;
import com.suhIT.restroManager.exception.ItemCategoryWithSameNameAlreadyExists;
import com.suhIT.restroManager.exception.NoSuchElementException;
import com.suhIT.restroManager.mapper.ItemCategoryMapper;
import com.suhIT.restroManager.model.ItemCategory;
import com.suhIT.restroManager.repository.ItemCategoryRepository;
import com.suhIT.restroManager.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemCategoryMapper itemCategoryMapper;

    @Autowired
    public ItemCategoryServiceImpl(ItemCategoryRepository itemCategoryRepository,
                                   ItemCategoryMapper itemCategoryMapper) {
        this.itemCategoryRepository = itemCategoryRepository;
        this.itemCategoryMapper = itemCategoryMapper;
    }

    @Override
    public ItemCategoryDTO createItemCategory(ItemCategoryDTO itemCategoryDTO) {
        validateUniqueItemCategoryName(itemCategoryDTO.getName());
        ItemCategory itemCategory = ItemCategory.builder().name(itemCategoryDTO.getName())
                .active(itemCategoryDTO.isActive()).build();
        ItemCategory createdItemCat = itemCategoryRepository.save(itemCategory);
        return itemCategoryMapper.toDTO(createdItemCat);
    }

    @Override
    public ItemCategoryDTO updateItemCategory(String name, ItemCategoryDTO itemDTO) {
        //TODO: IMPLEMENT updateItemCategory !!!
        return null;
    }
    @Override
    public void validateUniqueItemCategoryName(String name) {
        Optional<ItemCategory> existingItemCategory = itemCategoryRepository.findByName(name);

        if (existingItemCategory.isPresent()) {
            throw new ItemCategoryWithSameNameAlreadyExists(HttpStatus.BAD_REQUEST,
                    "Item category with name: '" + name + "' already exists!"
            );
        }
    }



    @Override
    public List<ItemCategoryDTO> getAllItemCategories() {
        List<ItemCategory> itemCategories = itemCategoryRepository.findAll();
        if (itemCategories.isEmpty()) {
            throw new NoSuchElementException(HttpStatus.NOT_FOUND, "Item categories doesn't exist!");
        }
        return itemCategories.stream().map(itemCategoryMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public List<ItemCategoryDTO> getAllActiveItemsCategories() {
        List<ItemCategory> itemCategories = itemCategoryRepository.findAllByActiveIsTrue().orElseThrow(
                () -> new ItemCategoryNotFound(HttpStatus.NOT_FOUND, "There is no active item categories!"));
        return itemCategories.stream().map(itemCategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ItemCategoryDTO getByName(String name) {
        ItemCategory itemCategory = itemCategoryRepository.findByName(name).orElseThrow(
                () -> new ItemCategoryNotFound(HttpStatus.NOT_FOUND,
                        "Item category with name + " + name + " doesn't exist!"
                ));
        return itemCategoryMapper.toDTO(itemCategory);
    }


    @Override
    public ItemCategoryDTO deactivateItemCategory(String name) {
        ItemCategory itemCategory = itemCategoryRepository.findByName(name).orElseThrow(
                () -> new ItemCategoryNotFound(HttpStatus.NOT_FOUND,
                        "Item category with name " + name + " not found!"
                ));
        itemCategory.setActive(false);
        itemCategoryRepository.save(itemCategory);
        return itemCategoryMapper.toDTO(itemCategory);
    }

    @Override
    public ItemCategoryDTO activateItemCategory(String name) {
        ItemCategory itemCategory = itemCategoryRepository.findByName(name).orElseThrow(
                () -> new ItemCategoryNotFound(HttpStatus.NOT_FOUND,
                        "Item category with name " + name + " not found!"
                ));
        itemCategory.setActive(true);
        itemCategoryRepository.save(itemCategory);
        return itemCategoryMapper.toDTO(itemCategory);
    }
}
