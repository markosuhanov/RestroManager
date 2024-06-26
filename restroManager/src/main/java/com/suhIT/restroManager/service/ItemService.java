package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.UserDTO;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    ItemDTO createItem(ItemDTO itemDTO, String itemType);
    ItemDTO updateItem(Long id, ItemDTO itemDTO);
    List<ItemDTO> getAllItems();

    List<ItemDTO> getAllActiveItems();

    List<ItemDTO> getAllFoodItems();

    List<ItemDTO> getAllActiveFoodItems();

    List<ItemDTO> getAllDrinkItems();

    List<ItemDTO> getAllActiveDrinkItems();
    List<ItemDTO> getAllPromotions();
    ItemDTO getItemByName(String itemName);


    ItemDTO deactivateItem(Long itemId);


    ItemDTO activateItem(Long itemId);

    ItemDTO getItemById(Long itemId);
}
