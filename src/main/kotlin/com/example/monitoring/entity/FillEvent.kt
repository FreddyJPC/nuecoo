package com.example.monitoring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "fill_event")
class FillEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "fill_level", nullable = false)
    var fillLevel: Int? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: LocalDateTime = LocalDateTime.now()
}
