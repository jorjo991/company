package org.solvd.company.service;

import org.solvd.company.domain.equipment.Laptop;
import org.solvd.company.persistence.impl.LaptopRepositoryImp;
import org.solvd.company.service.interfcae.LaptopService;

import java.util.List;

public class LaptopServiceImp implements LaptopService {

    private LaptopRepositoryImp laptopRepositoryImp;

    public LaptopServiceImp(LaptopRepositoryImp laptopRepositoryImp) {
        this.laptopRepositoryImp = laptopRepositoryImp;
    }

    @Override
    public void create(Laptop laptop, Long employeeId) {

        laptopRepositoryImp.create(laptop, employeeId);
    }

    @Override
    public Laptop getLaptopById(Long id) {
        Laptop laptop = laptopRepositoryImp.get(id);
        if (laptop == null) {
            throw new RuntimeException("Laptop not found");
        }
        return laptop;
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
