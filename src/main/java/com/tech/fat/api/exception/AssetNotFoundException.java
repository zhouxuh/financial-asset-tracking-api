package com.tech.fat.api.exception;

public class AssetNotFoundException extends RuntimeException {
    public AssetNotFoundException(String str) {
        super("Could not find the asset by " + str);
    }

    public AssetNotFoundException(Long id) {
        super("Could not find the asset by id: " + id);
    }

    public AssetNotFoundException() {
        super("No asset found.");
    }
}

