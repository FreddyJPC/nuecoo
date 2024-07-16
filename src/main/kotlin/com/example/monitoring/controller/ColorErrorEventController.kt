package com.example.monitoring.controller

import com.example.monitoring.entity.ColorErrorEvent
import com.example.monitoring.service.ColorErrorEventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/color-error-event")
class ColorErrorEventController {

    @Autowired
    lateinit var colorErrorEventService: ColorErrorEventService

    @GetMapping
    fun list(): List<ColorErrorEvent> {
        return colorErrorEventService.list()
    }

    @PostMapping
    fun save(@RequestBody colorErrorEvent: ColorErrorEvent): ColorErrorEvent {
        return colorErrorEventService.save(colorErrorEvent)
    }

    @PostMapping("/data")
    fun receiveData(@RequestBody data: Map<String, Any>): ResponseEntity<String> {
        println("Datos recibidos: $data")
        val detectedColor = data["detectedColor"] as? String ?: return ResponseEntity.badRequest().body("Missing 'detectedColor'")
        val expectedColor = data["expectedColor"] as? String ?: return ResponseEntity.badRequest().body("Missing 'expectedColor'")

        val colorErrorEvent = ColorErrorEvent().apply {
            this.detectedColor = detectedColor
            this.expectedColor = expectedColor
        }

        colorErrorEventService.save(colorErrorEvent)

        return ResponseEntity.ok("Datos recibidos correctamente")
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody colorErrorEvent: ColorErrorEvent): ColorErrorEvent {
        return colorErrorEventService.update(id, colorErrorEvent)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        colorErrorEventService.delete(id)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ColorErrorEvent {
        return colorErrorEventService.getById(id)
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody partialColorErrorEvent: Map<String, Any>): ColorErrorEvent {
        return colorErrorEventService.partialUpdate(id, partialColorErrorEvent)
    }
}
