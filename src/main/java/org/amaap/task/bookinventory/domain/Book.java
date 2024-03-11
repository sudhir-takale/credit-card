package org.amaap.task.bookinventory.domain;

import java.util.Objects;

public class Book {

    private int bookId;
    private String bookName;
    private String author;
    private String publisher;


    private String category;
    private int quantity;
    private double price;

    public Book(int bookId, String bookName, String author, String publisher, String category, int quantity, double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }


    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && quantity == book.quantity && Double.compare(price, book.price) == 0 && Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher) && Objects.equals(category, book.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, author, publisher, category, quantity, price);
    }
}
