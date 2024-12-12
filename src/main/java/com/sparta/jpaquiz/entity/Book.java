package com.sparta.jpaquiz.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    /**
     * TODO 2: 1:N 관계의 Owning Side 설정 - 여러개의 Book은 하나의 Category에 속합니다.
     * --------------------------------------------------------
     * 조건: 카테고리의 외래키 이름은 명시적으로 "category_id_ref"로 설정합니다.
     * 조건: 연관된 카테고리(Category)엔티티는 실제로 필요할때만 DB에서 조회하도록 명시적으로 설정
     * Hint: ManyToOne 관계를 정의하고 Fetch 전략을 Lazy로 설정하세요.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id_ref")
    private Category category;

//    1. @manytoone 책은 하나의 카테고리에만
//    2. fetchtype.lazy 필요할 때만 카테고리 정보 가져온다.
//    3. @JoinColumn(name = "category_id_ref"): 데이터베이스에서 category_id_ref라는 이름으로 연결

    /**
     * TODO 3: N:N 관계 설정 - 하나의 책(Book)은 여러 저자(Author)와 관련되며 하나의 저자(Author)는 여러 책(Book)과 관련될 수도 있습니다.
     * --------------------------------------------------------
     * 조건: ManyToMany 관계로 설정하여 중간테이블이 자동으로 생성되도록 설정
     * 조건: 책(Book)을 저장하는 시점에, 연관된 저자(Author) 정보도 함께 저장 되도록 설정 *HINT:Cascade 설정
     * 조건: 중간테이블 이름은 명시적으로 "book_author"로 설정
     * 조건: 책/저자의 외래키 이름은 명시적으로 각각 "book_id"/"author_id"로 설정
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    public Book(String title) {
        this.title = title;
    }

    public Book() {

    }

    public void setCategory(Category category) {
            this.category = category;
    }
    public void addAuthor(Author author) {
        this.authors.add(author);
    }
}
//1. @manytomany : 책과 저자 다대다
//2. @jointtable 중간 테이블 이름, 외래키 이름 지정
