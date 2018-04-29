package kpardyka.exchangeRates;

class Rate {
    private String no;
    private String effectiveDate;
    private Double mid;

    String getNo() {
        return no;
    }

    Double getMid() {
        return mid;
    }

    String getEffectiveDate() {
        return effectiveDate;
    }

    void setNo(String no) {
        this.no = no;
    }

    void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    void setMid(Double mid) {
        this.mid = mid;
    }
}
