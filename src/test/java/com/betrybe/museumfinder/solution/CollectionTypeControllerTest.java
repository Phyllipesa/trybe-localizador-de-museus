package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 *  Classe de teste para Controller
 */

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testando a CollectionController")
public class CollectionTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CollectionTypeService collectionService;

  @Test
  @DisplayName("Testando a rota GET")
  public void getCollection() throws Exception {
    // Arrange
    String[] collectionTypes = new String[]{"historia"};
    CollectionTypeCount collectionMock = new CollectionTypeCount(collectionTypes, 492);

    Mockito.when(
        collectionService.countByCollectionTypes("historia")
    ).thenReturn(
        collectionMock
    );

    // Act and Assert
    mockMvc.perform(get("/collections/count/historia"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.count").value(492))
        .andExpect(jsonPath("$.collectionTypes").value(collectionTypes[0]));
  }
}
