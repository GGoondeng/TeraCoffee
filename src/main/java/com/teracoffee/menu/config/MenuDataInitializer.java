package com.teracoffee.menu.config;

import com.teracoffee.menu.entity.Menu;
import com.teracoffee.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MenuDataInitializer implements CommandLineRunner {

    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (menuRepository.count() > 0) {
            return;
        }

        menuRepository.save(new Menu("아메리카노", 2800));
        menuRepository.save(new Menu("카페라떼", 3000));
        menuRepository.save(new Menu("바닐라라떼", 3200));
        menuRepository.save(new Menu("콜드브루", 3000));
        menuRepository.save(new Menu("레몬에이드", 3500));
    }
}
