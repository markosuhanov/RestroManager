package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.DrinkItemDTO;
import com.suhIT.restroManager.dto.FoodItemDTO;
import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.exception.*;
import com.suhIT.restroManager.mapper.ItemMapper;
import com.suhIT.restroManager.model.*;
import com.suhIT.restroManager.repository.ItemCategoryRepository;
import com.suhIT.restroManager.repository.ItemRepository;
import com.suhIT.restroManager.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {


    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;


    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper, ItemCategoryRepository itemCategoryRepository,
                           ItemRepository itemRepository) {

        this.itemMapper = itemMapper;
        this.itemCategoryRepository = itemCategoryRepository;
        this.itemRepository = itemRepository;
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
        if (itemType.equals("food")) {
            item = new FoodItem();
            BeanUtils.copyProperties(itemDTO, item);
        } else if (itemType.equals("drink")) {
            item = new DrinkItem();
            BeanUtils.copyProperties(itemDTO, item);
        } else {
            throw new BadRequest(HttpStatus.BAD_REQUEST, "Item type not choosen!");
        }
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setCost(itemDTO.getCost());
        item.setActive(true);
        item.setImgPath(itemDTO.getImgPath());
        ItemCategory itemCategory = itemCategoryRepository.findByName(itemDTO.getItemCategoryName()).orElseThrow(
                () -> new ItemCategoryNotFound(HttpStatus.BAD_REQUEST,
                        "Item category with name " + itemDTO.getItemCategoryName() + " not found!"
                ));
        item.setItemCategory(itemCategory);
        itemRepository.save(item);
        return itemMapper.toDTO(item);

    }

    @Override
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {
        Item existingItem = itemRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Item with id " + id + " not found"));
        if (!existingItem.getName().equals(itemDTO.getName())) {
            validateUniqueItemName(itemDTO.getName());
        }
        existingItem.setActive(false);
        itemRepository.save(existingItem);

        Item item = null;
        if (existingItem instanceof FoodItem) {
            item = new FoodItem();
            BeanUtils.copyProperties(itemDTO, item);
        } else if (existingItem instanceof DrinkItem) {
            item = new DrinkItem();
            BeanUtils.copyProperties(itemDTO, item);
        }
        item.setActive(true);
        item.setId(null);
        item.setItemCategory(this.itemCategoryRepository.findByName(itemDTO.getItemCategoryName()).get());

        return itemMapper.toDTO(itemRepository.save(item));
    }
    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllActiveItems() {
        List<Item> items = itemRepository.findAllByActiveIsTrue()
                .orElseThrow(() -> new NoSuchElementException(HttpStatus.NOT_FOUND, "Items doesn't exists!"));
        return items.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public List<ItemDTO> getAllFoodItems() {
        List<Item> items = itemRepository.findAllFoodItems()
                .orElseThrow(() -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Food items not found!"));
        return items.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllActiveFoodItems() {
        List<Item> items = itemRepository.findAllActiveFoodItems()
                .orElseThrow(() -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Food items not found!"));
        return items.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllDrinkItems() {
        List<Item> items = itemRepository.findAllDrinkItems()
                .orElseThrow(() -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Drink items not found!"));
        return items.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllActiveDrinkItems() {
        List<Item> items = itemRepository.findAllActiveDrinkItems()
                .orElseThrow(() -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Drink items not found!"));
        return items.stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllPromotions() {
        return null;
    }

    @Override
    public ItemDTO getItemByName(String itemName) {
        ItemDTO itemDto = itemMapper.toDTO(itemRepository.findByName(itemName).orElseThrow(
                () -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Item with name " + itemName + " not found!")));
        return itemDto;
    }



    @Override
    public ItemDTO deactivateItem(Long itemId) {

        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Item with id " + itemId + " not found!"));
        item.setActive(false);
        itemRepository.save(item);
        return itemMapper.toDTO(item);
    }

    @Override
    public ItemDTO activateItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Item with id " + itemId + " not found!"));
        item.setActive(true);
        itemRepository.save(item);
        return itemMapper.toDTO(item);
    }

    @Override
    public ItemDTO getItemById(Long itemId) {
        Item item =  itemRepository.findById(itemId).orElseThrow(
                () -> new ItemNotFoundException(HttpStatus.NOT_FOUND, "Item with id " + itemId + " not found!"));
        return itemMapper.toDTO(item);
    }



    public void validateUniqueItemName(String name) {

        Optional<Item> item = itemRepository.findByName(name);
        if (item.isPresent()) {
            throw new ItemWithSameNameAlreadyExists(HttpStatus.BAD_REQUEST,
                    "Item with name: '" + name + "' already exists!"
            );
        }
    }


    //TODO: checkForValidName when creating
}
