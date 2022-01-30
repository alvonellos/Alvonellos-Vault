package com.alvonellos.vaultemulator.repository;

import com.alvonellos.vaultemulator.model.VaultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaultRepository extends JpaRepository<VaultEntity, String> {
    List<VaultEntity> findAllByOrderByKeyAsc();
    List<VaultEntity> findByKeyStartsWith(String key);
    VaultEntity save(VaultEntity vaultEntity);
    void deleteAll();

}
