package com.example.monitoring.controller

import com.example.monitoring.entity.FillEvent
import com.example.monitoring.service.FillEventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/fill-event")
class FillEventController {

    @Autowired
    lateinit var fillEventService: FillEventService

    @GetMapping
    fun list(): List<FillEvent> {
        return fillEventService.list()
    }

    @PostMapping
    fun save(@RequestBody fillEvent: FillEvent): FillEvent {
        return fillEventService.save(fillEvent)
    }

    @PostMapping("/data")
    fun receiveData(@RequestBody data: Map<String, Any>): ResponseEntity<String> {
        println("Datos recibidos: $data")
        val fillLevel = data["fillLevel"] as? Int ?: return ResponseEntity.badRequest().body("Missing 'fillLevel'")

        val fillEvent = FillEvent().apply {
            this.fillLevel = fillLevel
        }

        fillEventService.save(fillEvent)

        return ResponseEntity.ok("Datos recibidos correctamente")
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody fillEvent: FillEvent): FillEvent {
        return fillEventService.update(id, fillEvent)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        fillEventService.delete(id)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): FillEvent {
        return fillEventService.getById(id)
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody partialFillEvent: Map<String, Any>): FillEvent {
        return fillEventService.partialUpdate(id, partialFillEvent)
    }
}
