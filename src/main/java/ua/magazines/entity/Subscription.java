package ua.magazines.entity;

public class Subscription {
    private Integer id;
    private Integer userId;
    private Integer magazineId;
    private Boolean status;

    public Subscription(Integer id, Integer userId, Integer magazineId, Boolean status) {
        this.id = id;
        this.userId = userId;
        this.magazineId = magazineId;
        this.status = status;
    }

    public Subscription(Integer userId, Integer magazineId, Boolean status) {
        this.userId = userId;
        this.magazineId = magazineId;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        if (getMagazineId() != null ? !getMagazineId().equals(that.getMagazineId()) : that.getMagazineId() != null)
            return false;
        return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getMagazineId() != null ? getMagazineId().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subscription{" +
               "id=" + id +
               ", userId=" + userId +
               ", magazineId=" + magazineId +
               ", status=" + status +
               '}';
    }
}
