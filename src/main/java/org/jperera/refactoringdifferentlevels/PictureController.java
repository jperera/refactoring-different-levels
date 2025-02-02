package org.jperera.refactoringdifferentlevels;

import org.springframework.web.bind.annotation.*;

@RestController
public class PictureController {

    private final PictureRepository repository;

    public PictureController(PictureRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/v1/pictures")
    Pictures all() {
        return new Pictures(repository.findAll());
    }

    @GetMapping("/v1/pictures/{id}")
    Picture get(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new PictureNotFoundException());
    }

    @PostMapping("/v1/pictures")
    Picture newPicture(@RequestBody Picture picture) {
        return repository.save(picture);
    }

    @PutMapping("/v1/pictures/{id}")
    Picture replacePicture(@RequestBody Picture newPicture, @PathVariable Integer id) {
        return repository.findById(id)
                .map(existingPicture -> {
                    existingPicture.setPosition(newPicture.getPosition());
                    existingPicture.setTitle(newPicture.getTitle());
                    existingPicture.setUrl(newPicture.getUrl());
                    return repository.save(existingPicture);
                })
                .orElseThrow(() -> new PictureNotFoundException());
    }

    @DeleteMapping("/v1/pictures/{id}")
    void deletePicture(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
