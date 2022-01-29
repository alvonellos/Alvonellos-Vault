package com.alvonellos.vaultemulator.service;

import com.alvonellos.vaultemulator.model.VaultEntity;
import com.alvonellos.vaultemulator.repository.VaultRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/** VaultService service layer implementation */
@Service
@Log
public class VaultService implements InitializingBean {

  @Value("${com.alvonellos.vaultemulator.vaultpath}")
  String vaultPath;

  public VaultRepository vaultRepository;

    /**
     * Autowired VaultService Constructor
     * @param vaultRepository the vault session to use
     */
  public VaultService(@Autowired VaultRepository vaultRepository) {
    this.vaultRepository = vaultRepository;
  }

  /**
   * Add an entity to the vault
   * @param entity the entity to add
   * @param token the token to check for authentication
   * @param requestURI the key path (obtained from requestURI)
   * @return
   */
  public ResponseEntity<VaultEntity> postEntity(VaultEntity entity, String token, String requestURI) {
    entity.setKey(requestURI + "/" + entity.getKey());
    //TODO Implement authentication
    vaultRepository.save(entity);
    return new ResponseEntity<>(entity, HttpStatus.OK);
  }

  /**
   * Finds a response entity by key
   * @param requestURI this is the key
   * @param token
   * @return
   */
  public ResponseEntity<Object> findByKey(String requestURI, String token) {
    List<VaultEntity> entity = vaultRepository.findByKeyStartsWith(requestURI);
    //TODO: implement authentication
    if (entity != null) {
      return new ResponseEntity<Object>(entity, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info(String.format("Loaded from properties com.alvonellos.vaultemulator.vaultpath: %s", vaultPath));
  }
}
