package com.demo.authdemo.service;

import com.demo.authdemo.entity.Material;
import com.demo.authdemo.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public int getTotalMaterialCount(Long id) {
        return materialRepository.countMaterialsByRoomId(id);
    }

    public int getFoundMaterialCount(Long id) {
        return materialRepository.countFoundMaterialsByRoomId(id);
    }

    public int getMaterialCountInOtherLocations(Long id) {
        return materialRepository.countMaterialsInOtherLocations(id);
    }

    public void updateMaterialStatus(Long barkodNo, boolean found) {
        Optional<Material> materialOpt = materialRepository.findByBarkodNo(barkodNo);
        if (materialOpt.isPresent()) {
            Material material = materialOpt.get();
            material.setBulduMu(found);
            materialRepository.save(material);
        } else {
            throw new RuntimeException("Material not found with barcode: " + barkodNo);
        }
    }

    public Material getMaterialDetailsByBarkodNo(Long barkodNo) {
        return materialRepository.findByBarkodNoWithDetails(barkodNo)
                .orElseThrow(() -> new RuntimeException("Material not found with barcode: " + barkodNo));
    }
}
