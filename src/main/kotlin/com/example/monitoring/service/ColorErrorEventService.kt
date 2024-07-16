package com.example.monitoring.service

import com.example.monitoring.entity.ColorErrorEvent
import com.example.monitoring.repository.ColorErrorEventRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ColorErrorEventService {

    @Autowired
    lateinit var colorErrorEventRepository: ColorErrorEventRepository

    fun list(): List<ColorErrorEvent> {
        return colorErrorEventRepository.findAll()
    }

    fun save(colorErrorEvent: ColorErrorEvent): ColorErrorEvent {
        return colorErrorEventRepository.save(colorErrorEvent)
    }

    fun update(id: Long, colorErrorEvent: ColorErrorEvent): ColorErrorEvent {
        val existingColorErrorEvent = colorErrorEventRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el evento de error de color con el ID: $id") }

        existingColorErrorEvent.detectedColor = colorErrorEvent.detectedColor
        existingColorErrorEvent.expectedColor = colorErrorEvent.expectedColor

        return colorErrorEventRepository.save(existingColorErrorEvent)
    }

    fun delete(id: Long) {
        val existingColorErrorEvent = colorErrorEventRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el evento de error de color con el ID: $id") }

        colorErrorEventRepository.delete(existingColorErrorEvent)
    }

    fun getById(id: Long): ColorErrorEvent {
        return colorErrorEventRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el evento de error de color con el ID: $id") }
    }

    fun partialUpdate(id: Long, partialColorErrorEvent: Map<String, Any>): ColorErrorEvent {
        val existingColorErrorEvent = colorErrorEventRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el evento de error de color con el ID: $id") }

        partialColorErrorEvent.forEach { (key, value) ->
            when (key) {
                "detectedColor" -> existingColorErrorEvent.detectedColor = value as String
                "expectedColor" -> existingColorErrorEvent.expectedColor = value as String
                else -> throw IllegalArgumentException("Campo no válido para actualización parcial: $key")
            }
        }

        return colorErrorEventRepository.save(existingColorErrorEvent)
    }
}
