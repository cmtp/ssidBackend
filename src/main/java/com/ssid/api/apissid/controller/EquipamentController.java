package com.ssid.api.apissid.controller;

import com.ssid.api.apissid.domain.Equipament;
import com.ssid.api.apissid.services.EquipamentService;
import com.ssid.api.apissid.command.EquipamentCommand;
import com.ssid.api.apissid.util.ApiPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author daniel fernandez
 */
@RestController
@RequestMapping(value = ApiPath.EQUIPAMENT_PATH)
public class EquipamentController {

    private EquipamentService equipamentService;

    @Autowired
    public EquipamentController(EquipamentService equipamentService) {
        this.equipamentService = equipamentService;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public List<EquipamentCommand> getListEquipament() {

        List<EquipamentCommand> equipamentCommadsList = new ArrayList<>();

        this.equipamentService.getListEquipament().forEach(equipament -> {
            equipamentCommadsList.add(new EquipamentCommand(equipament));
        });
        return equipamentCommadsList;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public @ResponseBody
    void saveEquipament(@RequestBody EquipamentCommand equipamentCommad) {
        this.equipamentService.saveEquipament(equipamentCommad.toEquipament());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    EquipamentCommand findEquipamentById(@PathVariable(value = "id") Long id) {

        Optional<Equipament> equipament = equipamentService.getEquipamentById(id);
        return new EquipamentCommand(equipament.get());
    }

    @RequestMapping(value = ApiPath.EQUIPAMENT_BY_ID, method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteEquipamentById(@PathVariable(value = "id") Long id) {
        this.equipamentService.deleteEquipamentById(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateEquipament(@RequestBody EquipamentCommand equipament, @PathVariable int id) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("status", "updated");
        mapResponse.put("data", this.equipamentService.updateEquipament(equipament.toEquipament(), (long) id));
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
    @RequestMapping(path = ApiPath.EQUIPAMENT_BY_ID, method = RequestMethod.PUT)
    public @ResponseBody
    EquipamentCommand updateEquipament(@RequestBody EquipamentCommand equipament, @PathVariable(value = "id") Long id){

        Equipament update =this.equipamentService.updateEquipament(equipament.toEquipament(), id);
        return new EquipamentCommand(update);
    }
}