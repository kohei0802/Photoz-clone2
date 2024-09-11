package com.foogle.kohei.photoz.clone2.service;

import com.foogle.kohei.photoz.clone2.model.Photo;
import com.foogle.kohei.photoz.clone2.repository.PhotozRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Component
@Service
public class PhotozService {


    private final PhotozRepository _photozRepository;

    public PhotozService(PhotozRepository photozRepository) {
        _photozRepository = photozRepository;
    }

    public Iterable<Photo> get() {

        return _photozRepository.findAll();
    }

    public Photo get(Integer id) {
        return  _photozRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        _photozRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setData(data);
        photo.setContentType(contentType);
        _photozRepository.save(photo);
        return photo;
    }
}
