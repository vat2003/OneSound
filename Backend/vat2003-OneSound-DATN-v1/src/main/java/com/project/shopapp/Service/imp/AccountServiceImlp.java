
package com.project.shopapp.Service.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.project.shopapp.entity.*;
import com.project.shopapp.security.DataNotFoundException;
import com.project.shopapp.utils.UpdateUserDTO;

import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.AccountService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.FeedRequest;
import com.project.shopapp.entity.PasswordResetToken;
import com.project.shopapp.entity.Role;
import com.project.shopapp.entity.UserLoginDTO;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.RoleDAO;
import com.project.shopapp.repository.TokenRepositoryDAO;
import com.project.shopapp.security.JwtTokenUtil;

@Service
public class AccountServiceImlp implements AccountService {

    public String sendEmailFedd(FeedRequest user) {
        try {
            String emailContent = user.getContent();

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(user.getEmail());
            helper.setSubject(user.getReason());
            helper.setText(emailContent, true);

            javaMailSender.send(message);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public Account updateAccount(Long id, UpdateUserDTO updateUserDTO) {
        Account existingUser = AccountDAO.findById(id)
                .orElseThrow();

        String mail = updateUserDTO.getEmail();
        if (!existingUser.getEmail().equals(mail) &&
                AccountDAO.existsByEmail(mail)) {
            throw new DataIntegrityViolationException("Email  already exists");
        }

        if (updateUserDTO.getFullName() != null) {
            existingUser.setFullname(updateUserDTO.getFullName());
        }

        if (updateUserDTO.getAddress() != null) {
            existingUser.setAddress(updateUserDTO.getAddress());
        }

        existingUser.setBirthday(updateUserDTO.getBirthday());
        if (updateUserDTO.getAvatar_url() != null) {
            existingUser.setAvatar_url(updateUserDTO.getAvatar_url());
        }

        if (updateUserDTO.isGender() != existingUser.isGender()) {
            existingUser.setGender(updateUserDTO.isGender());
        }

        if (updateUserDTO.getAccountRole() != existingUser.getAccountRole()) {
            existingUser.setAccountRole(updateUserDTO.getAccountRole());
        }

        return AccountDAO.save(existingUser);
    }

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private TokenRepositoryDAO TokenRepositoryDAO;

    @Override
    public Account getAccountByEmail(String email) {
        return AccountDAO.findByEmail(email).orElseThrow();
    }

    @Autowired
    private com.project.shopapp.repository.RoleDAO RoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Account createAccount(Account account) {
        if (AccountDAO.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }
        if (account.getAccountRole() == null) {

            Role userRole = RoleDAO.findById(1L).orElseThrow();
            account.setAccountRole(userRole);
        }
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        account.setActive(true);

        // account.setAccountRole(userRole);

        Account savedAccount = AccountDAO.save(account);
        System.out.println(savedAccount);
        return savedAccount;
    }

    @Override
    public Account createAccountadmin(Account account) {
        if (AccountDAO.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }

        if (account.getAccountRole() == null) {
            Role userRole = RoleDAO.findById(1L).orElseThrow();
            account.setAccountRole(userRole);
        }
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        Account savedAccount = AccountDAO.save(account);
        System.out.println(savedAccount);

        return savedAccount;
    }

    @Override
    public Account quenmk(Account account) {
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        Account savedAccount = AccountDAO.save(account);
        return savedAccount;
    }

    @Override
    // public Account updateAccount(Long id, Account account) {
    public Account updateAccount(Long id, Account updatedAccount) {
        Account existingAccount = AccountDAO.findById(id).orElse(null);
        if (updatedAccount.getEmail() != existingAccount.getEmail()) {
            Account other = AccountDAO.findByEmail(updatedAccount.getEmail()).orElse(null);
            if (other != null && other.getId() != existingAccount.getId()) {
                System.err.println("Đã có tài khoản đăng ký địa chỉ email này, vui lòng chọn email khác!");
                // Update the fields of the existing account with the provided values
                existingAccount.setFullname(updatedAccount.getFullname());
                existingAccount.setActive(updatedAccount.isActive());
                existingAccount.setAddress(updatedAccount.getAddress());
                existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
                existingAccount.setGender(updatedAccount.isGender());
                existingAccount.setBirthday(updatedAccount.getBirthday());
                existingAccount.setPhonenumber(updatedAccount.getPhonenumber());
                existingAccount.setAccountRole(updatedAccount.getAccountRole());
                existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
            } else {
                existingAccount.setFullname(updatedAccount.getFullname());
                existingAccount.setActive(updatedAccount.isActive());
                existingAccount.setAddress(updatedAccount.getAddress());
                existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
                existingAccount.setGender(updatedAccount.isGender());
                existingAccount.setBirthday(updatedAccount.getBirthday());
                existingAccount.setPhonenumber(updatedAccount.getPhonenumber());
                existingAccount.setAccountRole(updatedAccount.getAccountRole());
                existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
                existingAccount.setEmail(updatedAccount.getEmail());
                System.err.println("Đang chạy trường hợp 2");
            }
        } else {
            existingAccount.setFullname(updatedAccount.getFullname());
            existingAccount.setActive(updatedAccount.isActive());
            existingAccount.setAddress(updatedAccount.getAddress());
            existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
            existingAccount.setGender(updatedAccount.isGender());
            existingAccount.setBirthday(updatedAccount.getBirthday());
            existingAccount.setPhonenumber(updatedAccount.getPhonenumber());
            existingAccount.setAccountRole(updatedAccount.getAccountRole());
            existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
            existingAccount.setEmail(updatedAccount.getEmail());
            System.err.println("Đang chạy trường hợp 3");
        }
        // Update other fields as needed

        Account updatedAccountEntity = AccountDAO.save(existingAccount);
        return updatedAccountEntity;
    }

    @Override
    public Account updateAccountadmin(Long id, Account account) {
        Account existingAccount = AccountDAO.findById(id).orElse(null);

        if (existingAccount == null) {
            throw new IllegalArgumentException("Account not found with id: " + id);
        }

        String newEmail = account.getEmail();
        if (newEmail != null && !newEmail.equals(existingAccount.getEmail()) && AccountDAO.existsByEmail(newEmail)) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }
        // String password = account.getPassword();
        // String encodedPassword = passwordEncoder.encode(password);
        // existingAccount.setPassword(encodedPassword);
        existingAccount.setFullname(account.getFullname());
        existingAccount.setBirthday(account.getBirthday());
        existingAccount.setEmail(newEmail);
        existingAccount.setAddress(account.getAddress());
        existingAccount.setAvatar_url(account.getAvatar_url());
        existingAccount.setGender(account.isGender());
        existingAccount.setPhone(account.getPhone());
        existingAccount.setActive(account.isActive());
        existingAccount.setAccountRole(account.getAccountRole());

        Account updatedAccountEntity = AccountDAO.save(existingAccount);
        return updatedAccountEntity;
    }

    @Override
    public void deleteAccount(Long accountId) {
        AccountDAO.deleteById(accountId);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return AccountDAO.findById(accountId).orElse(null);
    }

    @Override
    public List<Account> getAllAccount() {
        return AccountDAO.findAll();
    }

    @Override
    public Page<Account> getAllAccount(Pageable pageable) {
        return AccountDAO.findAll(pageable);
    }

    @Override
    public String login(String mail, String password) throws Exception {

        Optional<Account> optionalUser = AccountDAO.findByEmail(mail);

        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("User not found");
        }

        Account existingUser = optionalUser.get();
        Long userIdLong = existingUser.getId();

        // Optional<Role> optionalRole = RoleDAO.findById(userIdLong);

        // if (existingUser.getAccountRole() == null || !optionalRole.isPresent() ||
        // !optionalRole.get().getId().equals(existingUser.getAccountRole().getId())) {
        // throw new DataNotFoundException("Không tìm thấy role");
        // }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                mail, password,
                existingUser.getAuthorities());

        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);

    }

    @Override
    public Account getUserDetailsFromToken(String token) throws Exception {
        if (jwtTokenUtil.isTokenExpired(token)) {
            throw new Exception("Token is expired");
        }
        String Email = jwtTokenUtil.extractEmail(token);
        Optional<Account> user = AccountDAO.findByEmail(Email);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return AccountDAO.existsByEmail(email);
    }

    public String sendEmailFedd(String senderEmail, String recipientEmail, FeedRequest user) {
        try {
            String emailContent = user.getContent();

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            helper.setFrom(senderEmail); // Set sender's email
            helper.setTo(recipientEmail); // Set fixed recipient's email

            String subject = "Feedback from '" + senderEmail + "' - '" + user.getReason() + "'";
            helper.setSubject(subject);
//            helper.setSubject(user.getReason());
            helper.setText(emailContent, true);

            javaMailSender.send(message);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    public String sendEmail(Account user) {
        try {
            String resetLink = generateResetToken(user);
            String emailContent = "Hello, This is a reset password mail from ONESOUND <br/><br/>"
                    + "<div style='border: 2px solid #007bff; border-radius: 8px; background-color: #f8f9fa; padding: 20px; width: 40%; margin: 20px auto; font-family: Arial, sans-serif;'>"
                    + "<p style='margin: 10px 0; line-height: 1.4;'>Xin chào <span style='color: #007bff; font-weight: bold;'>"
                    + user.getFullname() + "</span>,</p>"
                    + "<p style='margin: 10px 0; line-height: 1.4;'>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu Facebook của bạn.</p>"
                    + "<p style='margin: 10px 0; line-height: 1.4;'>Nhập mã đặt lại mật khẩu sau đây:</p>"
                    + "<p style='margin: 10px 0; line-height: 1.4;'>Ngoài ra, bạn có thể thay đổi trực tiếp mật khẩu của mình.</p>"
                    + "<a href='" + resetLink
                    + "' style='display: inline-block; width: 93%; background-color: #007bff; color: #fff; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; text-align: center; text-decoration: none;'>Đổi mật khẩu</a>"
                    + "<p style='margin: 10px 0; line-height: 1.4;'><b>Bạn đã không yêu cầu thay đổi này?</b></p>"
                    + "<p style='margin: 10px 0; line-height: 1.4;'>Nếu bạn không yêu cầu mật khẩu mới, <span style='color: #007bff; font-weight: bold;'>hãy cho chúng tôi biết</span></p>"
                    + "</div>";
            System.out.println(resetLink);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(user.getEmail());
            helper.setSubject("RESET PASSWORD FOR ONESOUND ACCOUNT");
            helper.setText(emailContent, true);

            javaMailSender.send(message);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public Account updateAccountActive(Long id, Account account) {
        Account existingAccount = AccountDAO.findById(id).orElse(null);

        if (existingAccount == null) {
            throw new IllegalArgumentException("Account not found with id: " + id);
        }
        String newEmail = account.getEmail();
        if (newEmail != null && !newEmail.equals(existingAccount.getEmail()) && AccountDAO.existsByEmail(newEmail)) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }
        existingAccount.setActive(true);
        Account updatedAccountEntity = AccountDAO.save(existingAccount);
        return updatedAccountEntity;
    }

    public String sendEmaildoimk(Account user) {
        try {
            String resetLink = generateResetToken(user);
            String emailContent = "Your password has been changed!! -- " + user.getFullname();

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(user.getEmail());
            helper.setSubject("RESET PASSWORD FOR ONESOUND ACCOUNT");
            helper.setText(emailContent, true);

            javaMailSender.send(message);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String generateResetToken(Account user) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);

        PasswordResetToken existingToken = TokenRepositoryDAO.findByAccount(user);
        PasswordResetToken tokenToSave;

        if (existingToken == null) {
            tokenToSave = new PasswordResetToken();
        } else {
            tokenToSave = existingToken;
        }

        tokenToSave.setAccount(user);
        tokenToSave.setToken(uuid.toString());
        tokenToSave.setExpiryDateTime(expiryDateTime);

        PasswordResetToken savedToken = TokenRepositoryDAO.save(tokenToSave);
        if (savedToken != null) {
            String endpointUrl = "http://localhost:4200/onesound/changepassword";
            return endpointUrl + "/" + savedToken.getToken() + "?a=" + user.getEmail();
        }

        return "";
    }

    public boolean hasExipred(LocalDateTime expiryDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return expiryDateTime.isAfter(currentDateTime);
    }

    @Override
    public List<CountAccountDTO> countAccountByDate(int index) {
        if (index == 0) {
            return AccountDAO.countByCreatedDateDESC();
        } else {
            return AccountDAO.countByCreatedDateAsc();
        }

    }

    @Override
    public List<Account> getAllAccountByCreatedDate(Date date) {
        return AccountDAO.getAllAccountByCreatedDate(date);
    }

    @Override
    public List<CountAccountDTO> countByCreatedById(int index) {
        return AccountDAO.countByCreatedById();
    }

    @Override
    public Account UpdatePassUser(String email, UpdateUserDTO UpdateUserDTO) {
        Account existingAccount = AccountDAO.findByEmail(email).orElse(null);

        if (existingAccount != null) {

            String password = UpdateUserDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            existingAccount.setPassword(encodedPassword);

            Account savedAccount = AccountDAO.save(existingAccount);

            return savedAccount;
        } else {
            throw new IllegalArgumentException("Account not found for email: " + email);
        }
    }

    public String sendCustomEmail(String toEmail, String messageType) {
        try {
            Account existingAccount = AccountDAO.findByEmail(toEmail).orElse(null);

            if (toEmail != null && !toEmail.isEmpty()) {
                String emailContent = "";
                String subject = "";
                if (messageType.equals("lock")) {
                    emailContent = "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>Tài khoản bị khoá</title>\n" +
                            "</head>\n" +
                            "<body style=\"font-family: Arial, sans-serif; margin: 10px; padding: 0; background-color: #f5f5f5; \">\n"
                            +
                            "    <div style=\"max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n"
                            +
                            "        <div style=\"text-align: center; margin-bottom: 30px;\">\n" +
                            "            <h1 style=\"color: #333333;\">Thông báo từ trang web nghe nhạc onesound</h1>\n"
                            +
                            "        </div>\n" +
                            "        <div style=\"margin-bottom: 30px;\">\n" +
                            "            <p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">Xin chào, "
                            + existingAccount.getFullname() + "</p>\n"
                            +
                            "            <p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">Tài khoản của bạn đã bị khoá trên hệ thống trang web nghe nhạc onesound, nếu có gì thắc mắc, vui lòng liên hệ với chúng tôi để biết thêm thông tin chi tiết.</p>\n"
                            +
                            "        </div>\n" +
                            "        <div style=\"text-align: center;\">\n" +
                            "            <p style=\"color: #999999; font-size: 14px;\">Liên hệ: 0999999999</p>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</body>\n" +
                            "</html>";
                    subject = "Thông Báo Khoá Tài Khoản";
                } else if (messageType.equals("unlock")) {
                    emailContent = "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>Tài khoản được mở khoá</title>\n" +
                            "</head>\n" +
                            "<body style=\"font-family: Arial, sans-serif; margin: 10px; padding: 0; background-color: #f5f5f5; \">\n"
                            +
                            "    <div style=\"max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n"
                            +
                            "        <div style=\"text-align: center; margin-bottom: 30px;\">\n" +
                            "            <h1 style=\"color: #333333;\">Thông báo từ trang web nghe nhạc onesound</h1>\n"
                            +
                            "        </div>\n" +
                            "        <div style=\"margin-bottom: 30px;\">\n" +
                            "            <p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">Xin chào, "
                            + existingAccount.getFullname() + "</p>\n"
                            +
                            "            <p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">Tài khoản của bạn đã được mở khoá trên hệ thống trang web nghe nhạc onesound, nếu có gì thắc mắc, vui lòng liên hệ với chúng tôi để biết thêm thông tin chi tiết.</p>\n"
                            +
                            "        </div>\n" +
                            "        <div style=\"text-align: center;\">\n" +
                            "            <p style=\"color: #999999; font-size: 14px;\">Liên hệ: 0999999999</p>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</body>\n" +
                            "</html>";
                    subject = "Thông Báo Mở Khoá Tài Khoản";
                } else if (messageType.equals("delete")) {
                    emailContent = "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>Tài khoản đã bị xóa</title>\n" +
                            "</head>\n" +
                            "<body style=\"font-family: Arial, sans-serif; margin: 10px; padding: 0; background-color: #f5f5f5; \">\n"
                            +
                            "    <div style=\"max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n"
                            +
                            "        <div style=\"text-align: center; margin-bottom: 30px;\">\n" +
                            "            <h1 style=\"color: #333333;\">Thông báo từ trang web nghe nhạc onesound</h1>\n"
                            +
                            "        </div>\n" +
                            "        <div style=\"margin-bottom: 30px;\">\n" +
                            "            <p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">Xin chào, "
                            + existingAccount.getFullname() + "</p>\n"
                            +
                            "            <p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">Tài khoản của bạn đã bị xoá khỏi hệ thống trang web nghe nhạc onesound, nếu có gì thắc mắc, vui lòng liên hệ với chúng tôi để biết thêm thông tin chi tiết.</p>\n"
                            +
                            "        </div>\n" +
                            "        <div style=\"text-align: center;\">\n" +
                            "            <p style=\"color: #999999; font-size: 14px;\">Liên hệ: 0999999999</p>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</body>\n" +
                            "</html>";
                    subject = "Thông Báo Xoá Tài Khoản";
                } else if (messageType.equals("create")) {
                    emailContent = "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>Tài khoản được tạo</title>\n" +
                            "</head>\n" +
                            "<body style=\"font-family: Arial, sans-serif; margin: 10px; padding: 0; background-color: #f5f5f5; \">\n"
                            +
                            "    <div style=\"max-width: 600px; margin: 20px auto; background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n"
                            +
                            "        <div style=\"text-align: center; margin-bottom: 30px;\">\n" +
                            "            <h1 style=\"color: #333333;\">Chào mừng bạn đến với onesound</h1>\n" +
                            "        </div>\n" +
                            "        <div style=\"margin-bottom: 30px;\">\n" +
                            "            <p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">Xin chào, "
                            + existingAccount.getFullname() + "</p>\n"
                            +
                            "            <p style=\"color: #666666; font-size: 16px; line-height: 1.5;\">Tài khoản của bạn đã được tạo trên trang web nghe nhạc onesound, nếu có gì thắc mắc, vui lòng liên hệ với chúng tôi để biết thêm thông tin chi tiết.</p>\n"
                            +
                            "        </div>\n" +
                            "        <div style=\"text-align: center;\">\n" +
                            "            <p style=\"color: #999999; font-size: 14px;\">Liên hệ: 0999999999</p>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "</body>\n" +
                            "</html>";
                    subject = "Thông Báo Tạo Tài Khoản";
                } else {
                    return "error: Invalid message type";
                }

                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                helper.setTo(toEmail);
                helper.setSubject(subject);
                helper.setText(emailContent, true);
                javaMailSender.send(message);
                return "success";
            } else {
                return "error: Invalid email or message";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error: " + e.getMessage();
        }
    }

    @Override
    public Account createAccountfb(Account account) {

        if (account.getAccountRole() == null) {
            Role userRole = RoleDAO.findById(1L).orElseThrow();
            account.setAccountRole(userRole);
        }
        Account savedAccount = AccountDAO.save(account);
        System.out.println(savedAccount);
        return savedAccount;
    }

    @Override
    public List<CountAccountDTO> countAccountBetweenByDate(Date date1, Date date2) {
        return AccountDAO.getAllAccountByBetweenCreatedDate(date1, date2);
    }

    @Override
    public List<Account> getAllAccountBetweenCreatedDate(Date date1, Date date2) {
        return AccountDAO.getAccountByBetweenCreatedDate(date1, date2);
    }

    @Override
    public List<Account> countByCreatedByDayOfCreateDate(Integer day) {
        return AccountDAO.countAccountByDayOfCreateDate(day);
    }

    @Override
    public List<Account> countByCreatedByMonthOfCreateDate(Integer month) {
        return AccountDAO.countAccountByMounthOfCreateDate(month);
    }

    @Override
    public List<Account> countByCreatedByYearOfCreateDate(Integer year) {
        return AccountDAO.countAccountByYearOfCreateDate(year);
    }

    @Override
    public List<Account> getUserByOptionDate(Integer day, Integer month, Integer year) {
        return AccountDAO.getUserByOptionDate(day, month, year);
    }


    // @Override
    // public List<CountAccountByMonthDTO> getCountAccountByYear(Integer year) {
    // return AccountDAO.getCountAccountByYear(year);
    // }

}
