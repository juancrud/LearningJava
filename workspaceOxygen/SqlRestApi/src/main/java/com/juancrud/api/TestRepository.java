package com.juancrud.api;

import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Integer> {
   Test findByName(String name);
}
