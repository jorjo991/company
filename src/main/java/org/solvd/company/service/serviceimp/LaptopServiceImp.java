package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.equipment.Laptop;
import org.solvd.company.persistence.LaptopRepository;
import org.solvd.company.persistence.impl.LaptopRepositoryImp;
import org.solvd.company.service.LaptopService;

import java.util.List;

public class LaptopServiceImp implements LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopServiceImp() {
        this.laptopRepository = new LaptopRepositoryImp();
    }

    @Override
    public void create(Laptop laptop, Long employeeId) {

        laptopRepository.create(laptop, employeeId);
    }

    @Override
    public Laptop getLaptopById(Long id) {
        return laptopRepository.get(id).
                orElseThrow(() -> new RuntimeException("Laptop not found with id " + id));

    }

    @Override
    public void updateLaptop(Laptop laptop) {
        laptopRepository.update(laptop);
    }

    @Override
    public void deleteLaptop(Laptop laptop) {
        laptopRepository.delete(laptop);
    }

    @Override
    public List<Laptop> getAllLaptops() {
        return laptopRepository.readAll();
    }
}
