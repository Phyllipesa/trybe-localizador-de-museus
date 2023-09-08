package com.betrybe.museumfinder.solution;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("Testando a CollectionService")
public class CollectionTypeServiceTest {
  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  CollectionTypeService collectionService;

  @Test
  @DisplayName("Testando o metodo countByCollectionTypes")
  public void collectioTypeTest() throws  Exception {
    // Arrange
    String[] collectionTypes = new String[]{"historia"};
    CollectionTypeCount collectionMock = new CollectionTypeCount(collectionTypes, 241);

    Mockito.when(museumFakeDatabase.countByCollectionType("historia"))
        .thenReturn(241L);

    //Act
    CollectionTypeCount collection = collectionService.countByCollectionTypes("historia");

    // Assert
    assertNotNull(collection);

    assertEquals(collectionMock.count(), collection.count());
    assertEquals(collectionMock.collectionTypes()[0], collection.collectionTypes()[0]);

    Mockito.verify(museumFakeDatabase).countByCollectionType("historia");

  }

}
