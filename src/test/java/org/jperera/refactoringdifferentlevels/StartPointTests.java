package org.jperera.refactoringdifferentlevels;

import org.jperera.refactoringdifferentlevels.testhelpers.PictureEndpoints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.jperera.refactoringdifferentlevels.testhelpers.PictureRequestBuilder.aRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class StartPointTests {

    @Autowired
    private MockMvc mockMvc;
    private PictureEndpoints endpoints;

    @BeforeEach
    public void setUp() {
        this.endpoints = new PictureEndpoints(mockMvc, "v1");
    }

	@Test
	void getOnePreviousCreatedPicture() throws Exception {
        Integer pictureId = endpoints.createPicture(
                aRequest()
                        .with("position", 1)
                        .with("title", "Mi primera foto")
                        .with("url", "https://images-eu.ssl-images-amazon.com/images/I/51ttgxwzArL._SX260_.jpg"));

        endpoints.getPicture(pictureId)
                .andExpect(jsonPath("$.id", is(pictureId)))
                .andExpect(jsonPath("$.position", is(1)))
                .andExpect(jsonPath("$.title", is("Mi primera foto")))
                .andExpect(jsonPath("$.url", is("https://images-eu.ssl-images-amazon.com/images/I/51ttgxwzArL._SX260_.jpg")));
    }

    @Test
    void modifyPreviousCreatedPicture() throws Exception {
        Integer pictureId = endpoints.createPicture(
                aRequest()
                        .with("position", 1)
                        .with("title", "Mi primera foto")
                        .with("url", "https://images-eu.ssl-images-amazon.com/images/I/51ttgxwzArL._SX260_.jpg"));

        endpoints.modifyPicture(pictureId,
            aRequest()
                .with("position", 1)
                .with("title", "Foto modificada")
                .with("url", "https://foto.mod/ificada.jpg")
        );

        endpoints.getPicture(pictureId)
                .andExpect(jsonPath("$.id", is(pictureId)))
                .andExpect(jsonPath("$.position", is(1)))
                .andExpect(jsonPath("$.title", is("Foto modificada")))
                .andExpect(jsonPath("$.url", is("https://foto.mod/ificada.jpg")));
    }

}
