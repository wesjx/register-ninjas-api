package dev.wesleyjunior.RegisterNinjas.Ninjas;

import dev.wesleyjunior.RegisterNinjas.Missions.MissionsModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private UUID id;

    private String name;

    private String email;

    private int age;

    private MissionsModel missions;

    private String Rank;
}
