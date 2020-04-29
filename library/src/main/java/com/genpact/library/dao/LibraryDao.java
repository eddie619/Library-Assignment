package com.genpact.library.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.genpact.library.model.Library;

@Service
@Transactional
public class LibraryDao extends AbstractDao {

	public Serializable save(Library libraryDetail) {
		Session session = getSession();
		Serializable savedObject = session.save(libraryDetail);
		return savedObject;
	}

	public List<?> getAll() {
		Session session = getSession();
		Query query = session.createQuery("from Library");
		List<?> libraries = query.list();
		return libraries;
	}
}
