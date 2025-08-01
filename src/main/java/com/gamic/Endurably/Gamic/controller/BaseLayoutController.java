package com.gamic.Endurably.Gamic.controller;

import com.gamic.Endurably.Gamic.Entity.BaseLayout;
import com.gamic.Endurably.Gamic.services.BaseLayoutService;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/bases")
public class BaseLayoutController {

    private final BaseLayoutService baseLayoutService;

    public BaseLayoutController(BaseLayoutService baseLayoutService) {
        this.baseLayoutService = baseLayoutService;
    }

    @GetMapping
    public ResponseEntity<Page<BaseLayout>> getAllBaseLayouts(
            @RequestParam Optional<Integer> townhallLevel,
            Pageable pageable
    ) {
        Page<BaseLayout> layouts = baseLayoutService.findAll(townhallLevel, pageable);
        return ResponseEntity.ok(layouts);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createBaseWithImage(
            @RequestParam("title") String title,
            @RequestParam("townhallLevel") Integer townhallLevel,
            @RequestParam("baseLink") String baseLink,
            @RequestParam("image") MultipartFile imageFile
    ) {
        baseLayoutService.createNewBase(title, townhallLevel, baseLink, imageFile);
        return ResponseEntity.ok("Base Layout created successfully!");
    }

    
}
