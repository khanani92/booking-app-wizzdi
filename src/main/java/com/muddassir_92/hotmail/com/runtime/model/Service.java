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

  @OneToMany(targetEntity = ServiceToServiceProvider.class, mappedBy = "service")
  @JsonIgnore
  private List<ServiceToServiceProvider> serviceServiceToServiceProviders;

  private float price;

  @ManyToOne(targetEntity = FileResource.class)
  private FileResource profilePicture;

  private boolean block;

  /**
   * @return serviceServiceToServiceProviders
   */
  @OneToMany(targetEntity = ServiceToServiceProvider.class, mappedBy = "service")
  @JsonIgnore
  public List<ServiceToServiceProvider> getServiceServiceToServiceProviders() {
    return this.serviceServiceToServiceProviders;
  }

  /**
   * @param serviceServiceToServiceProviders serviceServiceToServiceProviders to set
   * @return Service
   */
  public <T extends Service> T setServiceServiceToServiceProviders(
      List<ServiceToServiceProvider> serviceServiceToServiceProviders) {
    this.serviceServiceToServiceProviders = serviceServiceToServiceProviders;
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

  /**
   * @return block
   */
  public boolean isBlock() {
    return this.block;
  }

  /**
   * @param block block to set
   * @return Service
   */
  public <T extends Service> T setBlock(boolean block) {
    this.block = block;
    return (T) this;
  }
}
