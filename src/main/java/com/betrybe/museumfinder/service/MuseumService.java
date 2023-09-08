package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Museum Service.
 */

@Service
public class MuseumService implements MuseumServiceInterface {
  private final MuseumFakeDatabase museumDb;

  /**
   * Recebe um bean do tipo MuseumFakeDatabase por injeção de dependência.
   *
   * @param museumDb database.
   */
  @Autowired
  public MuseumService(MuseumFakeDatabase museumDb) {
    this.museumDb = museumDb;
  }

  /**
   * getClosestMuseum.
   *
   * @param coordinate contem a latitude e longitute.
   * @param maxDistance a distância máxima em quilômetros.
   * @return o museu caso encontrado.
   */
  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    if (!CoordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> museum = museumDb.getClosestMuseum(coordinate, maxDistance);

    if (museum.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return museum.get();
  }


  /**
   * createMuseum.
   *
   * @param museum recebe os dados de um museum.
   * @return dados do museum inserido no DB
   */
  @Override
  public Museum createMuseum(Museum museum) {
    if (!CoordinateUtil.isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException();
    }
    return museumDb.saveMuseum(museum);
  }

  /**
   * getMuseum busca por uma museum com o ID especificado.
   *
   * @param id O id da museum.
   * @return museum com o ID especificado.
   */
  @Override
  public Museum getMuseum(Long id) {
    Optional<Museum> optionalMuseum = museumDb.getMuseum(id);

    if (optionalMuseum.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return optionalMuseum.get();
  }
}
