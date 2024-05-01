package com.project.shopapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.HistoryListenServeice;
import com.project.shopapp.entity.HistoryListen;
import com.project.shopapp.repository.HistoryListenDAO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class HistoryController {

    @Autowired
    private HistoryListenServeice historyListenServeice;

    @Autowired
    private HistoryListenDAO listenDAO;

    @PostMapping("/listen/add/{songId}/{userId}")
    public ResponseEntity<?> addHis(@PathVariable("songId") Long songId, @PathVariable("userId") Long userId) {
        LocalDateTime todayLocalDate = LocalDateTime.now();

        // Chuyển đổi từ LocalDate sang Date
        ZonedDateTime zonedDateTime = todayLocalDate.atZone(ZoneId.systemDefault());
        System.out.println("------------------------->" + zonedDateTime);
        // Chuyển đổi ZonedDateTime sang Instant và sau đó sang Date
        Date todayDate = Date.from(zonedDateTime.toInstant());
        historyListenServeice.addHistory(songId, userId, todayDate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listen/get-by-user-id/{userId}")
    public List<HistoryListen> getMethodName(@PathVariable("userId") Long userId) {
        return historyListenServeice.finfByUserId(userId);
    }

    @DeleteMapping("/listen/delete/{userId}")
    public ResponseEntity<?> deleteHistoryy(@PathVariable("userId") Long userId) {
        try {
            listenDAO.deleteAllByUserId(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            String errorMessage = "Đã xảy ra lỗi khi xóa lịch sử nghe nhạc của người dùng có ID: " + userId;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    // deleteByUserIdAndSongId

    @DeleteMapping("/listen/delete/one/{id}")
    public ResponseEntity<?> deleteOneRecord(@PathVariable("id") Long id) {
        try {
            listenDAO.deleteByUserIdAndSongId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            String errorMessage = "Đã xảy ra lỗi khi xóa lịch sử nghe nhạc của người dùng có ID: " + id;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
