package com.project.shopapp.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.CountAccountDTO;
import com.project.shopapp.entity.ReportAccountByYear;
import com.project.shopapp.entity.Role;


public interface AccountDAO extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    List<Account> findByAccountRole(Role accountRole);
    
    boolean existsByEmail(String email);

    @Query("SELECT a FROM Account a WHERE LOWER(a.fullname) LIKE LOWER(CONCAT('%', :fullname, '%'))")
    Page<Account> findByFullnamePage(String fullname, Pageable pageable);
    @Query("SELECT COUNT(id) FROM Account id")
    long getTotalUsers();

    @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) from Account a  GROUP BY a.createdDate ORDER BY a.createdDate DESC")
    List<CountAccountDTO> countByCreatedDateDESC();

    @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) FROM Account a WHERE a.createdDate BETWEEN :date1 AND :date2 GROUP BY a.createdDate ORDER BY a.createdDate DESC")
    List<CountAccountDTO> getAllAccountByBetweenCreatedDate(@Param("date1") Date date1, @Param("date2") Date date2);

    @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) from Account a  GROUP BY a.createdDate ORDER BY a.createdDate ASC")
    List<CountAccountDTO> countByCreatedDateAsc();

    @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) from Account a  GROUP BY a.createdDate ORDER BY COUNT(a) DESC ")
    List<CountAccountDTO> countByCreatedById();

    // @Query("SELECT new CountAccountDTO(COUNT(a) as quantity, a.createdDate) from
    // Account a WHERE a.createdDate = :date GROUP BY a.createdDate ORDER BY
    // a.createdDate DESC")

    // List<CountAccountDTO> countByCreatedDate(@Param("date") Date date);
    @Query("select a from Account a where a.createdDate = :date ")
    List<Account> getAllAccountByCreatedDate(@Param("date") Date date);

    @Query("select a from Account a where a.createdDate between :date1 and :date2")
    List<Account> getAccountByBetweenCreatedDate(@Param("date1") Date date1, @Param("date2") Date date2);

    @Query("SELECT a FROM Account a WHERE DAY(a.createdDate) = :day")
    List<Account> countAccountByDayOfCreateDate(@Param("day") Integer day);

    @Query("SELECT a FROM Account a WHERE FUNCTION('MONTH', a.createdDate) = :month")
    List<Account> countAccountByMounthOfCreateDate(@Param("month") Integer month);

    @Query("SELECT a FROM Account a WHERE FUNCTION('YEAR', a.createdDate) = :year")
    List<Account> countAccountByYearOfCreateDate(@Param("year") Integer year);

    @Query(value = "EXEC count_users_and_creation_dates :p_day, :p_month, :p_year", nativeQuery = true)
//    @Procedure(name = "count_users_and_creation_dates")
    List<Account> getUserByOptionDate(@Param("p_day") Integer day, @Param("p_month") Integer month,
            @Param("p_year") Integer year);

    // @Query("SELECT new CountAccountByMonthDTO(COUNT(a) as count,

    @Query("Select new ReportAccountByYear(COUNT(a), MONTH(a.createdDate)) from Account a group by MONTH(a.createdDate)")
    List<ReportAccountByYear> getMonthOfCreateDate();

//    @Query(value = "EXEC get_user_by_year_order_by_month_of_create_date :p_year", nativeQuery = true)
//    @Procedure(name = "get_user_by_year_order_by_month_of_create_date")
//    List<Object[]> getCountAccountByYear(@Param("p_year") Integer year);

    @Query(value = "EXEC get_user_by_year_order_by_month_of_create_date :p_year", nativeQuery = true)
    List<Object[]> getCountAccountByYear(@Param("p_year") Integer year);
}
