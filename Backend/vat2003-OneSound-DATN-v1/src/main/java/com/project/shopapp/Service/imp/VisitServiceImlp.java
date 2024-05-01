package com.project.shopapp.Service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.VisitService;
import com.project.shopapp.entity.CountAccountDTO;
import com.project.shopapp.entity.Visit;
import com.project.shopapp.entity.VisitCountDTO;
import com.project.shopapp.repository.VisitDAO;

/**
 * VisitServiceImlp
 */
@Service
public class VisitServiceImlp implements VisitService {
    @Autowired
    private VisitDAO visitDAO;

    @Override
    public Visit createVisit(Visit visit) {
        return visitDAO.save(visit);
    }

    @Override
    public Long countVisit() {
        return visitDAO.count();
    }

    @Override
    public List<VisitCountDTO> countVisitBetweenDate(Date date1, Date date2) {
        return visitDAO.countVisitBetweenDate(date1, date2);
    }

    // @Override
    // public List<VisitCountDTO> countVisitWDate() {
    // return visitDAO.countVisitsByDate();
    // }

}