package org.solvd.company.designPatterns.observer;

public interface SubjectCompany {
    void registerObserver(ClientObserver o);

    void removeObserver(ClientObserver o);

    void notifyObservers();
}
