package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
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
 *  Classe de teste para Museum Controller
 */

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testando a MuseumController")
public class MuseumControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MuseumService museumService;

  private Museum createMuseum() {
    Coordinate coordinate = new Coordinate(-22.8093976, -43.3877343 );

    Museum museum = new Museum();
    museum.setId(2L);
    museum.setName("Museu Memorial Iyá Davina");
    museum.setDescription("Museu Memorial Iyá Davina");
    museum.setAddress("Rua General Olímpio da Fonseca, 380 , Parque São Nicolau, 25530-140, São João de Meriti, RJ");
    museum.setCollectionType("Imagem e Som, História, Antropologia e Etnografia");
    museum.setSubject("Antropologia e arqueologia");
    museum.setUrl("http://www.ileomoluoxum.org");
    museum.setCoordinate(coordinate);

    return museum;
  }

  @Test
  @DisplayName("Testando a rota GET id")
  public void getMuseumById() throws Exception {
    // Arrange
    Museum museum = createMuseum();

    Mockito.when(museumService.getMuseum(2L))
        .thenReturn(museum);

    // Act and Assert

    Museum result = museumService.getMuseum(2L);

    mockMvc.perform(get("/museums/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(2L))
        .andExpect(jsonPath("$.name").value(result.getName()))
        .andExpect(jsonPath("$.description").value(result.getDescription()))
        .andExpect(jsonPath("$.address").value(result.getAddress()))
        .andExpect(jsonPath("$.collectionType").value(result.getCollectionType()))
        .andExpect(jsonPath("$.subject").value(result.getSubject()))
        .andExpect(jsonPath("$.url").value(result.getUrl()))
        .andExpect(jsonPath("$.coordinate.latitude").value(result.getCoordinate().latitude()))
        .andExpect(jsonPath("$.coordinate.longitude").value(result.getCoordinate().longitude()));
  }
}
