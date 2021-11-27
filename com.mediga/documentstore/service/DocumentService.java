package com.mediga.documentstore.service;

import com.mediga.documentstore.exception.*;
import com.mediga.documentstore.model.*;
import com.mediga.documentstore.util.StringUtil;
import com.mediga.documentstore.util.Validator;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DocumentService {
    private static String DOCUMENT_STORE_PATH = "D:/document-store";
    private LinkedHashMap<String, Document> documentCache;
    private Map<String, DocumentPermission> documentPermissionMap;

    public DocumentService() throws IOException {
        this.documentCache = new LinkedHashMap<>();
        this.documentPermissionMap = new HashMap<>();
        File file = new File(DOCUMENT_STORE_PATH);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    public void createDocument(User user, Document document, Tier tier) throws DocumentAlreadyExistsException, DocumentStoreException, UserNotFoundException {

        Validator.validateUser(user);

        if(document == null || tier == null) {
            throw new IllegalArgumentException("document/tier cannot be null");
        }

        if(StringUtil.isEmpty(user.getName())) {
            throw new IllegalArgumentException("user name should not be null or empty");
        }

        if(StringUtil.isEmpty(document.getName()) || StringUtil.isEmpty(document.getContent())) {
            throw new IllegalArgumentException("Document name or content should not be null/empty");
        }

        if(checkIfDocumentExists(document.getName())) {
            throw new DocumentAlreadyExistsException("Document already exists with name " + document.getName());
        }

        try{
            DocumentPermission documentPermission = new DocumentPermission(document.getName(), user, tier);
            documentPermissionMap.put(document.getName(), documentPermission);
            File file = new File(DOCUMENT_STORE_PATH + "/" + document.getName());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            try{
                writer.write(document.getContent());
            } finally {
                writer.close();
            }

            if(tier == Tier.MEMORY) {
                documentCache.put(document.getName(), document);
            }
        } catch (IOException e) {
            throw new DocumentStoreException(e.getMessage());
        }
    }

    public void editDocument(User user, String documentName) {

    }

    public String readDocument(User user, String documentName) throws PermissionDeniedException, DocumentNotFoundException, DocumentStoreException, UserNotFoundException {
        Validator.validateUser(user);

        if(StringUtil.isEmpty(documentName)) {
            throw new IllegalArgumentException("Document name or content should not be null/empty");
        }

        if(!checkIfDocumentExists(documentName)) {
            throw new DocumentNotFoundException("Document with name :" + documentName + " is not exists");
        }
        try{
            if(checkPermission(user, documentName, Permission.READ)) {
                if(documentPermissionMap.get(documentName) != null &&
                        documentPermissionMap.get(documentName).getTier() == Tier.MEMORY && documentCache.get(documentName) != null) {
                    return documentCache.get(documentName).getContent();
                }

                File file = new File(DOCUMENT_STORE_PATH + "/" + documentName);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                try{
                    String line;
                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } finally {
                    reader.close();
                }

                return sb.toString();
            } else {
                throw new PermissionDeniedException("User " + user.getName() + " does not have permission");
            }
        } catch (IOException e) {
            throw new DocumentStoreException(e.getMessage());
        }
    }

    public void grantPermission(User owner, Permission permission, String documentName, User user) throws PermissionDeniedException, UserNotFoundException, DocumentStoreException {
        Validator.validateUser(owner);
        Validator.validateUser(user);
        if(checkIfDocumentExists(documentName))
        if(documentPermissionMap.get(documentName) == null) {
            throw new DocumentStoreException("Could not grant permission");
        }
        if(documentPermissionMap.get(documentName).getOwner().getName().equals(owner.getName())) {
            documentPermissionMap.get(documentName).getUserPermissions().put(user.getName(), permission);
        } else {
            throw new PermissionDeniedException("User " + owner.getName() + " is not the owner of the document");
        }
    }

    public void changeGrant(User user, String documentName, GrantType grantType) throws PermissionDeniedException, UserNotFoundException, DocumentStoreException, DocumentNotFoundException {
        Validator.validateUser(user);
        if(StringUtil.isEmpty(documentName)) {
            throw new IllegalArgumentException("Document name or content should not be null/empty");
        }

        if(!checkIfDocumentExists(documentName)) {
            throw new DocumentNotFoundException("Document with name :" + documentName + " is not exists");
        }
        if(documentPermissionMap.get(documentName) == null) {
            throw new DocumentStoreException("Could not change grant");
        }
        if(documentPermissionMap.get(documentName).getOwner().getName().equals(user.getName())) {
            documentPermissionMap.get(documentName).setGrantType(grantType);
        } else {
            throw new PermissionDeniedException("User " + user.getName() + " is not the owner of the document");
        }
    }
    private boolean checkPermission(User user, String documentName, Permission permission) throws DocumentNotFoundException, DocumentStoreException {
        if(StringUtil.isEmpty(documentName)) {
            throw new IllegalArgumentException("Document name or content should not be null/empty");
        }

        if(!checkIfDocumentExists(documentName)) {
            throw new DocumentNotFoundException("Document with name :" + documentName + " is not exists");
        }
        DocumentPermission documentPermission = documentPermissionMap.get(documentName);
        if(documentPermissionMap.get(documentName) == null) {
            throw new DocumentStoreException("Could not check permission");
        }
        if(documentPermission.getGrantType() == GrantType.PUBLIC) {
            return true;
        }

        Map<String, Permission> userPermissions = documentPermission.getUserPermissions();
        if(userPermissions.get(user.getName()) == permission) {
            return true;
        }
        return false;
    }

    private boolean checkIfDocumentExists(String documentName) {
        if(documentCache.get(documentName) != null) {
            return true;
        }
        File documentStore = new File(DOCUMENT_STORE_PATH);
        for(File file : documentStore.listFiles()) {
            if(file.getName().equals(documentName)) {
                return true;
            }
        }
        return false;
    }
}
