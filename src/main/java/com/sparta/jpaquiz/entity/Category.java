package com.sparta.jpaquiz.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    /**
     * TODO 1: 1:N 관계 설정 - 하나의 카테고리는 여러 책(Book)을 가질 수 있습니다.
     * ----------------------------------------------------------------
     * 조건: 카테고리(Category)를 저장하는 시점에 연관된 책(Book) 정보를 함께 저장 하도록 설정
     * 조건: 연관된 책(Book) 엔티티는 실제로 필요할때만 DB에서 조회하도록 명시적으로 설정
     * Hint: Cascade 설정을 추가하고 Lazy 로딩 전략을 사용하세요!
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

}
