package ua.magazines.entity;

public class Magazine {
    private Integer id;
    private String name;
    private String description;
    private Double priceForMount;

    public Magazine(Integer id, String name, String description, Double priceForMount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceForMount = priceForMount;
    }

    public Magazine(String name, String description, Double priceForMount) {
        this.name = name;
        this.description = description;
        this.priceForMount = priceForMount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceForMount() {
        return priceForMount;
    }

    public void setPriceForMount(Double priceForMount) {
        this.priceForMount = priceForMount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine magazine)) return false;

        if (getId() != null ? !getId().equals(magazine.getId()) : magazine.getId() != null) return false;
        if (getName() != null ? !getName().equals(magazine.getName()) : magazine.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(magazine.getDescription()) : magazine.getDescription() != null)
            return false;
        return getPriceForMount() != null ? getPriceForMount().equals(magazine.getPriceForMount()) : magazine.getPriceForMount() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPriceForMount() != null ? getPriceForMount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Magazine{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", priceForMount=" + priceForMount +
               '}';
    }
}
