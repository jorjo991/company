package org.solvd.company.persistence;

import org.apache.ibatis.annotations.Param;
import org.solvd.company.domain.equipment.Laptop;

import java.util.List;
import java.util.Optional;

public interface LaptopRepository {

    void create(@Param("laptop") Laptop laptop, @Param("employeeId") Long employeeId);

    void update(Laptop laptop);

    Optional<Laptop> get(Long id);

    void delete(Long id);

    List<Laptop> readAll();

}
