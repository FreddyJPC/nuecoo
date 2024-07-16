package com.example.monitoring.repository

import com.example.monitoring.entity.ColorErrorEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ColorErrorEventRepository : JpaRepository<ColorErrorEvent, Long>
