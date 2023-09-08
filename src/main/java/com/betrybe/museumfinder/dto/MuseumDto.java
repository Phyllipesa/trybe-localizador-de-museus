package com.betrybe.museumfinder.dto;

import com.betrybe.museumfinder.model.Coordinate;
/**
 * Create the Museum dto.
 *
 * @param id museum id
 * @param name museum name
 * @param description museum description
 * @param address museum address
 * @param collectionType museum collectionType
 * @param subject museum subject
 * @param url museum url
 * @param coordinate museum coordinates
 */

public record MuseumDto(
    Long id,
    String name,
    String description,
    String address,
    String collectionType,
    String subject,
    String url,
    Coordinate coordinate
) {
}
