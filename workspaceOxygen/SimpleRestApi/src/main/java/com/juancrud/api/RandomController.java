package com.juancrud.api;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random")
public class RandomController {

	@GetMapping()
    public RandomResponse getRandomNumber(@RequestParam(defaultValue="0") int min, @RequestParam(defaultValue="100000") int max) {
		if (min > max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random random = new Random();
		int number = random.nextInt(max - min + 1) + min;
		return new RandomResponse(number);
    }
}
