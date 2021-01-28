package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repos.AuthorRepo;
import guru.springframework.spring5webapp.repos.BookRepo;
import guru.springframework.spring5webapp.repos.PublisherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final PublisherRepo publisherRepo;

    public BootstrapData(AuthorRepo authorRepo, BookRepo bookRepo, PublisherRepo publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Author stephen = new Author("Stephen", "King");
        Book book1 = new Book("The Shining", "1234");
        stephen.getBooks().add(book1);
        book1.getAuthors().add(stephen);

        authorRepo.save(stephen);
        bookRepo.save(book1);

        Author nabokov = new Author("Vladimir", "Nabokov");
        Book book2 = new Book("Lolita", "1235");
        nabokov.getBooks().add(book2);
        book2.getAuthors().add(nabokov);

        authorRepo.save(nabokov);
        bookRepo.save(book2);

        Publisher publisher = new Publisher();
        publisher.setName("Some publisher");
        publisher.setCity("Boston");
        publisher.setState("Washington");

        publisherRepo.save(publisher);

        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);
        publisherRepo.save(publisher);

        book2.setPublisher(publisher);
        publisher.getBooks().add(book2);
        publisherRepo.save(publisher);

        System.out.println("Nr of books: " + bookRepo.count());
        System.out.println("Nr of books assigned to the publisher: " + publisher.getBooks().size());


    }
}

