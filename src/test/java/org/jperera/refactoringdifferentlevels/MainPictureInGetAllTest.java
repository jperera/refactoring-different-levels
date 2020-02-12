package org.jperera.refactoringdifferentlevels;

import org.jperera.refactoringdifferentlevels.testhelpers.PictureEndpoints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.jperera.refactoringdifferentlevels.testhelpers.PictureRequestBuilder.aRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
public class MainPictureInGetAllTest {

    @Autowired
    private MockMvc mockMvc;
    private PictureEndpoints endpoints;

    @BeforeEach
    public void setUp() {
        String version = null;  // ¿What version?
        this.endpoints = new PictureEndpoints(mockMvc, version);
    }

    @Test
    void uniquePictureIsMainPicture() throws Exception {
        Integer pictureId = endpoints.createPicture(
                aRequest()
                .with("position", 1)
                .with("title", "Mi primera foto")
                .with("url", "https://images-eu.ssl-images-amazon.com/images/I/51ttgxwzArL._SX260_.jpg"));

        endpoints.getAllPictures()
            .andExpect(jsonPath("$.mainPictureId", is(pictureId)));
    }


}
