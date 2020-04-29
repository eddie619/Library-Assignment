package com.genpact.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.library.beans.BookDto;
import com.genpact.library.dao.BooksDao;
import com.genpact.library.model.Book;

@Service
@SuppressWarnings("all")
public class BookService {

	@Autowired
	private BooksDao booksDao;

	private Function<BookDto, Book> mapDtoToModel = aBook -> new Book(aBook.getBookName(), aBook.getBookAuthor(),
			aBook.getBookIsbn(),aBook.getLibraryId());

	public Function<BookDto, Book> getMapDtoToModel() {
		return mapDtoToModel;
	}

	public BooksDao getBooksDao() {
		return booksDao;
	}

	public void addBook(BookDto aBook) {
		getBooksDao().save(getMapDtoToModel().apply(aBook));
	}

	public List<BookDto> getAllBooks(int libraryId) {
		List<Book> books = (List<Book>) getBooksDao().getAll(libraryId);
		List<BookDto> booksDtoList = books.stream()
				.map(object -> new BookDto(object.getBookId(),object.getBookName(), object.getBookAuthor(), object.getBookIsbn(),object.getLibraryId()))
				.collect(Collectors.toList());
		return booksDtoList;
	}
	
	public void updateBook(BookDto aBook) {
		Book book = getMapDtoToModel().apply(aBook);
		book.setBookId(aBook.getBookId());
		getBooksDao().update(book);
	}
}
