package org.solvd.company.mybatis;

import org.apache.ibatis.annotations.Param;
import org.solvd.company.domain.company.Address;

import java.util.List;
import java.util.Optional;

public interface AddressMapper {

    void insert(@Param("address") Address address, @Param("officeId") Long officeId);

    Optional<Address> selectById(Long id);

    List<Address> selectAll();

    void update(Address budget);

    void delete(Long id);

}
