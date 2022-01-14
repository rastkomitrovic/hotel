package com.fon.hotel.dto;

public class ServiceDTO {

    private long serviceId;

    private String serviceName;

    private double pricePerUse;

    private String description;

    public ServiceDTO() {

    }

    public ServiceDTO(long serviceId, String serviceName, double pricePerUse, String description) {
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
        ServiceDTO service = (ServiceDTO) o;
        return serviceId == service.serviceId;
    }

    @Override
    public String toString() {
        return serviceName;
    }
}
