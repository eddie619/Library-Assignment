package com.genpact.library.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.library.beans.LibraryDto;
import com.genpact.library.dao.LibraryDao;
import com.genpact.library.model.Library;

@Service
@SuppressWarnings("all")
public class LibraryService {

	@Autowired
	private LibraryDao libraryDao;

	private Function<LibraryDto, Library> mapDtoToModel = aLibrary -> new Library(aLibrary.getLibraryName());
//	private Function<List<Library>, List<LibraryDto>> mapModelToDto = aLibrary -> new Library(aLibrary.getLibraryName());

	public LibraryDao getLibraryDao() {
		return libraryDao;
	}

	public Function<LibraryDto, Library> getMapDtoToModel() {
		return mapDtoToModel;
	}

	public void addLibrary(LibraryDto aLibrary) {
		getLibraryDao().save(getMapDtoToModel().apply(aLibrary));

	}

	public List<LibraryDto> getAllLibraries() {
		List<Library> libraries = (List<Library>) getLibraryDao().getAll();
		List<LibraryDto> libraryDtoList = libraries.stream()
				.map(object -> new LibraryDto(object.getLibraryId(), object.getLibraryName()))
				.collect(Collectors.toList());
		return libraryDtoList;
	}

}
