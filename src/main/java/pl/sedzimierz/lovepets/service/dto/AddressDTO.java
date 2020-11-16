package pl.sedzimierz.lovepets.service.dto;

import pl.sedzimierz.lovepets.model.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressDTO {

    private Long id;

    @NotBlank
    @Size(min = 1)
    private String country;

    @NotBlank
    @Size(min = 1)
    private String city;

    private String state;

    private String zipCode;

    private String streetAddress;

    public AddressDTO() {
    }

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.country = address.getCountry();
        this.city = address.getCity();
        this.state = address.getState();
        this.zipCode = address.getZipCode();
        this.streetAddress = address.getStreetAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public AddressDTO clone() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry(country);
        addressDTO.setCity(city);
        addressDTO.setState(state);
        addressDTO.setZipCode(zipCode);
        addressDTO.setStreetAddress(streetAddress);
        return addressDTO;
    }
    @Override
    public String toString() {
        return "AddressDTO {" +
                "id: " + id +
                ", country: '" + country + '\'' +
                ", city: '" + city + '\'' +
                ", state: '" + state + '\'' +
                ", zipCode: '" + zipCode + '\'' +
                ", streetAddress: '" + streetAddress + '\'' +
                '}';
    }
}
