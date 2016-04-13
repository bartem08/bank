package com.bank.controller.rest;

import com.bank.model.Operation;
import com.bank.model.Response;
import com.bank.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/bank/operations")
public class OperationRestController {

    @Autowired
    private OperationService operationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> createOperation(@RequestBody @Valid Operation operation,
                                                    BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    new Response(result.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
        try {
            operationService.createOperation(operation);
            return new ResponseEntity<>(new Response("Success"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
