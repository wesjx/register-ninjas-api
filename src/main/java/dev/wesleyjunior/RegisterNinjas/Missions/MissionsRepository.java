package dev.wesleyjunior.RegisterNinjas.Missions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MissionsRepository extends JpaRepository<MissionsModel, UUID> {
}
