package org.solvd.company.domain.budget;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Salary {
    private Long id;
    private Double amount;
    private Double bonus;
    private Integer cutPercentage;

    public Salary(Long id,Double amount, Double bonus, Integer cutPercentage) {
        this.id=id;
        this.amount = amount;
        this.bonus = bonus;
        this.cutPercentage = cutPercentage;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", amount=" + amount +
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
