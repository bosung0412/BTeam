package kr.co.ict.project.login.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "faqboard")
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private UserEntity users;

    @Column(name = "oname")
    private String name;

    @Column(name = "ocentent")
    private String content;

    @Column(name = "oregdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @Column(name = "ocategory")
    private String category;

}
