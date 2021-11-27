package com.mediga.documentstore.model;

import java.time.LocalDateTime;

public class Document {
    private String name;
    private LocalDateTime createdAt;
    private String content;

    public Document(String name, String content) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
