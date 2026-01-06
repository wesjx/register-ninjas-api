package dev.wesleyjunior.RegisterNinjas.Missions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.wesleyjunior.RegisterNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "tb_missions")
public class MissionsModel {

    @Column(name = "missions_UUID")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "difficulty")
    private String difficulty;

    @OneToMany(mappedBy = "missions")
    @JsonIgnore
    private List<NinjaModel> ninjas;

    public MissionsModel(UUID uuid, String title, String description) {
        this.uuid = uuid;
        this.title = title;
        this.difficulty = description;
    }

}
