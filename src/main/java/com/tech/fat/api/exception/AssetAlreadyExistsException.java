package com.tech.fat.api.exception;

public class AssetAlreadyExistsException extends RuntimeException{
    public AssetAlreadyExistsException(String name) {
        super("Asset with name " + name + " already exists.");
    }
}
