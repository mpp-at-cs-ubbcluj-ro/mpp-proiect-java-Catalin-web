package com.example.tripService.Repository;


import com.example.tripService.Domain.Entity;

import java.util.List;

public interface Repository<ID,entity extends Entity<ID>> {
    void adauga(entity entity);
    void sterge(entity entity);
    entity cautaId(ID id);
    List<entity> getAll();
    void update(entity entitate,entity nouaEntitate);
}
