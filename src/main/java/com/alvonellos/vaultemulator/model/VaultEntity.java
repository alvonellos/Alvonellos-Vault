package com.alvonellos.vaultemulator.model;

import com.alvonellos.vaultemulator.model.request.VaultRequest;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "VAULT")
@JsonPropertyOrder({"key", "secret"})
public class VaultEntity {
  @Id @JsonProperty public String key;
  @JsonProperty public String secret;

  public VaultEntity() {}

  public VaultEntity(String key, String secret) {
    this.key = key;
    this.secret = secret;
  }

  public VaultEntity(VaultRequest request) {
    this.key = request.getKey();
    this.secret = request.getSecret();
  }
}
