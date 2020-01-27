package com.twu.biblioteca.menu;

import com.twu.biblioteca.books.Book;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfBooksTest {
    ListOfBooks listOfBooks = new ListOfBooks();

    @Test
    public void selectBookFromTheListShouldReturnProperOne() {
        Book selectedBook = listOfBooks.selectBook("1");
        assertEquals("Unlocking Android", selectedBook.getTitle());
        assertEquals(true, selectedBook.getIsAvailable());
    }

    @Test
    public void checkoutBookShouldChangeTheAvailableState() {
        Book selectedBook = listOfBooks.selectBook("1");
        listOfBooks.checkout("Y");
        assertEquals(false, selectedBook.getIsAvailable());
    }
}