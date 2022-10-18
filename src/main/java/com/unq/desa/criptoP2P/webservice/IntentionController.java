package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.user.User;
import com.unq.desa.criptoP2P.service.IntentionService;
import com.unq.desa.criptoP2P.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class IntentionController {

    @Autowired
    private IntentionService intentionService;

    @GetMapping("/intentions")
    public List<Intention> index() {
        return intentionService.get();
    }

    @GetMapping("/intention")
    public Intention show(@RequestParam("intentionId") Integer id) {
        return this.intentionService.getById(id);
    }

    @PostMapping(value = "/intention")
    public void register(@Valid @RequestBody Intention intention, Errors errors) throws Exception {
        this.intentionService.save(intention);
    }

    @PutMapping(value = "/intention")
    public void update(@RequestBody Intention intention) {
        this.intentionService.save(intention);
    }

    @DeleteMapping(value = "/intention")
    public void delete(@PathVariable Integer id) {
        this.intentionService.delete(id);
    }

}

