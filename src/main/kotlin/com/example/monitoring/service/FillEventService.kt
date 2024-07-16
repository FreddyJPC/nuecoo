package com.example.monitoring.service

import com.example.monitoring.entity.FillEvent
import com.example.monitoring.repository.FillEventRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FillEventService {

    @Autowired
    lateinit var fillEventRepository: FillEventRepository

    fun list(): List<FillEvent> {
        return fillEventRepository.findAll()
    }

    fun save(fillEvent: FillEvent): FillEvent {
        return fillEventRepository.save(fillEvent)
    }

    fun update(id: Long, fillEvent: FillEvent): FillEvent {
        val existingFillEvent = fillEventRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el evento de llenado con el ID: $id") }

        existingFillEvent.fillLevel = fillEvent.fillLevel

        return fillEventRepository.save(existingFillEvent)
    }

    fun delete(id: Long) {
        val existingFillEvent = fillEventRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el evento de llenado con el ID: $id") }

        fillEventRepository.delete(existingFillEvent)
    }

    fun getById(id: Long): FillEvent {
        return fillEventRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el evento de llenado con el ID: $id") }
    }

    fun partialUpdate(id: Long, partialFillEvent: Map<String, Any>): FillEvent {
        val existingFillEvent = fillEventRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el evento de llenado con el ID: $id") }

        partialFillEvent.forEach { (key, value) ->
            when (key) {
                "fillLevel" -> existingFillEvent.fillLevel = value as Int
                else -> throw IllegalArgumentException("Campo no válido para actualización parcial: $key")
            }
        }

        return fillEventRepository.save(existingFillEvent)
    }
}
