package com.example.monitoring.repository

import com.example.monitoring.entity.FillEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FillEventRepository : JpaRepository<FillEvent, Long>
