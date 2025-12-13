package dev.wesleyjunior.RegisterNinjas.Missions;

import dev.wesleyjunior.RegisterNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "tb_missions")
public class MissionsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String title;
    private String difficulty;

    @OneToMany(mappedBy = "missions")
    private NinjaModel ninja;

    public MissionsModel(UUID uuid, String title, String description) {
        this.uuid = uuid;
        this.title = title;
        this.difficulty = description;
    }

}
