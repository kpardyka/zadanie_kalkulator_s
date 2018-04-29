package kpardyka.api.model;

public class Rate {
    private String no;
    private String effectiveDate;
    private Double mid;

    public String getNo() {
        return no;
    }

    public Double getMid() {
        return mid;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }
}
