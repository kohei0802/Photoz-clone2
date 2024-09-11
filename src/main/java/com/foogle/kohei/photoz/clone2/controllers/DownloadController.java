package com.foogle.kohei.photoz.clone2.controllers;

import com.foogle.kohei.photoz.clone2.model.Photo;
import com.foogle.kohei.photoz.clone2.service.PhotozService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController
{
    @Autowired
    private PhotozService _photozService;

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id)
    {
        Photo photo = _photozService.get(id);
        if(photo==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        //ContentDisposition build = ContentDisposition.builder("inline").build();
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(photo.getFileName())
                .build();
        headers.setContentDisposition(build);
        return  new ResponseEntity<>(photo.getData(), headers, HttpStatus.OK);
    }
}
