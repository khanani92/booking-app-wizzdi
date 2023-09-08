package com.muddassir_92.hotmail.com.runtime;

import com.flexicore.security.SecurityContextBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitConfig {

  @Autowired
  @Qualifier("adminSecurityContext")
  private SecurityContextBase securityContext;
}
