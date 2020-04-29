package com.genpact.library.rest.controller.test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.genpact.library.beans.BookDto;
import com.genpact.library.rest.controller.BookRestController;
import com.genpact.library.service.BookService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class BookRestControllerTest {
	
	private static final String ACTION_ADD = "actionAdd";
	private static final String GET_BOOK_URL = "/rest/api/v1/details/book/get/1";
	private static final String ADD_BOOK_URL = "/rest/api/v1/details/book/add";
	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Tested
	private BookRestController bookController;

	@Injectable
	private BookService bookService;

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	public BookRestController getBookController() {
		if (null == bookController) {
			bookController = new BookRestController();
		}
		return bookController;
	}

	public BookService getBookService() {
		return bookService;
	}

	@Before
	public void beforeTest() {
		setMockMvc(MockMvcBuilders.standaloneSetup(getBookController()).build());
	}

	@Test
	public void testGetBookByLibrary_success() throws Exception {
		int libraryId = 1;
		List<BookDto> books = new ArrayList<>();
		BookDto book1 = new BookDto(1,"test name","test author","12345",1);
		books.add(book1);

		new Expectations() {
			{
				getBookService().getAllBooks(libraryId);
				result = books;
			}
		};
		String expectedResponse = "[{\"action\":null,\"bookId\":1,\"bookName\":\"test name\",\"bookAuthor\":\"test author\",\"bookIsbn\":\"12345\",\"libraryId\":1}]";
		MvcResult response = mockMvc.perform(
				MockMvcRequestBuilders.get(GET_BOOK_URL))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals("Response json", expectedResponse, response.getResponse().getContentAsString());
	}
	
	
	@Test
	public void testAddBook_success() throws Exception {
		BookDto book1 = new BookDto(1,"test name","test author","12345",1);
		book1.setAction(ACTION_ADD);
		// Convert to JSON
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(book1);

		new Expectations() {
			{
				getBookService().addBook((BookDto) any);
			}
		};

		getMockMvc().perform(
				MockMvcRequestBuilders.post(ADD_BOOK_URL).contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
	}
}
