package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.*;
import com.suhIT.restroManager.model.Bill;
import com.suhIT.restroManager.model.DrinkItem;
import com.suhIT.restroManager.model.FoodItem;
import com.suhIT.restroManager.model.Item;
import com.suhIT.restroManager.repository.BillRepository;
import com.suhIT.restroManager.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {

    private final BillRepository billRepository;

    @Autowired
    public ReportServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public UserReportDTO createForUser(ReportRequestDTO reportRequest) {
        List<Bill> bills = this.billRepository.findBillsByDateRange(reportRequest.getDateFrom(),
                reportRequest.getDateUntil()
        );
        UserDTO userDTO = reportRequest.getUserDTO();
        Long id = userDTO.getId();
        String fullName = userDTO.getFirstName() + " " + userDTO.getLastName();
        String username = userDTO.getUsername();
        int totalItemSold = 0;
        int totalNumberOfBillsForUser = 0;
        double totalPricePerUser = 0;
        double totalCostPerUser= 0;
        if (userDTO.getRole().toString().equalsIgnoreCase("cook")) {
            totalItemSold = bills.stream()
                    .filter(bill -> bill.getCook() != null && bill.getCook().getUsername().equals(userDTO.getUsername()))
                    .flatMap(bill -> bill.getItems().stream())
                    .filter(item -> item instanceof FoodItem) // Assuming there's an ItemType enum
                    .mapToInt(item -> 1)
                    .sum();
            totalNumberOfBillsForUser = (int) bills.stream()
                    .filter(bill -> bill.getCook() != null && bill.getCook().getUsername().equals(username)) // Replace with your logic for checking the user
                    .count();
            totalPricePerUser = bills.stream()
                    .filter(bill -> bill.getCook() != null && bill.getCook().getUsername().equals(username))
                    .flatMap(bill -> bill.getItems().stream())
                    .filter(item -> item instanceof FoodItem)
                    .mapToDouble(Item::getPrice) // Assuming there's a getPrice() method in the Item class
                    .sum();
            totalCostPerUser = bills.stream()
                    .filter(bill -> bill.getCook() != null && bill.getCook().getUsername().equals(username))
                    .flatMap(bill -> bill.getItems().stream())
                    .filter(item -> item instanceof FoodItem)
                    .mapToDouble(Item::getCost) // Assuming there's a getCost() method in the Item class
                    .sum();
        } else if (userDTO.getRole().toString().equalsIgnoreCase("bartender")) {
            totalItemSold = bills.stream()
                    .filter(bill -> bill.getBartender() != null && bill.getBartender().getUsername().equals(userDTO.getUsername()))
                    .flatMap(bill -> bill.getItems().stream())
                    .filter(item -> item instanceof DrinkItem) // Assuming there's an ItemType enum
                    .mapToInt(item -> 1)
                    .sum();
            totalNumberOfBillsForUser = (int) bills.stream()
                    .filter(bill -> bill.getBartender() != null && bill.getBartender().getUsername().equals(username)) //
                    // Replace with your logic for checking the user
                    .count();
            totalPricePerUser = bills.stream()
                    .filter(bill -> bill.getBartender() != null && bill.getBartender().getUsername().equals(username))
                    .flatMap(bill -> bill.getItems().stream())
                    .filter(item -> item instanceof DrinkItem)
                    .mapToDouble(Item::getPrice) // Assuming there's a getPrice() method in the Item class
                    .sum();
            totalCostPerUser = bills.stream()
                    .filter(bill -> bill.getBartender() != null && bill.getBartender().getUsername().equals(username))
                    .flatMap(bill -> bill.getItems().stream())
                    .filter(item -> item instanceof DrinkItem)
                    .mapToDouble(Item::getCost) // Assuming there's a getCost() method in the Item class
                    .sum();
        } else if (userDTO.getRole().toString().equalsIgnoreCase("waiter")) {
            totalItemSold = bills.stream()
                    .filter(bill -> bill.getBartender() != null && bill.getBartender().getUsername().equals(userDTO.getUsername()))
                    .flatMap(bill -> bill.getItems().stream())
                    .mapToInt(item -> 1)
                    .sum();
            totalNumberOfBillsForUser = (int) bills.stream()
                    .filter(bill -> bill.getWaiter() != null && bill.getWaiter().getUsername().equals(username)) //
                    // Replace with your logic for checking the user
                    .count();
            totalPricePerUser = bills.stream()
                    .filter(bill -> bill.getWaiter() != null && bill.getWaiter().getUsername().equals(username))
                    .mapToDouble(Bill::getPrice)
                    .sum();

            totalCostPerUser = bills.stream()
                    .filter(bill -> bill.getWaiter() != null && bill.getWaiter().getUsername().equals(username))
                    .mapToDouble(Bill::getCost)
                    .sum();
        }
        double totalSalary = 0;
        UserReportDTO userReportDTO = UserReportDTO.builder().userId(id).fullName(fullName).username(username)
                .dateFrom(reportRequest.getDateFrom()).dateUntil(reportRequest.getDateUntil())
                .numberOfBills(totalNumberOfBillsForUser).totalPricePerUser(totalPricePerUser)
                .totalCostPerUser(totalCostPerUser).totalSalary(totalSalary).build();
        return userReportDTO;




    }

    @Override
    public ItemReportDTO createForItem(ReportRequestDTO reportRequest) {
        List<Bill> bills = this.billRepository.findBillsByDateRange(reportRequest.getDateFrom(),
                reportRequest.getDateUntil()
        );
        ItemDTO itemDto = reportRequest.getItemDTO();
        int totalItemSold = (int) bills.stream()
                .flatMap(bill -> bill.getItems().stream())
                .filter(item -> item.getName().equalsIgnoreCase(itemDto.getName()))
                .count();
        double totalPrice = bills.stream()
                .flatMap(bill -> bill.getItems().stream())
                .filter(item -> item.getName().equalsIgnoreCase(itemDto.getName()))
                .mapToDouble(Item::getPrice)
                .sum();

        double totalCost = bills.stream()
                .flatMap(bill -> bill.getItems().stream())
                .filter(item -> item.getName().equalsIgnoreCase(itemDto.getName()))
                .mapToDouble(Item::getCost)
                .sum();
        int totalNumberOfBills = (int) bills.stream()
                .filter(bill -> bill.getItems().stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemDto.getName())))
                .count();

        ItemReportDTO reportResponse= ItemReportDTO.builder()
                .itemId(itemDto.getId())
                .itemName(itemDto.getName())
                .dateFrom(reportRequest.getDateFrom())
                .dateUntil(reportRequest.getDateUntil())
                .totalItemSold(totalItemSold)
                .totalPrice(totalPrice)
                .totalCost(totalCost)
                .totalNoOfBillItemShowed(totalNumberOfBills)
                .build();

        return reportResponse;
    }
}
