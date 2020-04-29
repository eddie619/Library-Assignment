package com.genpact.library.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.library.beans.BookDto;
import com.genpact.library.service.BookService;

@RestController
@RequestMapping("/rest/api/v1/details")
public class BookRestController {

	@Autowired
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	@GetMapping("/book/get/{libraryId}")
	public ResponseEntity<List<BookDto>> getBookByLibrary(@PathVariable int libraryId) {
		List<BookDto> books = getBookService().getAllBooks(libraryId);
		return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
	}

	@PostMapping("/book/add")
	public ResponseEntity<?> addBook(@RequestBody BookDto book) {
		if (book.getAction() != null && book.getAction().equals("actionAdd")) {
			getBookService().addBook(book);
		} else {
			bookService.updateBook(book);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
