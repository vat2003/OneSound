
package com.project.shopapp.controller;

import com.project.shopapp.entity.*;
import com.project.shopapp.repository.RoleDAO;
import com.project.shopapp.utils.UpdateUserDTO;
import com.project.shopapp.utils.thongbao;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.project.shopapp.Service.AccountService;
import com.project.shopapp.Service.HistoryListenServeice;
import com.project.shopapp.Service.PasswordResetTokenService;

import com.project.shopapp.Service.imp.AccountServiceImlp;
import com.project.shopapp.repository.AccountDAO;

import com.project.shopapp.repository.TokenRepositoryDAO;
import com.project.shopapp.utils.LoginResponse;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/users")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordResetTokenService PasswordResetTokenService;

    @Autowired
    private AccountServiceImlp AccountServiceImlp;

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private RoleDAO RoleDAO;

    @Autowired
    private TokenRepositoryDAO TokenRepositoryDAO;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/oauth2/login/success")
    public RedirectView success(OAuth2AuthenticationToken oauth) throws IOException, URISyntaxException {
        String method = oauth.getAuthorizedClientRegistrationId();
        String email = oauth.getPrincipal().getAttribute("email");
        String fullname = oauth.getPrincipal().getAttribute("name");
        String picture = oauth.getPrincipal().getAttribute("picture");
        System.out.println("EMAIL" + email);
        System.out.println("FULLNAME" + fullname);
        System.out.println("PICTURE" + picture);
        System.out.println("METHOD ==> " + method);
        String url = "http://localhost:4200/onesound/signin";

        // Optional<Account> acc =
        // Optional.of(AccountDAO.findByEmail(email).orElse(null));
        Optional<Account> acc = AccountDAO.findByEmail(email);
        // Account acc = accountService.getAccountByEmail(email);
        if (acc.isPresent()) {
            // if (acc != null) {
            System.out.println("THIS ACCOUNT ALREADY EXIST!");
            System.out.println(acc.get());
            return new RedirectView("http://localhost:4200/onesound/home/explore");

        } else {
            try {
                System.out.println("THIS ACCOUNT NOT EXIST!");
                Account newAcc = new Account();
                Role userRole = RoleDAO.findById(1).get();
                newAcc.setEmail(email);
                newAcc.setFullname(fullname);
                newAcc.setAccountRole(userRole);
                newAcc.setAvatar_url(picture);
                newAcc.setActive(true);

                if (method.equalsIgnoreCase("google")) {
                    newAcc.setProvider(AuthProvider.GOOGLE);
                } else if (method.equalsIgnoreCase("facebook")) {
                    newAcc.setProvider(AuthProvider.FACEBOOK);
                } else if (method.equalsIgnoreCase("github")) {
                    newAcc.setProvider(AuthProvider.GITHUB);
                }

                accountService.createAccountfb(newAcc);
                System.out.println("Create account successfully ==> " + newAcc.getFullname());
            } catch (Exception e) {
                System.err.println("****ERROR*****" + e);
            }
        }

        return new RedirectView("http://localhost:4200/onesound/home/explore");
    }

    @PostMapping("/checkactive")
    public ResponseEntity<?> hello1(@RequestBody UserIdDTO userIdDTO) {
        String id = userIdDTO.getEmail();
        Account a = accountService.getAccountByEmail(id);

        if (isActiveAccount(a)) {
            System.out.println(a);

            return ResponseEntity.ok().body(a);
        } else {
            return ResponseEntity.ok().body(null);
        }
    }

    @PutMapping("/UpdateActive/{id}")
    public ResponseEntity<?> updateUser1(
            @PathVariable Long id,
            @RequestBody Account updatedAccount) {

        try {
            accountService.updateAccountActive(id, updatedAccount);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/emailUser/{email}")
    public ResponseEntity<?> checkIfUserExistsByEmai1l(@PathVariable String email) {
        try {
            Account account = accountService.getAccountByEmail(email);

            return ResponseEntity.ok(account);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    private boolean isActiveAccount(Account account) {
        return account.isActive();
    }

    @PostMapping("/feed")
    public ResponseEntity<?> hello1(@RequestBody FeedRequest request) {
        if (request.getEmail() == null || request.getContent() == null || request.getEmail().isEmpty()
                || request.getContent().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and content cannot be empty");
        }

        // Set the recipient's email to the desired one
        String recipientEmail = "danghuutai2923@gmail.com";

        System.out.println("EMAIL == " + request.getEmail() + " Recipient == " + recipientEmail + " Request == " + request);
        // Call the method with the sender's email and the fixed recipient's email
        AccountServiceImlp.sendEmailFedd(request.getEmail(), recipientEmail, request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody Account Account,
                                        BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            accountService.createAccount(Account);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Account Account,
                                    BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            accountService.createAccountadmin(Account);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/user")
    public List<Account> getMethodName() {
        Role role = RoleDAO.findById(1).get();
        return AccountDAO.findByAccountRole(role);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = accountService.login(
                    userLoginDTO.getEmail(),
                    userLoginDTO.getPassword());
            return ResponseEntity.ok(LoginResponse.builder()

                    .token(token)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @PostMapping("/details")
    public ResponseEntity<Account> getUserDetails(
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String extractedToken = authorizationHeader.substring(7);
            Account user = accountService.getUserDetailsFromToken(extractedToken);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public List<Account> getAllUser() {
        return accountService.getAllAccount();
    }

    @GetMapping("/passwordResetTokens") // Đặt tên phản ánh chức năng của API endpoint
    public List<PasswordResetToken> getAllPasswordResetTokens() {
        return PasswordResetTokenService.getAllPasswordResetToken();
    }

    @GetMapping("/page")
    public Page<Account> getAllSingers(Pageable pageable) {
        return accountService.getAllAccount(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        Account accountToDelete = accountService.getAccountById(id);
        accountService.deleteAccount(accountToDelete.getId());
        return ResponseEntity.ok().build(); // Trả về 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getUser(@PathVariable Long id) {
        Account employee = accountService.getAccountById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/forgotPassword/{mail}")
    public ResponseEntity<?> forgotPassword(@PathVariable String mail) {
        try {
            Optional<Account> userOptional = AccountDAO.findByEmail(mail);

            if (userOptional.isPresent()) {
                Account user = userOptional.get();
                AccountServiceImlp.sendEmail(user);
                return ResponseEntity.ok(thongbao.builder().message(user.getEmail()).build());
            } else {
                return ResponseEntity.badRequest().body("Không tìm thấy mail " + mail);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @GetMapping("/resetPassword/token/{token}")
    public ResponseEntity<?> resetPasswordForm(@PathVariable String token) {
        try {
            PasswordResetToken reset = TokenRepositoryDAO.findByToken(token);

            if (reset == null) {
                return ResponseEntity.badRequest().body("Token not found");
            }

            passwordResetTokenService.DeletePasswordResetToken(reset.getId());
            return ResponseEntity.ok(thongbao.builder().message(reset.getAccount().getEmail()).build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/pass/{email}")
    public ResponseEntity<?> updatepassuser(@PathVariable String email, @RequestBody UpdateUserDTO UpdateUserDTO) {
        try {

            Account updatedAccount = accountService.UpdatePassUser(email, UpdateUserDTO);
            // AccountServiceImlp.sendEmaildoimk(updatedAccount);
            return ResponseEntity.ok(updatedAccount);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/resetPassword/quenmk")
    public ResponseEntity<Account> getUserDetails2(@RequestBody Account Account) {
        try {
            Account user = accountService.getAccountByEmail(Account.getEmail());
            accountService.quenmk(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // @GetMapping("/email")
    // public ResponseEntity<Account> getUserByEmail(@RequestParam String mail) {
    // Account account = accountService.getAccountByEmail(mail);
    // return ResponseEntity.ok(account);
    // }

    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> checkIfUserExistsByEmail(@PathVariable String email) {
        try {
            Account account = accountService.getAccountByEmail(email);
            boolean accountExists = account != null;

            return ResponseEntity.ok(accountExists);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody Account updatedAccount,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            accountService.updateAccount(id, updatedAccount);

            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/Account")
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountService.getAllAccount(pageable);
    }

    @PutMapping("/update/admin/{id}")
    public ResponseEntity<?> updateUserr(
            @PathVariable Long id,
            @Valid @RequestBody Account updatedAccount,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            accountService.updateAccountadmin(id, updatedAccount);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserr(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDTO UpdateUserDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            accountService.updateAccount(id, UpdateUserDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping("/mess/{mess}/{email}")
    public ResponseEntity<?> messmail(
            @PathVariable String mess,
            @PathVariable String email) {

        try {
            return ResponseEntity
                    .ok(thongbao.builder().message(AccountServiceImlp.sendCustomEmail(email, mess)).build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/users/getaccountByName/{title}")
    public Page<Account> getAlbumByTitle(@PathVariable String title, Pageable pageable) {
        return AccountDAO.findByFullnamePage(title, pageable);
    }

}
