package com.alvonellos.vaultemulator.service;

import com.alvonellos.vaultemulator.model.VaultEntity;
import com.alvonellos.vaultemulator.repository.VaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** VaultService service layer implementation */
public class VaultService {
  public VaultRepository vaultRepository;

    /**
     * Autowired VaultService Constructor
     * @param vaultRepository the vault session to use
     */
  public VaultService(@Autowired VaultRepository vaultRepository) {
    this.vaultRepository = vaultRepository;
  }

  public ResponseEntity<VaultEntity> postEntity(VaultEntity entity, String token, String requestURI) {
    entity.setPath(requestURI);
    //TODO Implement authentication
    vaultRepository.save(entity);
    return new ResponseEntity<>(entity, HttpStatus.OK);
  }
}
