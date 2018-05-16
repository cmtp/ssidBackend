
package com.ssid.api.apissid.controller;


import com.ssid.api.apissid.command.ResourcesSsoCommand;
import com.ssid.api.apissid.domain.ResourceSso;
import com.ssid.api.apissid.services.ResourceSsoService;
import com.ssid.api.apissid.util.ApiPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = ApiPath.RESOURCES_SSO_PATH)
public class ResourceSsoController {
    private ResourceSsoService resourceSsoService;

    @Autowired
    public ResourceSsoController(ResourceSsoService resourceSsoService) {
        this.resourceSsoService = resourceSsoService;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getListResourcesSso() {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("status", "ok");
        List<ResourceSso> resourcesList = resourceSsoService.getResource();
        mapResponse.put("data", resourcesList);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findResocuresSsoById(@PathVariable Long id) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("status", "ok");
        ResourceSso resourceSso = this.resourceSsoService.getResourceById(id);
        mapResponse.put("data", resourceSso);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);

    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> saveResourceSso(@RequestBody ResourcesSsoCommand resourcesSsoCommand) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("status", "created");
        mapResponse.put("data", resourceSsoService.saveResourceSso(resourcesSsoCommand.toResourcesSso()));
        return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateResourceSso(@RequestBody ResourcesSsoCommand resourcesSsoCommand, @PathVariable int id) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("status", "updated");
        mapResponse.put("data", resourceSsoService.updateResource(resourcesSsoCommand.toResourcesSso(), (long) id));
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> deleteResourceSsoById(@PathVariable long id) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("status", "deleted");
        this.resourceSsoService.deleteResourceById(id);
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
}
