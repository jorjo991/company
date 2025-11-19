package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.equipment.Laptop;
import org.solvd.company.persistence.impl.LaptopRepositoryImp;
import org.solvd.company.service.LaptopService;

import java.util.List;

public class LaptopServiceImp implements LaptopService {

    private final LaptopRepositoryImp laptopRepositoryImp;

    public LaptopServiceImp(LaptopRepositoryImp laptopRepositoryImp) {
        this.laptopRepositoryImp = laptopRepositoryImp;
    }

    @Override
    public void create(Laptop laptop, Long employeeId) {

        laptopRepositoryImp.create(laptop, employeeId);
    }

    @Override
    public Laptop getLaptopById(Long id) {
        return laptopRepositoryImp.get(id).orElseThrow(() -> new RuntimeException("Laptop not found with id " + id));

    }

    @Override
    public void updateLaptop(Laptop laptop) {
        laptopRepositoryImp.update(laptop);
    }

    @Override
    public void deleteLaptop(Laptop laptop) {
        laptopRepositoryImp.delete(laptop);
    }

    @Override
    public List<Laptop> getAllLaptops() {
        return laptopRepositoryImp.readAll();
    }
}
