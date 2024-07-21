package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LibraryManagement");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        // Yazarlar oluşturuluyor
        Author author1 = new Author();
        author1.setName("J.K. Rowling");
        author1.setBirthDate(LocalDate.of(1965, 7, 31));
        author1.setCountry("United Kingdom");

        Author author2 = new Author();
        author2.setName("George Orwell");
        author2.setBirthDate(LocalDate.of(1903, 6, 25));
        author2.setCountry("India");

        // Yayınevleri oluşturuluyor
        Publisher publisher1 = new Publisher();
        publisher1.setName("Bloomsbury Publishing");
        publisher1.setEstablishmentYear(1986);
        publisher1.setAddress("London, UK");

        Publisher publisher2 = new Publisher();
        publisher2.setName("Secker and Warburg");
        publisher2.setEstablishmentYear(1936);
        publisher2.setAddress("London, UK");

        // Kategoriler oluşturuluyor
        Category category1 = new Category();
        category1.setName("Fantasy");
        category1.setDescription("Fantasy books include magical and unreal elements.");

        Category category2 = new Category();
        category2.setName("Dystopian");
        category2.setDescription("Dystopian books involve a society that is typically repressive and controlled.");

        // Kitaplar oluşturuluyor
        Book book1 = new Book();
        book1.setName("Harry Potter and the Sorcerer's Stone");
        book1.setPublicationYear(1997);
        book1.setStock(30);
        book1.setAuthor(author1);
        book1.setPublisher(publisher1);
        book1.setCategoryList(Arrays.asList(category1));

        Book book2 = new Book();
        book2.setName("1984");
        book2.setPublicationYear(1949);
        book2.setStock(20);
        book2.setAuthor(author2);
        book2.setPublisher(publisher2);
        book2.setCategoryList(Arrays.asList(category2));

        // Veritabanına kaydet
        entityManager.persist(author1);
        entityManager.persist(author2);
        entityManager.persist(publisher1);
        entityManager.persist(publisher2);
        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.persist(book1);
        entityManager.persist(book2);

        transaction.commit();

        transaction.begin();

        // Ödünç alma işlemleri oluşturuluyor
        BookBorrowing borrowing1 = new BookBorrowing();
        borrowing1.setBorrowerName("Mehmet Yılmaz");
        borrowing1.setBorrowingDate(LocalDate.of(2024, 7, 10));
        borrowing1.setReturnDate(null);  // Başlangıçta null, kitap iade edildiğinde güncellenir
        borrowing1.setBook(book1);  // "Harry Potter and the Sorcerer's Stone"

        BookBorrowing borrowing2 = new BookBorrowing();
        borrowing2.setBorrowerName("Ahmet Taş");
        borrowing2.setBorrowingDate(LocalDate.of(2024, 7, 12));
        borrowing2.setReturnDate(null);
        borrowing2.setBook(book2);  // "1984"

        // Ödünç alma işlemlerini veritabanına kaydet
        entityManager.persist(borrowing1);
        entityManager.persist(borrowing2);

        transaction.commit();  // İkinci transaction'ı commit ediyoruz


    }
}