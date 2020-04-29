package com.genpact.library.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.library.beans.LibraryDto;
import com.genpact.library.service.LibraryService;

@RestController
@RequestMapping("/rest/api/v1/details")
public class LibraryRestController {

	@Autowired
	private LibraryService libraryService;

	public LibraryService getLibraryService() {
		return libraryService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<LibraryDto>> getAllLibraries() {
		List<LibraryDto> librarys = getLibraryService().getAllLibraries();
		return new ResponseEntity<List<LibraryDto>>(librarys, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addLibrary(@RequestBody LibraryDto aLibrary) {
		getLibraryService().addLibrary(aLibrary);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
