package com.alvonellos.vaultemulator.model.request;

import com.alvonellos.vaultemulator.model.VaultEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;


@Data
public class VaultRequest  {
    @JsonProperty
    private String key;
    @JsonProperty private String secret;

    public VaultRequest() {}

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

    public VaultRequest(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    public VaultRequest(VaultEntity vaultEntity) {
        this.key = vaultEntity.getKey();
        this.secret = vaultEntity.getValue();
    }
}
