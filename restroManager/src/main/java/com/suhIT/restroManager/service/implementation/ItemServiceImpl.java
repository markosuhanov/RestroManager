package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.exception.ItemNotFoundException;
import com.suhIT.restroManager.exception.NoSuchElementException;
import com.suhIT.restroManager.mapper.ItemMapper;
import com.suhIT.restroManager.model.DrinkItem;
import com.suhIT.restroManager.model.FoodItem;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.repository.DrinkItemRepository;
import com.suhIT.restroManager.repository.FoodItemRepository;
import com.suhIT.restroManager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemServiceImpl implements ItemService {

    private final FoodItemRepository foodItemRepository;
    private final DrinkItemRepository drinkItemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper,  FoodItemRepository foodItemRepository, DrinkItemRepository drinkItemRepository)
    {

        this.itemMapper = itemMapper;
        this.foodItemRepository = foodItemRepository;
        this.drinkItemRepository = drinkItemRepository;
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
        List<FoodItem> foodItems = foodItemRepository.findAll();
        List<DrinkItem> drinkItems = drinkItemRepository.findAll();
        if (foodItems.isEmpty() && drinkItems.isEmpty()) {
            throw new NoSuchElementException(HttpStatus.NOT_FOUND, "Items doesn't exists!");
        }

        List<Item> concatenatedItems = Stream.concat(foodItems.stream(), drinkItems.stream()).collect(Collectors.toList());
        return concatenatedItems.stream().map(itemMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<ItemDTO> getAllActiveItems() {
        List<FoodItem> foodItems = foodItemRepository.findAllByActiveTrue().orElse(new ArrayList<>());
        List<DrinkItem> drinkItems = drinkItemRepository.findAllByActiveTrue().orElse(new ArrayList<>());

        if (foodItems.size() == 0 && drinkItems.size() == 0) {
            throw new NoSuchElementException(HttpStatus.NOT_FOUND, "Items doesn't exists!");
        }
        List<Item> concatenatedItems = Stream.concat(foodItems.stream(), drinkItems.stream()).collect(Collectors.toList());
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

    //TODO: checkForValidName when creating
}
