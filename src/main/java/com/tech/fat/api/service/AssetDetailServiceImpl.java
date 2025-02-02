package com.tech.fat.api.service;

import com.tech.fat.api.exception.AssetAlreadyExistsException;
import com.tech.fat.api.exception.AssetNotFoundException;
import com.tech.fat.api.model.Asset;
import com.tech.fat.api.repository.AssetDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetDetailServiceImpl implements AssetDetailService {
    private final AssetDetailRepository assetDetailRepository;

    public AssetDetailServiceImpl(AssetDetailRepository assetDetailRepository) {
        this.assetDetailRepository = assetDetailRepository;
    }

    @Override
    public List<Asset> getAllAssets(String userName) {
        return assetDetailRepository.findAllByUserName(userName);
    }

    @Override
    public Asset addAsset(Asset asset) throws AssetAlreadyExistsException {
        String name = asset.getName();
        asset.setId(null);
        if (assetDetailRepository.findAssetByNameAndUserName(name, asset.getUserName()).isPresent()) {
            throw new AssetAlreadyExistsException(name);
        }
        return assetDetailRepository.save(asset);
    }

    @Override
    public Asset getAssetByNameAndUserName(String name, String userName) throws AssetNotFoundException {
        return assetDetailRepository.findAssetByNameAndUserName(name, userName).orElseThrow(() -> new AssetNotFoundException(name));
    }

    @Override
    public Asset updateAsset(Long id, Asset newAsset) {
        return assetDetailRepository.findById(id)
                .map(asset -> {
                    asset.setName(newAsset.getName());
                    asset.setQuantity(newAsset.getQuantity());
                    asset.setPrice(newAsset.getPrice());
                    asset.setCostBasis(newAsset.getCostBasis());
                    asset.setNotes(newAsset.getNotes());
                    return assetDetailRepository.save(asset);
                })
                .orElseThrow(() -> new AssetNotFoundException(id));
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetDetailRepository.findById(id).orElseThrow(() -> new AssetNotFoundException(id));
    }

    @Override
    public Asset deleteAssetById(Long id) {
        Asset asset = assetDetailRepository.findById(id).orElseThrow(() -> new AssetNotFoundException(id));
        assetDetailRepository.deleteById(id);
        return asset;
    }
}
