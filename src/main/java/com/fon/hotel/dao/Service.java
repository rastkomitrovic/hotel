package com.fon.hotel.dao;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serviceId")
    private long serviceId;

    @Column(name = "serviceName", nullable = false, unique = true)
    private String serviceName;

    @Column(name = "pricePerUse", nullable = false)
    private double pricePerUse;

    @Column(name = "description", nullable = false)
    private String description;

    public Service() {

    }

    public Service(long serviceId, String serviceName, double pricePerUse, String description) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.pricePerUse = pricePerUse;
        this.description = description;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPricePerUse() {
        return pricePerUse;
    }

    public void setPricePerUse(double pricePerUse) {
        this.pricePerUse = pricePerUse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return serviceId == service.serviceId;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", pricePerUse=" + pricePerUse +
                ", description='" + description + '\'' +
                '}';
    }
}
