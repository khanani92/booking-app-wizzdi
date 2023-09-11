package com.muddassir_92.hotmail.com.runtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.model.SecuredBasic;
import com.wizzdi.flexicore.file.model.FileResource;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Service extends SecuredBasic {

  @OneToMany(targetEntity = ServiceProvider.class, mappedBy = "service")
  @JsonIgnore
  private List<ServiceProvider> serviceServiceProviders;

  private float price;

  @ManyToOne(targetEntity = FileResource.class)
  private FileResource profilePicture;

  /**
   * @return serviceServiceProviders
   */
  @OneToMany(targetEntity = ServiceProvider.class, mappedBy = "service")
  @JsonIgnore
  public List<ServiceProvider> getServiceServiceProviders() {
    return this.serviceServiceProviders;
  }

  /**
   * @param serviceServiceProviders serviceServiceProviders to set
   * @return Service
   */
  public <T extends Service> T setServiceServiceProviders(
      List<ServiceProvider> serviceServiceProviders) {
    this.serviceServiceProviders = serviceServiceProviders;
    return (T) this;
  }

  /**
   * @return price
   */
  public float getPrice() {
    return this.price;
  }

  /**
   * @param price price to set
   * @return Service
   */
  public <T extends Service> T setPrice(float price) {
    this.price = price;
    return (T) this;
  }

  /**
   * @return profilePicture
   */
  @ManyToOne(targetEntity = FileResource.class)
  public FileResource getProfilePicture() {
    return this.profilePicture;
  }

  /**
   * @param profilePicture profilePicture to set
   * @return Service
   */
  public <T extends Service> T setProfilePicture(FileResource profilePicture) {
    this.profilePicture = profilePicture;
    return (T) this;
  }
}
