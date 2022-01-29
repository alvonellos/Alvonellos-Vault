package com.alvonellos.vaultemulator.controller;

import com.alvonellos.vaultemulator.model.VaultEntity;
import com.alvonellos.vaultemulator.repository.VaultRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("v1")
@Log
public class VaultController {
    private VaultRepository vaultRepository;

    public VaultController(@Autowired VaultRepository vaultRepository) {
        this.vaultRepository = vaultRepository;
    }

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        log.info("Healthcheck OK");
        vaultRepository.save(new VaultEntity("test", "test"));
        return new ResponseEntity<String>("UP", HttpStatus.OK);
    }

    @GetMapping(value = "/vault/", produces = "application/json")
    public ResponseEntity<List<VaultEntity>> getAllEntities(
            @RequestHeader HttpHeaders headers, @RequestHeader(name = "X-Vault-Token") String token)  {
        log.info(String.format("%s provided token: %s for key %s", headers.getHost(), token, "/vault/"));
        return new ResponseEntity<>(vaultRepository.findAllByOrderByKeyAsc(), HttpStatus.OK);
    }

    @GetMapping(value = "/vault/**", produces = "application/json")
    public ResponseEntity<VaultEntity> getEntity(
            @RequestHeader HttpHeaders headers, @RequestHeader(name = "X-Vault-Token") String token,
            HttpServletRequest request) {
        log.info(String.format("%s provided token: %s for key %s", headers.getHost(), token, request.getRequestURI()));
        return new ResponseEntity<VaultEntity>(new VaultEntity(request.getRequestURI(), vaultRepository.findByKey(request.getRequestURI())), HttpStatus.OK);
    }

    @PostMapping(value = "/vault/**", consumes = "application/json", produces = "application/json")
    public ResponseEntity<VaultEntity> postEntity(
            @RequestHeader HttpHeaders headers, @RequestHeader(name = "X-Vault-Token") String token,
            @RequestHeader(name = "X-Vault-Request", required = false) String vaultRequest, @RequestBody VaultEntity entity, HttpServletRequest request) {
        log.info(String.format("%s provided token %s for vault request %s with entity key: %s, value: %s", headers.getHost(), token, vaultRequest, request.getRequestURI(), entity));
        vaultRepository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }


}
