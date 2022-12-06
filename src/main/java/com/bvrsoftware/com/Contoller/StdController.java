package com.bvrsoftware.com.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvrsoftware.com.model.Student;
import com.bvrsoftware.com.repositry.StudentRepositry;

@RestController
@RequestMapping("/api")
public class StdController {
	@Autowired
    private StudentRepositry repositry;
	
	@PostMapping("/student/")
	public ResponseEntity<Student> stdsave(@RequestBody Student std){
		Student savestd=repositry.save(std);
		return new ResponseEntity<>(savestd,HttpStatus.CREATED);
	}
	@PutMapping("/student/{id}")
	public ResponseEntity<?> stdupdate(@RequestBody Student std,@PathVariable Integer id){
		Student savestd=null;
		Student std1=repositry.findById(id).get();
		if(std1!=null) {
		std1.setCity(std.getCity());
		std1.setMobile(std.getMobile());
		std1.setName(std.getName());
		savestd=repositry.save(std1);
		return new ResponseEntity<>(savestd,HttpStatus.CONFLICT);
	    }
		return new ResponseEntity<>("detail not find '"+id+"'",HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> stdbyId(@PathVariable Integer id){
		Student savestd=repositry.findById(id).get();
		return new ResponseEntity<>(savestd,HttpStatus.OK);
	}
	@DeleteMapping("/student/{id}")
	public ResponseEntity<?> stddeletId(@PathVariable Integer id){
		Student savestd=repositry.findById(id).get();
		if(savestd!=null) {
			repositry.delete(savestd);
			return new ResponseEntity<>("delete successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("detail not delete !!",HttpStatus.OK);
	}
	@GetMapping("/student/")
	public ResponseEntity<List<Student>> stdAll(){
		List<Student> savestd=repositry.findAll();
		return new ResponseEntity<>(savestd,HttpStatus.OK);
	}
}
