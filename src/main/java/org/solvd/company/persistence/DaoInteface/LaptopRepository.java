package org.solvd.company.persistence.DaoInteface;

import org.solvd.company.domain.company.Address;
import org.solvd.company.domain.equipment.Laptop;

import java.util.List;

public interface LaptopRepository {

    void  create(Laptop laptop, Long employeeId);
    void  update(Laptop laptop);
    Laptop  get( Long id);
    void  delete(Laptop laptop);
    List<Laptop> readAll();

}
