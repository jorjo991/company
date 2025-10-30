package org.solvd.company.budget;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Budget {

    private Long budgetID;
    private Double totalAmount;
    private Double spent;
    private String belongs;

    public Budget(Long budgetID, Double totalAmount, Double spent, String belongs) {
        this.budgetID = budgetID;
        this.totalAmount = totalAmount;
        this.spent = spent;
        this.belongs = belongs;
    }

    public Budget() {
    }

    @Override
    public String toString() {
        return "Budget{" +
                "BudgetID=" + budgetID +
                ", totalAmount=" + totalAmount +
                ", spent=" + spent +
                ", Belongs='" + belongs + '\'' +
                '}';
    }

    public Long getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(Long budgetID) {
        this.budgetID = budgetID;
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
