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

    @Column(name = "ninjas_UUID")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(unique = true) //Column is unique, for sensitives datas
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "rank")
    private String rank;

    @ManyToOne
    @JoinColumn(name = "mission_id") // Foreigner Key
    private MissionsModel missions;

}
