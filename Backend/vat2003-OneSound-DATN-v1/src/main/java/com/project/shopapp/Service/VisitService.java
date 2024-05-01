package com.project.shopapp.Service;

import java.util.Date;
import java.util.List;

import com.project.shopapp.entity.CountAccountDTO;
import com.project.shopapp.entity.Visit;
import com.project.shopapp.entity.VisitCountDTO;

public interface VisitService {
    Visit createVisit(Visit visit);

    Long countVisit();

    List<VisitCountDTO> countVisitBetweenDate(Date date1, Date date2);

    // List<VisitCountDTO> countVisitWDate();
}