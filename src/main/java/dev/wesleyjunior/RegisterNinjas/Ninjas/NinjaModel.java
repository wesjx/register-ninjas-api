package dev.wesleyjunior.RegisterNinjas.Ninjas;

import dev.wesleyjunior.RegisterNinjas.Missions.MissionsModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

//Lobok depedence
@NoArgsConstructor
@AllArgsConstructor
@Data //Getters and setters

@Entity
@Table(name = "tb_register_ninjas")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private int age;

    @ManyToOne
    @JoinColumn(name = "missions_id") // Foreing Key
    private MissionsModel missions;

    public NinjaModel(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

}
