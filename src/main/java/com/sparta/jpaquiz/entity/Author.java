package com.sparta.jpaquiz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /**
     * TODO 4: N:N 관계 설정 - Author는 여러 Book과 관련됩니다.
     * ------------------------------------------------
     * 조건: ManyToMany 관계로 설정하여 중간테이블이 자동으로 생성되도록 설정
     * 조건: 중간테이블 이름은 명시적으로 "book_author"로 설정
     * 조건: 책/저자의 외래키 이름은 명시적으로 각각 "book_id"/"author_id"로 설정
     */
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

}

// 1.mappedBy = "authors": Book 클래스에서 이미 관계를 설정
// 2. mappedBy 사용해서 읽기만