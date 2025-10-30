package org.solvd.company.budget;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Budget {

    private Long id;
    private Double totalAmount;
    private Double spent;
    private String belongs;

    public Budget(Long budgetID, Double totalAmount, Double spent, String belongs) {
        this.id = budgetID;
        this.totalAmount = totalAmount;
        this.spent = spent;
        this.belongs = belongs;
    }

    public Budget() {
    }

    @Override
    public String toString() {
        return "Budget{" +
                "BudgetID=" + id +
                ", totalAmount=" + totalAmount +
                ", spent=" + spent +
                ", Belongs='" + belongs + '\'' +
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
        return belongs;
    }

    public void setBelongs(String belongs) {
        this.belongs = belongs;
    }
}
