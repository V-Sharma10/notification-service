package com.notif.service.notif.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "blacklist")
public class BlacklistDtoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sno;
    private String number;
}
