package me.fortibrine.polishlearn.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class WordleController {

    @GetMapping("/words")
    fun getWords(): List<String> {
        val result = mutableListOf<String>()
        this.javaClass.getResourceAsStream("/wordle-6-letters.txt")?.bufferedReader()?.forEachLine {
            result.add(it)
        }
        return result
    }

}