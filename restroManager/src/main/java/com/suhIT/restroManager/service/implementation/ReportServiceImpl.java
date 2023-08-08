package com.suhIT.restroManager.service.implementation;

import com.suhIT.restroManager.dto.ItemDTO;
import com.suhIT.restroManager.dto.ItemReportDTO;
import com.suhIT.restroManager.dto.ReportRequestDTO;
import com.suhIT.restroManager.dto.UserReportDTO;
import com.suhIT.restroManager.model.Bill;
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
        return null;
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
