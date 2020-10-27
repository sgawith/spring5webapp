package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository,
        BookRepository bookRepository,
        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author terry = new Author("Terry", "Pratchett");
        authorRepository.save(terry);

        Book colour = new Book("The Colour Of Magic", "1234-1212-121");
        colour.getAuthors().add(terry);
        bookRepository.save(colour);

        Book fantastic = new Book("The Light Fantastic", "1234-1212-122");
        fantastic.getAuthors().add(terry);
        bookRepository.save(fantastic);

        terry.getBooks().add(colour);
        terry.getBooks().add(fantastic);
        authorRepository.save(terry);

        Author stephen = new Author("Stephen", "Baxter");
        authorRepository.save(stephen);

        Book cosmos = new Book("The Long Cosmos", "13210-1231");
        cosmos.getAuthors().add(terry);
        cosmos.getAuthors().add(stephen);
        bookRepository.save(cosmos);

        stephen.getBooks().add(cosmos);
        authorRepository.save(stephen);

        terry.getBooks().add(cosmos);
        authorRepository.save(terry);

        Publisher corgi = new Publisher("Corgi");
        publisherRepository.save(corgi);

        Publisher penguin = new Publisher("Penguin");
        publisherRepository.save(penguin);

        System.out.println("Authors known: " + authorRepository.count());
        System.out.println("Books known: " + bookRepository.count());
        System.out.println("Publishers known: " + publisherRepository.count());
    }
}
