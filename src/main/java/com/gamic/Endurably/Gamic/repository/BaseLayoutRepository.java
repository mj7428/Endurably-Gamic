package com.gamic.Endurably.Gamic.repository;

import com.gamic.Endurably.Gamic.Entity.BaseLayout;
import com.gamic.Endurably.Gamic.Entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseLayoutRepository extends JpaRepository<BaseLayout, Long> {

    Page<BaseLayout> findAllByTownhallLevel(Integer townhallLevel, Pageable pageable);

    Page<BaseLayout> findAllBySubmittedBy(Users user, Pageable pageable);
}
