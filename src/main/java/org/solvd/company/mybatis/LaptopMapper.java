package org.solvd.company.mybatis;

import org.apache.ibatis.annotations.Param;
import org.solvd.company.domain.equipment.Laptop;

import java.util.List;
import java.util.Optional;

public interface LaptopMapper {

    void insert(@Param("laptop") Laptop laptop, @Param("employeeId") Long employeeId);

    Optional<Laptop> selectById(Long id);

    List<Laptop> selectAll();

    void update(Laptop laptop);

    void delete(Long id);
}
