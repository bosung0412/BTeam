package kr.co.ict.project.login.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "FAQBoard")
public class FAQBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faqboard_seq")
    @SequenceGenerator(name = "faqboard_seq", sequenceName = "FAQBOARD_SEQ", allocationSize = 1)
    private Long ONO;

    @Column(nullable = false)
    private Long Member_No;

    private String oname;
    private String ocontent;
    private LocalDateTime oregdate = LocalDateTime.now(); // 여기 내가 추가했음
    private String ocategory;
    private String ocentent;
}
