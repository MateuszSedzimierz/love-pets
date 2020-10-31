package pl.sedzimierz.lovepets.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1)
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull
    @Size(min = 1)
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "street_address")
    private String streetAddress;

    public Address() {
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

    public Address clone() {
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        address.setStreetAddress(streetAddress);
        return address;
    }

    @Override
    public String toString() {
        return "Address {" +
                "id: " + id +
                ", country: '" + country + '\'' +
                ", city: '" + city + '\'' +
                ", state: '" + state + '\'' +
                ", zipCode: '" + zipCode + '\'' +
                ", streetAddress: '" + streetAddress + '\'' +
                '}';
    }
}
