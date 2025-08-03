package com.gamic.Endurably.Gamic.services;

import com.gamic.Endurably.Gamic.Entity.BaseLayout;
import com.gamic.Endurably.Gamic.Entity.Users;
import com.gamic.Endurably.Gamic.repository.BaseLayoutRepository;
import com.gamic.Endurably.Gamic.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class BaseLayoutService {

    private final BaseLayoutRepository baseLayoutRepository;
    private final FileStorageService fileStorageService;
    private final UserRepository userRepository;

    public BaseLayoutService(BaseLayoutRepository baseLayoutRepository, FileStorageService fileStorageService, UserRepository userRepository) {
        this.baseLayoutRepository = baseLayoutRepository;
        this.fileStorageService = fileStorageService;
        this.userRepository = userRepository;
    }

    
    @Cacheable("baseLayouts")
    public Page<BaseLayout> findAll(Optional<Integer> townhallLevel, Pageable pageable) {
        System.out.println("--- Executing DB query for findAll bases ---"); 
        if (townhallLevel.isPresent()) {
            return baseLayoutRepository.findAllByTownhallLevel(townhallLevel.get(), pageable);
        } else {
            return baseLayoutRepository.findAll(pageable);
        }
    }

    
    @CacheEvict(value = "baseLayouts", allEntries = true)
    public void createNewBase(String title, Integer townhallLevel, String baseLink, MultipartFile imageFile) {
        System.out.println("--- Clearing 'baseLayouts' cache ---"); 
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = userDetails.getUsername();

        Users currentUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found in database"));

        String fileName = fileStorageService.storeFile(imageFile);

        BaseLayout newLayout = new BaseLayout();
        newLayout.setTitle(title);
        newLayout.setTownhallLevel(townhallLevel);
        newLayout.setBaseLink(baseLink);
        newLayout.setImageUrl("/uploads/" + fileName);
        newLayout.setSubmittedBy(currentUser);

        baseLayoutRepository.save(newLayout);
    }

    public Page<BaseLayout> findBasesByCurrentUser(Pageable pageable) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Users currentUser = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Authenticated user not found in database"));

        return baseLayoutRepository.findAllBySubmittedBy(currentUser, pageable);
    }
}
