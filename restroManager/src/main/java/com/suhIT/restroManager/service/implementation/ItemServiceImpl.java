package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.model.ItemDTO;
import com.suhIT.restroManager.repository.ItemRepository;
import com.suhIT.restroManager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        return null;
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return null;
    }

    @Override
    public List<ItemDTO> getAllFoodItems() {
        return null;
    }

    @Override
    public List<ItemDTO> getAllDrinkItems() {
        return null;
    }

    @Override
    public List<ItemDTO> getAllPromotions() {
        return null;
    }

    @Override
    public List<ItemDTO> getItemByName() {
        return null;
    }

    @Override
    public void deactivateItem() {

    }

    @Override
    public void activateItem() {

    }
}
