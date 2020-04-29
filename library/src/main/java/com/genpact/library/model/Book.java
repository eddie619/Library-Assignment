package com.genpact.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {

	public Book() {
	}

	

	public Book(String bookName, String bookAuthor, String bookIsbn, int libraryId) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookIsbn = bookIsbn;
		this.libraryId = libraryId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;

	@Column(name = "bookname")
	private String bookName;

	@Column(name = "bookauthor")
	private String bookAuthor;

	@Column(name = "bookisbn")
	private String bookIsbn;

	@Column(name = "libraryId")
	private int libraryId;

	public int getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookAuthor == null) ? 0 : bookAuthor.hashCode());
		result = prime * result + bookId;
		result = prime * result + libraryId;
		result = prime * result + ((bookIsbn == null) ? 0 : bookIsbn.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		if (bookAuthor == null) {
			if (other.bookAuthor != null) {
				return false;
			}
		} else if (!bookAuthor.equals(other.bookAuthor)) {
			return false;
		}
		if (bookId != other.bookId) {
			return false;
		}
		if (libraryId != other.libraryId) {
			return false;
		}
		if (bookIsbn == null) {
			if (other.bookIsbn != null) {
				return false;
			}
		} else if (!bookIsbn.equals(other.bookIsbn)) {
			return false;
		}
		if (bookName == null) {
			if (other.bookName != null) {
				return false;
			}
		} else if (!bookName.equals(other.bookName)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [bookId=").append(bookId).append(", bookName=").append(bookName).append(", bookAuthor=")
				.append(bookAuthor).append(", bookIsbn=").append(bookIsbn).append(", libraryId=").append(libraryId)
				.append("]");
		return builder.toString();
	}

}
