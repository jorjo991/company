package org.solvd.company.domain.budget;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Budget {

    private Long id;
    private Double totalAmount;
    private Double spent;
    private String description;

    public Budget(Long budgetID, Double totalAmount, Double spent, String description) {
        this.id = budgetID;
        this.totalAmount = totalAmount;
        this.spent = spent;
        this.description = description;
    }

    public Budget() {
    }

    @Override
    public String toString() {
        return "Budget{" +
                "BudgetID=" + id +
                ", totalAmount=" + totalAmount +
                ", spent=" + spent +
                ", Belongs='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setBudgetID(Long budgetID) {
        this.id = budgetID;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getSpent() {
        return spent;
    }

    public void setSpent(Double spent) {
        this.spent = spent;
    }

    public String getBelongs() {
        return description;
    }

    public void setBelongs(String belongs) {
        this.description = belongs;
    }
}
