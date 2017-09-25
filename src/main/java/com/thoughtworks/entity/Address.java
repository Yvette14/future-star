package com.thoughtworks.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_address")
public class Address {

    @Id
    private String id;

    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User userId;
}
