package com.sparta.jpaquiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor

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
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
    // String 매개변수를 받는 생성자 추가
    public Category(String name) {
        this.name = name;
    }

    // Book과 연관 관계를 추가하는 메서드
    public void addBook(Book book) {
        this.books.add(book);
        book.assignCategory(this); // DB에 insert는 문제없이 된다. 하지만 이게 없으면 다른 코드에서 참조한다고 했을때 인스턴스에서 업데이트 안된경우가 있기 때문에 유의하기!
    }

}
