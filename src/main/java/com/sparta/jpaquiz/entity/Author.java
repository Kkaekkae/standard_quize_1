package com.sparta.jpaquiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id" ))
    private List<Book> books = new ArrayList<>();
}