package org.solvd.company.service.interfcae;

import org.solvd.company.domain.equipment.Laptop;

import java.util.List;

public interface LaptopService {

    void create(Laptop laptop, Long employeeId);

    Laptop getLaptopById(Long id);

    void updateLaptop(Laptop laptop);

    void deleteLaptop(Laptop laptop);

    List<Laptop> getAllLaptops();
}
