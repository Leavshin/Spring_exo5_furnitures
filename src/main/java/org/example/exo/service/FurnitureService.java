package org.example.exo.service;

import org.example.exo.dao.FurnitureRepository;
import org.example.exo.entity.Furniture;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    public void saveFurniture(Furniture furniture) {
        furnitureRepository.save(furniture);
    }

    public Optional<Furniture> getFurnitureById(Long id) {
        return furnitureRepository.findById(id);
    }

    public void deleteFurniture(Long id) {
        furnitureRepository.deleteById(id);
    }
}
