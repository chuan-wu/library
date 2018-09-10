package chuan.wu.library.repository;

import chuan.wu.library.LibraryApplicationTests;
import chuan.wu.library.model.Book;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BookRepositoryTest extends LibraryApplicationTests {

    @Autowired
    BookRepository repository;

    @Test
    public void testSave() {
        Book book = new Book();
        book.setAuthor("Jackie");
        book.setIsbn("aklektk");
        book.setTitle("Javascript essentials");

        repository.save(book);
        Optional<Book> bookOptional = repository.findById(book.getId());
        Assert.assertTrue(bookOptional.isPresent());
    }

}