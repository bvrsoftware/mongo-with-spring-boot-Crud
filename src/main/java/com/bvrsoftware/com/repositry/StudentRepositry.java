package com.bvrsoftware.com.repositry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bvrsoftware.com.model.Student;

public interface StudentRepositry extends MongoRepository<Student, Integer> {

}
