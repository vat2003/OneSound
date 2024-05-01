package com.project.shopapp.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.project.shopapp.utils.UpdateUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.UserLoginDTO;
import com.project.shopapp.entity.CountAccountDTO;

public interface AccountService {

    List<Account> getAllAccount();

    Account getUserDetailsFromToken(String token) throws Exception;
    Account updateAccountActive(Long id, Account account);
    Page<Account> getAllAccount(Pageable pageable);

    Account createAccount(Account account);

    Account createAccountfb(Account account);

    Account createAccountadmin(Account account);

    Account quenmk(Account account);

    Account updateAccount(Long id, Account account);

    Account updateAccount(Long id, UpdateUserDTO updateUserDTO);

    Account updateAccountadmin(Long id, Account account);

    void deleteAccount(Long accountId);

    Account getAccountById(Long accountId);

    Account getAccountByEmail(String email);

    String login(String mail, String password) throws Exception;

    boolean existsByEmail(String email);

    List<CountAccountDTO> countAccountByDate(int index);

    List<CountAccountDTO> countAccountBetweenByDate(Date date1, Date date2);

    List<CountAccountDTO> countByCreatedById(int index);

    List<Account> getAllAccountByCreatedDate(Date date);

    List<Account> getAllAccountBetweenCreatedDate(Date date1, Date date2);

    Account UpdatePassUser(String email, UpdateUserDTO UpdateUserDTO);

    List<Account> countByCreatedByDayOfCreateDate(Integer day);

    List<Account> countByCreatedByMonthOfCreateDate(Integer month);

    List<Account> countByCreatedByYearOfCreateDate(Integer year);

    List<Account> getUserByOptionDate(Integer day, Integer month, Integer year);

    // List<CountAccountByMonthDTO> getCountAccountByYear(Integer year);
}
