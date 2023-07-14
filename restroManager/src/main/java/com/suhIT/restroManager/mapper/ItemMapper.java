package com.suhIT.restroManager.mapper;

import com.suhIT.restroManager.dto.DrinkItemDTO;
import com.suhIT.restroManager.dto.FoodItemDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.exception.ItemCategoryNotFound;
import com.suhIT.restroManager.exception.ItemNotFoundException;
import com.suhIT.restroManager.model.DrinkItem;
import com.suhIT.restroManager.model.FoodItem;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.ItemCategory;
import com.suhIT.restroManager.repository.DrinkItemRepository;
import com.suhIT.restroManager.repository.FoodItemRepository;
import com.suhIT.restroManager.repository.ItemCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements Mapper<Item, ItemDTO> {

    private final FoodItemRepository foodItemRepository;
    private final DrinkItemRepository drinkItemRepository;

    private final ItemCategoryRepository itemCategoryRepository;

    @Autowired
    public ItemMapper(FoodItemRepository foodItemRepository, DrinkItemRepository drinkItemRepository, ItemCategoryRepository itemCategoryRepository) {
        this.drinkItemRepository = drinkItemRepository;
        this.foodItemRepository = foodItemRepository;
        this.itemCategoryRepository = itemCategoryRepository;
    }
    @Override
    public Item toEntity(ItemDTO itemDTO) {

        Item item;
        if (itemDTO instanceof FoodItemDTO) {
            item = foodItemRepository.findById(itemDTO.getId()).orElseThrow(
                    () -> new ItemNotFoundException(HttpStatus.NOT_FOUND,
                            "Item with id + " + itemDTO.getId() + " not found!"
                    ));


            FoodItemDTO foodItemDTO = (FoodItemDTO) itemDTO;
            ((FoodItem) item).setAllergens(foodItemDTO.getAllergens());
            ((FoodItem) item).setPrepTime(foodItemDTO.getPrepTime());
        } else if (itemDTO instanceof DrinkItemDTO) {
            item = drinkItemRepository.findById(itemDTO.getId()).orElseThrow(
                    () -> new ItemNotFoundException(HttpStatus.NOT_FOUND,
                            "Item with id + " + itemDTO.getId() + " not found!"
                    ));
            DrinkItemDTO drinkItemDTO = (DrinkItemDTO) itemDTO;
            ((DrinkItem) item).setAllergens(drinkItemDTO.getAllergens());
            ((DrinkItem) item).setPrepTime(drinkItemDTO.getPrepTime());
        } //TODO: dodati else if za promotion
        else {
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND, "Something went wrong!");
        }
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setCost(itemDTO.getCost());
        item.setActive(itemDTO.isActive());
        item.setImgPath(item.getImgPath());
        ItemCategory itemCategory = itemCategoryRepository.findByName(itemDTO.getItemCategoryName()).orElseThrow(
                () -> new ItemCategoryNotFound(HttpStatus.NOT_FOUND,
                        "Item category with name: " + itemDTO.getName() + " doesn't exist!"
                ));
        item.setItemCategory(itemCategory);
        return item;
    }

//    @Override
//    public ItemDTO toDTO(Item item) {
//        return ItemDTO.builder()
//                .id(item.getId())
//                .name(item.getName())
//                .description(item.getDescription())
//                .price(item.getPrice())
//                .cost(item.getCost())
//                .active(item.isActive())
//                .imgPath(item.getImgPath())
//                .itemCategoryId(item.getItemCategory().getId())
//                .build();
//    }

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setCost(item.getCost());
        itemDTO.setImgPath(item.getImgPath());
        itemDTO.setItemCategoryName(item.getItemCategory().getName());
        itemDTO.setActive(item.isActive());
        if (item instanceof FoodItem) {
            FoodItem foodItem = (FoodItem) item;
            FoodItemDTO foodItemDTO = new FoodItemDTO();
            BeanUtils.copyProperties(itemDTO, foodItemDTO);
            foodItemDTO.setAllergens(foodItem.getAllergens());
            foodItemDTO.setPrepTime(foodItem.getPrepTime());
            return foodItemDTO;
        } else if (item instanceof DrinkItem) {
            DrinkItem drinkItem = (DrinkItem) item;
            DrinkItemDTO drinkItemDTO = new DrinkItemDTO();
            BeanUtils.copyProperties(itemDTO, drinkItemDTO);
            drinkItemDTO.setAllergens(drinkItem.getAllergens());
            drinkItemDTO.setPrepTime(drinkItem.getPrepTime());
            return drinkItemDTO;
            //TODO: Dodati else if za promotion
        } else {
            return itemDTO;
        }
    }
}

