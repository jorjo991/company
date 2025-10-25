package org.solvd.company.budget;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Salary {

    private Double amount;
    private Double bonus;
    private Integer cutPercentage;

    public Salary(Double amount, Double bonus, Integer cutPercentage) {
        this.amount = amount;
        this.bonus = bonus;
        this.cutPercentage = cutPercentage;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "amount=" + amount +
                ", bonus=" + bonus +
                ", cutPercentage=" + cutPercentage +
                '}';
    }

    public Salary() {

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Integer getCutPercentage() {
        return cutPercentage;
    }

    public void setCutPercentage(Integer cutPercentage) {
        this.cutPercentage = cutPercentage;
    }


}
