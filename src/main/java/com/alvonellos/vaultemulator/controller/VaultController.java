package com.alvonellos.vaultemulator.controller;

import com.alvonellos.vaultemulator.model.VaultEntity;
import com.alvonellos.vaultemulator.repository.VaultRepository;
import com.alvonellos.vaultemulator.service.VaultService;
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
    private final VaultRepository vaultRepository;
    private final VaultService vaultService;

    public VaultController(@Autowired VaultRepository vaultRepository, @Autowired VaultService vaultService) {
        this.vaultRepository = vaultRepository;
        this.vaultService = vaultService;
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
        return vaultService.postEntity(entity, token, request.getRequestURI());
    }


}
