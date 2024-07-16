package com.example.monitoring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "color_error_event")
class ColorErrorEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "detected_color", nullable = false)
    var detectedColor: String? = null

    @Column(name = "expected_color", nullable = false)
    var expectedColor: String? = null

    @Column(name = "timestamp", nullable = false)
    var timestamp: LocalDateTime = LocalDateTime.now()
}
