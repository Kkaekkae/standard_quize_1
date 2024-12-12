package com.sparta.jpaquiz.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.jpaquiz.entity.Book;
import com.sparta.jpaquiz.entity.Category;
import com.sparta.jpaquiz.repository.BookRepository;
import com.sparta.jpaquiz.repository.CategoryRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
class LibraryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void addCategoryWithBooksTest() {
        // Arrange
        LibraryService libraryService = new LibraryService(categoryRepository, bookRepository, null);
        String categoryName = "Fiction";
        String bookTitle1 = "Book One";
        String bookTitle2 = "Book Two";

        // Act
        libraryService.addCategoryWithBooks(categoryName, bookTitle1, bookTitle2);


        // Assert
        // 1. Category가 저장되었는지 확인
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories).hasSize(1);
        Category savedCategory = categories.get(0);
        assertThat(savedCategory.getName()).isEqualTo(categoryName);

        // 로그 출력
        System.out.println("Saved Category: " + savedCategory.getName());

        // 2. Book들이 저장되었는지 확인
        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(2);
        assertThat(books).extracting("title").containsExactlyInAnyOrder(bookTitle1, bookTitle2);

        // 로그 출력
        System.out.println("Saved Books:");
        books.forEach(book -> System.out.println(" - " + book.getTitle() + " (Category: " + book.getCategory().getName() + ")"));

        // 3. Book과 Category 간의 관계 확인
        assertThat(books.get(0).getCategory()).isEqualTo(savedCategory);
        assertThat(books.get(1).getCategory()).isEqualTo(savedCategory);
        assertThat(savedCategory.getBooks()).containsExactlyInAnyOrder(books.get(0), books.get(1));

        // 로그 출력
        System.out.println("Category-Book Relationships:");
        savedCategory.getBooks().forEach(book -> System.out.println(" - " + book.getTitle()));
    }
}