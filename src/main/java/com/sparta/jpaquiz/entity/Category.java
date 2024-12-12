package com.sparta.jpaquiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();


    public Category(String name) {
        this.name = name;
}

    public Category() {

}

    public void addBook(Book book) {
        books.add(book); // 리스트에 책 추가
        book.setCategory(this); // Owning Side에도 관계 설정
    }
}
// 1. mappedby = book 클래스에 있는 category 연결
// 2. cascade = cascadetype. all = 카테고리 저장 삭제 ,관련된 책도 자동으로
// 3. fetch = fetchtype.lazy 책정보는 필요할 때만 가져온다 ,프로그램 속도