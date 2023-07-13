package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.ItemDTO;

import java.util.List;

public interface ItemService {

    ItemDTO createItem(ItemDTO itemDTO);
    ItemDTO updateItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();

    List<ItemDTO> getAllFoodItems();

    List<ItemDTO> getAllDrinkItems();

    List<ItemDTO> getAllPromotions();
    List<ItemDTO> getItemByName();
    void deactivateItem();
    void activateItem();

}
