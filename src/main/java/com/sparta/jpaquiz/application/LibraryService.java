package com.sparta.jpaquiz.application;

import com.sparta.jpaquiz.entity.Author;
import com.sparta.jpaquiz.entity.Book;
import com.sparta.jpaquiz.entity.Category;
import com.sparta.jpaquiz.repository.AuthorRepository;
import com.sparta.jpaquiz.repository.BookRepository;
import com.sparta.jpaquiz.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibraryService {

    private final CategoryRepository categoryRepository;

    public LibraryService(CategoryRepository categoryRepository, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * TODO 5: Persistence Context와 @Transactional을 활용해 Category와 Book을 저장합니다.
     * 처리 순서:
     * 1. categoryName 인자로 새로운 Category(category1) 객체를 생성합니다.
     * 2. bookTitle1, bookTitle2 인자로 새로운 Book(book1, book2) 객체를 생성합니다.
     * 3. 생성한 book1, book2을 category1 과 연결합니다. * DB의 외래 키 값을 업데이트하기 위해 Owning 사이드(Book)에 추가
     * 4. category1과 book1, book2 을 연결합니다. * 메모리내 객체 상태 동기화를 위해 Non-Owning 사이드(Category)에도 추가
     * 5. category1 저장 * Cascade 설정을 활용해 Category 저장 시 Book도 함께 저장
     * ----------------------------------------------------------------
     * 조건: Category,Book 의 Setter 는 사용하지 않도록 합니다.
     */
    @Transactional
    public void addCategoryWithBooks(String categoryName, String bookTitle1, String bookTitle2) {
        // 1. 카테고리를 생성
        Category category = new Category(categoryName);
        // 2. 책을 두권 만들기
        Book book1 = new Book(bookTitle1);
        Book book2 = new Book(bookTitle2);
         //3. 카테고리에 책을 추가
        category.addBook(book1);
        category.addBook(book2);
        categoryRepository.save(category); //4. 카테고리 저장
    }
}

