package ua.com.alevel.impl;

import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookAuthorDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.ConsoleService;
import ua.com.alevel.util.ReadParametersUtil;

import java.util.HashSet;
import java.util.Set;

public class AuthorConsoleServiceImpl implements ConsoleService<Author> {
    private final AuthorDao authorDao = new AuthorDao();
    private final BookAuthorDao bookAuthorDao = new BookAuthorDao();

    @Override
    public Author create() {
        String firstName;
        String lastName;
        System.out.println("\nCreating an author: ");
        System.out.print("Please enter a first name of the author: ");
        firstName = ReadParametersUtil.readParams();
        System.out.print("Please enter a last name of the author: ");
        lastName = ReadParametersUtil.readParams();
        return new Author(firstName, lastName);
    }

    @Override
    public Author update() {
        String firstName;
        String authorId;
        String lastName;
        System.out.println("\nUpdating an author: ");
        authorId = getID();
        Author author = authorDao.find(authorId);
        if (author != null) {
            System.out.print("Please enter a first name of an author: ");
            firstName = ReadParametersUtil.readParams();
            System.out.print("Please enter a last name of a author: ");
            lastName = ReadParametersUtil.readParams();
            author.setFirstName(firstName);
            author.setLastName(lastName);
        }
        return author;
    }

    @Override
    public String delete() {
        System.out.println("\nDeleting an author: ");
        String authorId;
        authorId = getID();
        return authorId;
    }

    @Override
    public void findAll(Set<Author> values) {
        System.out.println("\nFinding all authors: ");
        for (Author a : values) {
            System.out.println("ID = " + a.getId() + ", First name = " + a.getFirstName() + ", Last name = " + a.getLastName());
        }
    }

    public void findById(Author author) {
        if (author != null)
            System.out.println("\nFirst name = " + author.getFirstName() + ", Last name = " + author.getLastName() + ", ID = " + author.getId());
    }

    public void findAuthorsByBook(Book book) {
        if (book == null) {
            System.out.println("\nthere is no such book");
            return;
        }
        Set<Author> authorsId = bookAuthorDao.findAuthorsByBook(book.getId());
        Set<Author> authors = new HashSet<>(authorsId);
        findAll(authors);
    }

    public String getID() {
        System.out.print("Please, enter an ID of author: ");
        return ReadParametersUtil.readParams();
    }

}
