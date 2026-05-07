package com.teracoffee.menu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "coffee_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoffeeMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean active;

    public CoffeeMenu(String name, int price) {
        this.name = name;
        this.price = price;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
