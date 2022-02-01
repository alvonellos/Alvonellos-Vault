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
  @Id @JsonProperty private String key;
  @JsonProperty private String secret;

  public VaultEntity() {}

  @JsonGetter("key")
  public String getKey() {
    return this.key;
  }

  @JsonGetter("secret")
  public String getValue() {
    return this.secret;
  }

  @JsonSetter("key")
  public void setKey(String key) {
    this.key = key;
  }

  @JsonSetter("secret")
  public void setValue(String secret) {
    this.secret = secret;
  }

  public VaultEntity(String key, String secret) {
    this.key = key;
    this.secret = secret;
  }

  public VaultEntity(VaultRequest request) {
    this.key = request.getKey();
    this.secret = request.getSecret();
  }
}
