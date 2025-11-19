package org.solvd.company.persistence;

import org.solvd.company.domain.equipment.Laptop;

import java.util.List;
import java.util.Optional;

public interface LaptopRepository {

    void create(Laptop laptop, Long employeeId);

    void update(Laptop laptop);

    Optional<Laptop> get(Long id);

    void delete(Laptop laptop);

    List<Laptop> readAll();

}
