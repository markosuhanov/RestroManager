package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.DrinkItemDTO;
import com.suhIT.restroManager.dto.FoodItemDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.exception.*;
import com.suhIT.restroManager.mapper.ItemMapper;
import com.suhIT.restroManager.model.DrinkItem;
import com.suhIT.restroManager.model.FoodItem;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.model.ItemCategory;
import com.suhIT.restroManager.repository.DrinkItemRepository;
import com.suhIT.restroManager.repository.FoodItemRepository;
import com.suhIT.restroManager.repository.ItemCategoryRepository;
import com.suhIT.restroManager.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemServiceImpl implements ItemService {

    private final FoodItemRepository foodItemRepository;
    private final DrinkItemRepository drinkItemRepository;

    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper, FoodItemRepository foodItemRepository,
                           DrinkItemRepository drinkItemRepository, ItemCategoryRepository itemCategoryRepository) {

        this.itemMapper = itemMapper;
        this.foodItemRepository = foodItemRepository;
        this.drinkItemRepository = drinkItemRepository;
        this.itemCategoryRepository = itemCategoryRepository;
    }


    @Override
    public ItemDTO createItem(ItemDTO itemDTO, String itemType) {
        validateUniqueItemName(itemDTO.getName());

        //check for valid item category id
        if (itemCategoryRepository.findByName(itemDTO.getItemCategoryName()).isEmpty()) {
            throw new ItemCategoryNotFound(HttpStatus.BAD_REQUEST,
                    "Item category with name: " + itemDTO.getName() + " doesn't exist!"
            );
        }

        Item item;
        if (itemType.equals("foodItem")) {
            item = new FoodItem();
            FoodItemDTO foodItemDTO = new FoodItemDTO();
            BeanUtils.copyProperties(itemDTO, foodItemDTO);
            ((FoodItem) item).setAllergens(foodItemDTO.getAllergens());
            ((FoodItem) item).setPrepTime(foodItemDTO.getPrepTime());
        } else if (itemType.equals("drinkItem")) {
            item = new DrinkItem();
            DrinkItemDTO drinkItemDTO = new DrinkItemDTO();
            BeanUtils.copyProperties(itemDTO, drinkItemDTO);

            ((DrinkItem) item).setAllergens(drinkItemDTO.getAllergens());
            ((DrinkItem) item).setPrepTime(drinkItemDTO.getPrepTime());
        } else {
            throw new BadRequest(HttpStatus.BAD_REQUEST, "Something went wrong!");
        }
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setCost(itemDTO.getCost());
        item.setActive(itemDTO.isActive());
        item.setImgPath(itemDTO.getImgPath());
        ItemCategory itemCategory = itemCategoryRepository.findByName(itemDTO.getItemCategoryName()).orElseThrow(
                () -> new ItemCategoryNotFound(HttpStatus.BAD_REQUEST,
                        "Item category with name " + itemDTO.getName() + " not found!"
                ));
        item.setItemCategory(itemCategory);

        Item createdItem;
        if (item instanceof FoodItem) {
            createdItem = foodItemRepository.save((FoodItem) item);
        } else if (item instanceof DrinkItem) {
            createdItem = drinkItemRepository.save((DrinkItem) item);
        } else {
            throw new BadRequest(HttpStatus.BAD_REQUEST, "Something went wrong!");
        }
        return itemMapper.toDTO(createdItem);

    }


    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) {
        //TODO: IMPLEMENT updateItem !!!
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        List<DrinkItem> drinkItems = drinkItemRepository.findAll();
        if (foodItems.isEmpty() && drinkItems.isEmpty()) {
            throw new NoSuchElementException(HttpStatus.NOT_FOUND, "Items doesn't exists!");
        }

        List<Item> concatenatedItems = Stream.concat(foodItems.stream(), drinkItems.stream())
                .collect(Collectors.toList());
        return concatenatedItems.stream().map(itemMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<ItemDTO> getAllActiveItems() {
        List<FoodItem> foodItems = foodItemRepository.findAllByActiveTrue().orElse(new ArrayList<>());
        List<DrinkItem> drinkItems = drinkItemRepository.findAllByActiveTrue().orElse(new ArrayList<>());

        if (foodItems.size() == 0 && drinkItems.size() == 0) {
            throw new NoSuchElementException(HttpStatus.NOT_FOUND, "Items doesn't exists!");
        }
        List<Item> concatenatedItems = Stream.concat(foodItems.stream(), drinkItems.stream())
                .collect(Collectors.toList());
        return concatenatedItems.stream().map(itemMapper::toDTO).collect(Collectors.toList());

    }


    @Override
    public List<ItemDTO> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        if (foodItems.isEmpty()) {
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND, "Food items not found!");
        }
        return foodItems.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllActiveFoodItems() {
        List<FoodItem> foodItems = foodItemRepository.findAllByActiveTrue()
                .orElseThrow(() -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Food items not found!"));
        return foodItems.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllDrinkItems() {
        List<DrinkItem> drinkItems = drinkItemRepository.findAll();
        if (drinkItems.isEmpty()) {
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND, "Drink items not found!");
        }
        return drinkItems.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllActiveDrinkItems() {
        List<DrinkItem> drinkItems = drinkItemRepository.findAllByActiveTrue()
                .orElseThrow(() -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Food items not found!"));
        return drinkItems.stream().map(itemMapper::toDTO).collect(Collectors.toList());
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

    public void validateUniqueItemName(String name) {
        Optional<Item> existingFoodItemName = foodItemRepository.findByName(name);
        Optional<Item> existingDrinkItemName = drinkItemRepository.findByName(name);
        if (existingDrinkItemName.isPresent() || existingFoodItemName.isPresent()) {
            throw new ItemWithSameNameAlreadyExists(HttpStatus.BAD_REQUEST,
                    "Item with name: '" + name + "' already exists!"
            );
        }
    }

    //TODO: checkForValidName when creating
}
