package org.jperera.refactoringdifferentlevels;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.jperera.refactoringdifferentlevels.PictureRequestBuilder.aRequest;
import static org.jperera.refactoringdifferentlevels.PictureResponseReader.reader;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class RefactoringDifferentLevelsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getOnePicture() throws Exception {
		String response = mockMvc.perform(
				post("/pictures")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(aRequest()
								.with("position", 1)
								.with("title", "Mi primera foto")
								.with("url", "https://images-eu.ssl-images-amazon.com/images/I/51ttgxwzArL._SX260_.jpg")
								.json())
		    ).andDo(print())
            .andExpect(jsonPath("$.id", is(notNullValue())))
            .andReturn().getResponse().getContentAsString();

		Integer pictureId = reader(response).id();

		mockMvc.perform(
		        get("/pictures/" + pictureId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)

            ).andDo(print())
            .andExpect(jsonPath("$.id", is(pictureId)))
            .andExpect(jsonPath("$.position", is(1)))
            .andExpect(jsonPath("$.title", is("Mi primera foto")))
            .andExpect(jsonPath("$.url", is("https://images-eu.ssl-images-amazon.com/images/I/51ttgxwzArL._SX260_.jpg")));
    }

}
