package dev.wesleyjunior.RegisterNinjas.Missions;

import dev.wesleyjunior.RegisterNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.UUID;

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

    public MissionsModel() {
    }

    public MissionsModel(UUID uuid, String title, String description) {
        this.uuid = uuid;
        this.title = title;
        this.difficulty = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
