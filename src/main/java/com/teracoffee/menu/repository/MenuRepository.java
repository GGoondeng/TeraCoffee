package com.teracoffee.menu.repository;

import com.teracoffee.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByActiveTrueOrderByIdAsc();
}
