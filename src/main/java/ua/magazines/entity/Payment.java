package ua.magazines.entity;

import java.util.Date;

public class Payment {
    private Integer id;
    private Integer userId;
    private Integer magazineId;
    private Date dateOfPayment;
    private Double sumPayment;

    public Payment(Integer id, Integer userId, Integer magazineId, Date dateOfPayment, Double sumPayment) {
        this.id = id;
        this.userId = userId;
        this.magazineId = magazineId;
        this.dateOfPayment = dateOfPayment;
        this.sumPayment = sumPayment;
    }

    public Payment(Integer userId, Integer magazineId, Date dateOfPayment, Double sumPayment) {
        this.userId = userId;
        this.magazineId = magazineId;
        this.dateOfPayment = dateOfPayment;
        this.sumPayment = sumPayment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(Integer magazineId) {
        this.magazineId = magazineId;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Double getSumPayment() {
        return sumPayment;
    }

    public void setSumPayment(Double sumPayment) {
        this.sumPayment = sumPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;

        if (getId() != null ? !getId().equals(payment.getId()) : payment.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(payment.getUserId()) : payment.getUserId() != null) return false;
        if (getMagazineId() != null ? !getMagazineId().equals(payment.getMagazineId()) : payment.getMagazineId() != null)
            return false;
        if (getDateOfPayment() != null ? !getDateOfPayment().equals(payment.getDateOfPayment()) : payment.getDateOfPayment() != null)
            return false;
        return getSumPayment() != null ? getSumPayment().equals(payment.getSumPayment()) : payment.getSumPayment() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getMagazineId() != null ? getMagazineId().hashCode() : 0);
        result = 31 * result + (getDateOfPayment() != null ? getDateOfPayment().hashCode() : 0);
        result = 31 * result + (getSumPayment() != null ? getSumPayment().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
               "id=" + id +
               ", userId=" + userId +
               ", magazineId=" + magazineId +
               ", dateOfPayment=" + dateOfPayment +
               ", sumPayment=" + sumPayment +
               '}';
    }
}
