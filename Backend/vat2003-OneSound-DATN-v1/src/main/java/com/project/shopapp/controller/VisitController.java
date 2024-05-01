package com.project.shopapp.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.VisitService;
import com.project.shopapp.entity.CountAccountDTO;
import com.project.shopapp.entity.Visit;
import com.project.shopapp.entity.VisitCountDTO;
import com.project.shopapp.repository.VisitDAO;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * VisitController
 */

@RestController
@RequestMapping("${api.prefix}/visit")
@CrossOrigin(origins = "*")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @Autowired
    private VisitDAO visitDAO;

    @PostMapping("/record")
    public ResponseEntity<?> recordVisit() {
        Visit visit = new Visit();
        visit.setDate(new Date());
        visitService.createVisit(visit);
        return ResponseEntity.ok(visit);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getVisitCount() {
        long count = visitService.countVisit();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/date")
    public ResponseEntity<List<VisitCountDTO>> getVisitCountWithDate() {
        List<VisitCountDTO> l = visitDAO.countVisitWDate();
        return ResponseEntity.ok(l);
    }

    @GetMapping("/count/betweendate/{date1}/{date2}")
    public List<VisitCountDTO> getMethodName(@PathVariable("date1") Long date1, @PathVariable("date2") Long date2) {
        Date d1 = new Date(date1);
        Date d2 = new Date(date2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return visitService.countVisitBetweenDate(d1, d2);
    }

}