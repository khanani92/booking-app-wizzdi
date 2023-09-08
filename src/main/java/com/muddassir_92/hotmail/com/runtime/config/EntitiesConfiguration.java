package com.muddassir_92.hotmail.com.runtime.config;

import com.wizzdi.flexicore.boot.jpa.service.EntitiesHolder;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EntitiesConfiguration {
  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public EntitiesHolder manualEntityHolder() {
    return new EntitiesHolder(
        new HashSet<>(
            Arrays.asList(
                com.flexicore.model.Baseclass.class,
                com.flexicore.model.Baselink.class,
                com.flexicore.model.Basic.class,
                com.flexicore.model.Clazz.class,
                com.flexicore.model.OperationCategory.class,
                com.flexicore.model.OperationToClazz.class,
                com.flexicore.model.PermissionGroup.class,
                com.flexicore.model.PermissionGroupToBaseclass.class,
                com.flexicore.model.Role.class,
                com.flexicore.model.RoleToBaseclass.class,
                com.flexicore.model.RoleToUser.class,
                com.flexicore.model.SecuredBasic.class,
                com.flexicore.model.SecurityEntity.class,
                com.flexicore.model.SecurityLink.class,
                com.flexicore.model.SecurityOperation.class,
                com.flexicore.model.security.SecurityPolicy.class,
                com.flexicore.model.SecurityTenant.class,
                com.flexicore.model.SecurityUser.class,
                com.flexicore.model.SecurityWildcard.class,
                com.flexicore.model.TenantToBaseClassPremission.class,
                com.flexicore.model.TenantToUser.class,
                com.flexicore.model.UserToBaseClass.class)));
  }
}
