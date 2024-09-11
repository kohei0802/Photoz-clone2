package com.foogle.kohei.photoz.clone2.controllers;

import com.foogle.kohei.photoz.clone2.model.Photo;
import com.foogle.kohei.photoz.clone2.service.PhotozService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
public class PhotozController {

    @Autowired
    private PhotozService _photozService;

    @GetMapping("/")
    public String hello()
    {
        return "Hello world";
    }

    @GetMapping("/photoz")
    public Iterable<Photo> get()
    {
        return _photozService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Integer id)
    {
        Photo photo = _photozService.get(id);
        if(photo ==null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Integer id)
    {
         _photozService.remove(id);
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return _photozService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
