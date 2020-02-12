package org.jperera.refactoringdifferentlevels.testhelpers;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.jperera.refactoringdifferentlevels.testhelpers.PictureResponseReader.reader;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PictureEndpoints {

    private MockMvc mockMvc;
    private String version;

    public PictureEndpoints(MockMvc mockMvc, String version) {
        this.mockMvc = mockMvc;
        this.version = version;
    }

    public ResultActions getAllPictures() throws Exception {
        return mockMvc.perform(
                get("/" + version +"/pictures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        ).andDo(print());
    }

    public ResultActions getPicture(Integer id) throws Exception {
        return mockMvc.perform(
                get("/" + version +"/pictures/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)

        ).andDo(print());
    }

    public Integer createPicture(PictureRequestBuilder request) throws Exception {
        String response = mockMvc.perform(
                post("/" + version +"/pictures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(request.json())
        ).andDo(print())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andReturn().getResponse().getContentAsString();

        return reader(response).id();
    }

    public ResultActions modifyPicture(Integer id, PictureRequestBuilder request) throws Exception {
        return mockMvc.perform(
                put("/" + version +"/pictures/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(request.json())
        ).andDo(print());
    }
}
