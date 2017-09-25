package com.thoughtworks.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_item")
public class Item {

    @Id
    private String id;

    private String itemName;
    private double price;
}
