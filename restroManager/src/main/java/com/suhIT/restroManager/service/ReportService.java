package com.suhIT.restroManager.service;

import com.suhIT.restroManager.dto.ItemReportDTO;
import com.suhIT.restroManager.dto.ReportRequestDTO;
import com.suhIT.restroManager.dto.UserReportDTO;

public interface ReportService {

    UserReportDTO createForUser(ReportRequestDTO reportRequest);
    ItemReportDTO createForItem(ReportRequestDTO reportRequest);


}
