package kpardyka.model;

import javax.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String code;

    @Column(nullable = false)
    private int tax;

    @Column(nullable = false)
    private double costOfGettingIncome;

    public Country() {
    }

    public Country(String name, int tax, double costOfGettingIncome) {
        this.name = name;
        this.tax = tax;
        this.costOfGettingIncome = costOfGettingIncome;
    }

    public Country(String name, String code, int tax, double costOfGettingIncome) {
        this.name = name;
        this.code = code;
        this.tax = tax;
        this.costOfGettingIncome = costOfGettingIncome;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", tax=" + tax +
                ", costOfGettingIncome=" + costOfGettingIncome +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getTax() {
        return tax;
    }

    public double getCostOfGettingIncome() {
        return costOfGettingIncome;
    }
}