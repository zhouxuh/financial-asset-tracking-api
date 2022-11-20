package com.tech.fat.api.service;

import com.tech.fat.api.exception.AssetNotFoundException;
import com.tech.fat.api.model.Asset;

import java.util.List;

public interface AssetDetailService {
    List<Asset> getAllAssets();
    Asset addAsset(Asset Asset);
    Asset getAssetByName(String name) throws AssetNotFoundException;
    Asset updateAsset(Long id, Asset asset);
    Asset deleteAssetById(Long id);
    Asset getAssetById(Long id);
}
