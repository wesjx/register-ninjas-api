package dev.wesleyjunior.RegisterNinjas.Ninjas;

import dev.wesleyjunior.RegisterNinjas.Missions.MissionsModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

//Lombok dependency
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

    @Column(unique = true) //Column is unique, for sensitives datas
    private String email;

    private int age;

    @ManyToOne
    @JoinColumn(name = "missions_id") // Foreigner Key
    private MissionsModel missions;

}
