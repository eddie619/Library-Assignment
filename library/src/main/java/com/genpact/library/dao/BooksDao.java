package com.genpact.library.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.genpact.library.model.Book;

@Service
@Transactional
public class BooksDao extends AbstractDao {

	public void save(Book book) {
		Session session = getSession();
		session.save(book);
	}

	public List<?> getAll(int libraryId) {
		Session session = getSession();
		Criteria crit = session.createCriteria(Book.class);
		crit.add(Restrictions.eq("libraryId", libraryId));
		List<?> books = crit.list();
		return books;
	}
	
	public void update(Book book) {
		Session session = getSession();
		Book fromDb = (Book) session.load(Book.class, book.getBookId());
		
		fromDb.setBookName(book.getBookName());
		fromDb.setBookAuthor(book.getBookAuthor());
		fromDb.setBookIsbn(book.getBookIsbn());
		Transaction tx = session.beginTransaction();
		session.update(fromDb);
		tx.commit();
	}
	
	 
}
