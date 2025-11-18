package org.solvd.company.persistence.DaoInteface;

import java.util.List;

public interface CompanyRepository<T>{

    void  create(T t);
    void  update(T t);
    T  get( Long id);
    void  delete(T t);
    List<T> readAll();

}
