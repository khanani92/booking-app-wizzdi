package com.muddassir_92.hotmail.com.runtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.model.SecuredBasic;
import com.wizzdi.flexicore.file.model.FileResource;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class ServiceProvider extends SecuredBasic {

  @OneToMany(targetEntity = ServiceToServiceProvider.class, mappedBy = "serviceProvider")
  @JsonIgnore
  private List<ServiceToServiceProvider> serviceProviderServiceToServiceProviders;

  private String password;

  private String email;

  private boolean block;

  private String gender;

  @ManyToOne(targetEntity = FileResource.class)
  private FileResource profilePicture;

  private String address;

  private String phoneNumber;

  /**
   * @return serviceProviderServiceToServiceProviders
   */
  @OneToMany(targetEntity = ServiceToServiceProvider.class, mappedBy = "serviceProvider")
  @JsonIgnore
  public List<ServiceToServiceProvider> getServiceProviderServiceToServiceProviders() {
    return this.serviceProviderServiceToServiceProviders;
  }

  /**
   * @param serviceProviderServiceToServiceProviders serviceProviderServiceToServiceProviders to set
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setServiceProviderServiceToServiceProviders(
      List<ServiceToServiceProvider> serviceProviderServiceToServiceProviders) {
    this.serviceProviderServiceToServiceProviders = serviceProviderServiceToServiceProviders;
    return (T) this;
  }

  /**
   * @return password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * @param password password to set
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setPassword(String password) {
    this.password = password;
    return (T) this;
  }

  /**
   * @return email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * @param email email to set
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setEmail(String email) {
    this.email = email;
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
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setBlock(boolean block) {
    this.block = block;
    return (T) this;
  }

  /**
   * @return gender
   */
  public String getGender() {
    return this.gender;
  }

  /**
   * @param gender gender to set
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setGender(String gender) {
    this.gender = gender;
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
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setProfilePicture(FileResource profilePicture) {
    this.profilePicture = profilePicture;
    return (T) this;
  }

  /**
   * @return address
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * @param address address to set
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setAddress(String address) {
    this.address = address;
    return (T) this;
  }

  /**
   * @return phoneNumber
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * @param phoneNumber phoneNumber to set
   * @return ServiceProvider
   */
  public <T extends ServiceProvider> T setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return (T) this;
  }
}
