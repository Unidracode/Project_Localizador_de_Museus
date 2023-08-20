package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * MusemController.
 */

@RestController
@RequestMapping("/museums")
public class MuseumController {

  private MuseumServiceInterface museumService;

  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * MuseumController.
   */

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MuseumDto museumCreate(@RequestBody MuseumCreationDto museumCreationDto) {
    Museum museumModel = ModelDtoConverter.dtoToModel(museumCreationDto);
    Museum museumCreated = museumService.createMuseum(museumModel);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museumCreated);
    return museumDto;
  }

  /**
   * MuseumController.
   */

  @GetMapping("/closest")
  @ResponseStatus(HttpStatus.OK)
  public MuseumDto closestMuseum(
      @RequestParam(name = "lat") Double lat,
      @RequestParam(name = "lng") Double lng,
      @RequestParam(name = "max_dist_km") Double dist
  ) {
    Coordinate coordinate = new Coordinate(lat, lng);
    Museum museum = museumService.getClosestMuseum(coordinate, dist);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museum);
    return museumDto;
  }
}