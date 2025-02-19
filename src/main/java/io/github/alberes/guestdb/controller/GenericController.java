package io.github.alberes.guestdb.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface GenericController {

    default URI createURI(String path, String id){
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(path)
                .buildAndExpand(id)
                .toUri();
    }
}
