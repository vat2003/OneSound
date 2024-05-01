package com.project.shopapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.shopapp.entity.Visit;
import com.project.shopapp.entity.VisitCountDTO;

public interface VisitDAO extends JpaRepository<Visit, Long> {

    @Query("SELECT COUNT(v) FROM Visit v ")
    Long countVisit();

    // @Query("SELECT NEW VisitCountDTO(COUNT(v), FUNCTION('FORMAT', v.timestamp,
    // 'yyyy-MM-dd')) FROM Visit v GROUP BY FUNCTION('FORMAT', v.timestamp,
    // 'yyyy-MM-dd')")
    // List<VisitCountDTO> countVisitsByDate();

    @Query("select new VisitCountDTO(COUNT(v), v.date) from Visit v group by v.date")
    List<VisitCountDTO> countVisitWDate();

    // select Count(id), date from visit where date between '2024-02-25' and
    // '2024-02-29' group by date

    @Query("select new VisitCountDTO(COUNT(v), v.date) from Visit v where v.date between :date1 and :date2 group by v.date")
    List<VisitCountDTO> countVisitBetweenDate(Date date1, Date date2);

}
