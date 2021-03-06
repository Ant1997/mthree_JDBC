package org.example.DVDDatabase.controller;

import org.example.DVDDatabase.data.DVDDao;
import org.example.DVDDatabase.models.DVD;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/DVD")
public class DVDController {
    private final DVDDao dao;

    public DVDController(DVDDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<DVD> all() {
        return dao.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DVD create(@RequestBody DVD dvd) {
        return dao.add(dvd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DVD> findById(@PathVariable int id) {
        DVD result = dao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody DVD dvd) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != dvd.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (dao.update(dvd)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (dao.deleteById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

