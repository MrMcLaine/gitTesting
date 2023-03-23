package ua.lesson5.domain;

import java.util.Date;

public class Bucket {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Date purchaseDate;

    public Bucket(Integer id, Integer userId, Integer productId, Date purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    public Bucket(Integer userId, Integer productId, Date purchaseDate) {
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bucket)) return false;

        Bucket bucket = (Bucket) o;

        if (getId() != null ? !getId().equals(bucket.getId()) : bucket.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(bucket.getUserId()) : bucket.getUserId() != null) return false;
        if (getProductId() != null ? !getProductId().equals(bucket.getProductId()) : bucket.getProductId() != null)
            return false;
        return getPurchaseDate() != null ? getPurchaseDate().equals(bucket.getPurchaseDate()) : bucket.getPurchaseDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
        result = 31 * result + (getPurchaseDate() != null ? getPurchaseDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bucket{" +
               "id=" + id +
               ", userId=" + userId +
               ", productId=" + productId +
               ", purchaseDate=" + purchaseDate +
               '}';
    }
}
