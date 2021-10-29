package com.example.octoberexambattleships.repo;

import com.example.octoberexambattleships.domain.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipRepo extends JpaRepository<ShipEntity, Long> {

    Optional<ShipEntity> findByName(String shipName);

}
