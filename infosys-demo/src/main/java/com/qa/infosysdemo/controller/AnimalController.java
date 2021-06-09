package com.qa.infosysdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qa.infosysdemo.domain.Animal;


@RestController
public class AnimalController {
	
	private List<Animal> animalList = new ArrayList<>();
	
	@GetMapping("/get/animals")
	public ResponseEntity<List<Animal>> getAllAnimals(){
		return new ResponseEntity<List<Animal>>( this.animalList, HttpStatus.OK);
	}
	
	@GetMapping("/get/animal/{index}")
	public ResponseEntity<Animal> getAnimal(@PathVariable int index){
		return new ResponseEntity<Animal>(this.animalList.get(index), HttpStatus.OK);
	}
	
	@PostMapping("/post/animal")
	public ResponseEntity<String> createAnimal(@RequestBody Animal a) {
		this.animalList.add(a);
		return new ResponseEntity<String>(a.toString() + " was added successfully", HttpStatus.CREATED);
	}
	
	@PutMapping("/put/animal/{index}")
	public ResponseEntity<Animal> updateAnimal(@PathVariable int index, @RequestBody Animal a){
			this.animalList.set(index, a);
		return new ResponseEntity<Animal>(a, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/patch/animal/{index}")
	public ResponseEntity<Animal> changeAnimal(@PathVariable int index, @PathParam("name") String name, @PathParam("age") int age, @PathParam("species") String species ){
			Animal a = this.animalList.get(index);
			if(name != null) {a.setName(name);}
			if(age != 0) {a.setAge(age);}
			if(species != null) {a.setSpecies(species);}
		return new ResponseEntity<Animal>(a, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/animal/{index}")
	public ResponseEntity<Animal> deleteAnimal(@PathVariable int index){
		return new ResponseEntity<Animal>(this.animalList.remove(index), HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete/animals")
	public ResponseEntity<String> deleteAllAnimals(){
		this.animalList.clear();
		return new ResponseEntity<String>("All animals removed", HttpStatus.NO_CONTENT);
	}
	
}
