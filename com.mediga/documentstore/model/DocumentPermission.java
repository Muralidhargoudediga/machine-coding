package com.mediga.documentstore.model;

import java.util.HashMap;
import java.util.Map;

public class DocumentPermission {
    private String documentName;
    private User owner;
    private GrantType grantType;
    private Map<String, Permission> userPermissions;
    private Tier tier;

    public DocumentPermission(String documentName, User owner, Tier tier) {
        this.documentName = documentName;
        this.grantType = GrantType.PRIVATE;
        this.owner = owner;
        this.tier = tier;
        userPermissions = new HashMap<>();
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }

    public Map<String, Permission> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Map<String, Permission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
