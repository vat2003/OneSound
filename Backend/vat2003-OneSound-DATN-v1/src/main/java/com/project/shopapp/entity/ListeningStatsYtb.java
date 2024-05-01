package com.project.shopapp.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListeningStatsYtb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long listens;
    @Temporal(TemporalType.DATE)
    private Date dateLis;

    @ManyToOne
    @JoinColumn(name = "youtubeId")
    private Youtube youtube;

    public ListeningStatsYtb(Long listens, Date dateLis, Youtube youtube) {
        this.listens = listens;
        this.dateLis = dateLis;
        this.youtube = youtube;
    }

}
