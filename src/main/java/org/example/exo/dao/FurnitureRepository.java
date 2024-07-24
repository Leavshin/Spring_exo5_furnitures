package org.example.exo.dao;

import org.example.exo.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
}
