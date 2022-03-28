package com.example.crudmySQL.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "table_memo")
@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String memoText;

    @Column(length = 1, nullable = false)
    private boolean complete;
}
