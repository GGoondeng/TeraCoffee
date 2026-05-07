package com.teracoffee.menu.service;

import com.teracoffee.menu.dto.MenuResponse;
import com.teracoffee.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuResponse> getMenus() {
        return menuRepository.findAllByActiveTrueOrderByIdAsc()
                .stream()
                .map(MenuResponse::from)
                .toList();
    }
}
