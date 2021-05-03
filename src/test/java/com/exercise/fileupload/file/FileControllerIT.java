package com.exercise.fileupload.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileControllerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldAcceptDocumentSearchRequest() throws Exception {
        mvc.perform(
                get("/files/{tagSearchQuery}/page", "+tag -otherTag +thirdTag")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    void shouldInvalidateDocumentSearchRequest() throws Exception {
        // given
        String invalidTag1 = "+ta+g,-otherTag";
        String invalidTag2 = "otherInvalid";

        // when then
        mvc.perform(
                get("/files/{tagSearchQuery}/page", format("%s +thirdTag %s", invalidTag1, invalidTag2))
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException())
                    .hasMessageContainingAll(invalidTag1, invalidTag2)
                );

    }
}