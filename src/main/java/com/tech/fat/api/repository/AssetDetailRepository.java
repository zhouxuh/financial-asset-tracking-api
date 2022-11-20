package com.tech.fat.api.repository;

import com.tech.fat.api.model.Asset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AssetDetailRepository extends CrudRepository<Asset, Long>  {

    @Query("SELECT a FROM Asset a WHERE a.name =:name")
    Optional<Asset> findAssetByName(@Param("name") String name);
}

