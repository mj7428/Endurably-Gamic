package com.gamic.Endurably.Gamic.services;

import com.gamic.Endurably.Gamic.Entity.BaseLayout;
import com.gamic.Endurably.Gamic.repository.BaseLayoutRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseLayoutServiceTest {

    @Mock 
    private BaseLayoutRepository baseLayoutRepository;

    @InjectMocks 
    private BaseLayoutService baseLayoutService;

    @Test
    void whenTownhallLevelIsPresent_thenFindAllByTownhallLevelIsCalled() {
 
        Integer townhallLevel = 15;
        Pageable pageable = PageRequest.of(0, 5);

        Page<BaseLayout> fakePage = new PageImpl<>(Collections.singletonList(new BaseLayout()));

 
        when(baseLayoutRepository.findAllByTownhallLevel(townhallLevel, pageable)).thenReturn(fakePage);

        Page<BaseLayout> result = baseLayoutService.findAll(Optional.of(townhallLevel), pageable);

        assertEquals(1, result.getTotalElements());
 
        verify(baseLayoutRepository).findAllByTownhallLevel(townhallLevel, pageable);
    }
}
