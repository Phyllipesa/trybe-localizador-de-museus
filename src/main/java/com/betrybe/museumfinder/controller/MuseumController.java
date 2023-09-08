package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Museum Controller.
 */

@RestController
@RequestMapping("/museums")
public class MuseumController {
  private final MuseumServiceInterface service;

  /**
   * Recebe um bean do tipo MuseumServiceInterface por injeção de dependência.
   *
   * @param service service.
   */
  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * Get getClosestMuseum.
   *
   * @param latitude mapeamento do RequestParam "lat".
   * @param longitude mapeamento do RequestParam "lng".
   * @param maxDistanceKm mapeamento do RequestParam "max_dist_km".
   * @return dados do museum mais proximo encontrado.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam("lat") Double latitude,
      @RequestParam("lng") Double longitude,
      @RequestParam("max_dist_km") Double maxDistanceKm
  ) {
    Coordinate coordinate = new Coordinate(latitude, longitude);
    Museum closestMuseum = service.getClosestMuseum(coordinate, maxDistanceKm);
    MuseumDto closestMuseumDto = ModelDtoConverter.modelToDto(closestMuseum);
    return ResponseEntity.ok().body(closestMuseumDto);
  }

  /**
   * Get getMuseumById.
   *
   * @param id do museum.
   * @return dados do museum referente ao ID informado.
   */
  @GetMapping("/{id}")
  public ResponseEntity<MuseumDto> getMuseum(@PathVariable Long id) {
    Museum museum = service.getMuseum(id);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.ok().body(museumDto);
  }

  /**
   * Post registerNewMuseum.
   *
   * @param museumDto informações do requestBody.
   * @return dados do museum registrado.
   */
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<MuseumDto> registerNewMuseum(@RequestBody MuseumCreationDto museumDto) {
    Museum newMuseum = ModelDtoConverter.dtoToModel(museumDto);
    Museum museumCreated = service.createMuseum(newMuseum);
    MuseumDto museumCreatedDto = ModelDtoConverter.modelToDto(museumCreated);
    return ResponseEntity.status(HttpStatus.CREATED).body(museumCreatedDto);
  }
}
