package com.grupoeragin.inventory.repositories;

import com.grupoeragin.inventory.entities.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    Optional<Tool> findToolBySerialNumber(String serialNumber);

    boolean existsBySerialNumber(String serialNumber);
}
