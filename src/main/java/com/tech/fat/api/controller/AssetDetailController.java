package com.tech.fat.api.controller;

import com.tech.fat.api.model.Asset;
import com.tech.fat.api.service.AssetDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
@Validated
public class AssetDetailController {
    private final AssetDetailService assetDetailService;

    public AssetDetailController(AssetDetailService assetDetailService) {
        this.assetDetailService = assetDetailService;
    }

    @PostMapping("asset")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<Asset> addAsset(@Valid @RequestBody Asset asset, Authentication authentication) {
        String userName = authentication.getName();
        asset.setUserName(userName);
        Asset addedAsset = assetDetailService.addAsset(asset);
        return new ResponseEntity<>(addedAsset, HttpStatus.CREATED);
    }

    @GetMapping("assets")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<List<Asset>> getAllAssets(Authentication authentication) {
        return new ResponseEntity<>(assetDetailService.getAllAssets(authentication.getName()), HttpStatus.OK);
    }

    @GetMapping("asset/name/{name}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<Asset> getAssetByName(@Size(min = 1, max = 10, message = "{asset.name.size}")
                                       @PathVariable(value="name") String name, Authentication authentication) {
        Asset asset = assetDetailService.getAssetByNameAndUserName(name.toUpperCase(), authentication.getName());
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @PutMapping("asset/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Asset> updateAsset(@Positive(message = "{asset.id.positive}")
                                           @PathVariable(value="id") Long id,
                                           @Valid @RequestBody Asset asset) {
        Asset updatedAsset = assetDetailService.updateAsset(id, asset);
        return new ResponseEntity<>(updatedAsset, HttpStatus.OK);
    }

    @DeleteMapping("asset/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Asset> deleteAssetById(@Positive(message = "{asset.id.positive}")
                                               @PathVariable(value="id") Long id) {
        Asset deletedAsset = assetDetailService.deleteAssetById(id);
        return new ResponseEntity<>(deletedAsset, HttpStatus.OK);
    }

    @GetMapping("asset/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    ResponseEntity<Asset> getAssetById(@Positive(message = "{asset.id.positive}")
                                     @PathVariable(value="id") Long id) {
        return new ResponseEntity<>(assetDetailService.getAssetById(id), HttpStatus.OK);
    }
}
