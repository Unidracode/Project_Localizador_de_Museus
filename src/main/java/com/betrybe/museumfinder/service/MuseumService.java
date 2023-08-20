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
 * MuseumService.
 */

@Service
public class MuseumService implements MuseumServiceInterface {

  private MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    if (CoordinateUtil.isCoordinateValid(coordinate) == false) {
      throw new InvalidCoordinateException();
    }
    Optional<Museum> findMuseumClosest = museumFakeDatabase.getClosestMuseum(coordinate,
        maxDistance);
    if (findMuseumClosest.isPresent()) {
      return findMuseumClosest.get();
    }
    throw new MuseumNotFoundException();
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (CoordinateUtil.isCoordinateValid(museum.getCoordinate())) {
      return museumFakeDatabase.saveMuseum(museum);
    }
    throw new InvalidCoordinateException();
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}