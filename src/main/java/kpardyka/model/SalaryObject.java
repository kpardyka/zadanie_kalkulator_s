package kpardyka.model;

public class SalaryObject {
    private long id;
    private double dailySalary;

    public SalaryObject(long id, double dailySalary) {
        this.id = id;
        this.dailySalary = dailySalary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(double dailySalary) {
        this.dailySalary = dailySalary;
    }

}
