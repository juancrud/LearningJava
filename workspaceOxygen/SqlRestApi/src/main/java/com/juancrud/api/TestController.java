package com.juancrud.api;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestController {
	
	@Autowired
    private TestRepository testRepository;

	@GetMapping
    public Collection<Test> getAll() {
		Iterable<Test> allTests = testRepository.findAll();
		
		Collection<Test> result = new ArrayList<Test>();
		allTests.forEach(result::add);
		
		return result;
    }
	
	@GetMapping("/{id}")
    public Test getById(@PathVariable int id) {
		return testRepository.findById(id).orElse(null);
    }
    
    @PostMapping
    public Test add(@RequestBody Test test) {
    	testRepository.save(test);
        return test;
    }
    
    @DeleteMapping("/{id}")
    public Test delete(@PathVariable int id) {
    	Test test = testRepository.findById(id).orElse(null);
    	testRepository.delete(test);
        return test;
    }
}
