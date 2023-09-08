package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;
/**
 * Create the Dto layer MuseumCreationDto.
 *
 * @param name name from museum.
 * @param description description from museum.
 * @param address museum's address
 * @param collectionType type of museum(art, history, etc)
 * @param subject main subject from museum
 * @param url url
 * @param coordinate coordinates from museum
 */

public record MuseumCreationDto(
    String name,
    String description,
    String address,
    String collectionType,
    String subject,
    String url,
    Coordinate coordinate
) {}
