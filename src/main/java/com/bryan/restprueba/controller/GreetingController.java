package com.bryan.restprueba.controller;


import com.bryan.restprueba.model.Greetings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class GreetingController {

    List<Greetings> greetingsList= new ArrayList<>();

    @RequestMapping(value="/greetings",method = RequestMethod.POST)
    public ResponseEntity<String> createGreetings(@RequestBody Greetings g){
        System.out.print("id"+g.getId());
        System.out.print("message"+g.getMessage());
        greetingsList.add(g);
        return  new ResponseEntity<>("Created is sucess",HttpStatus.CREATED);
    }

    @RequestMapping(value="/greetings",method = RequestMethod.GET)
    public ResponseEntity<List<Greetings>> getGreetings(){
        System.out.println("size"+greetingsList.size());
        return new ResponseEntity<>(greetingsList, HttpStatus.OK);
    }
    @RequestMapping(value="/greetings/{id}",method=RequestMethod.GET)
    public ResponseEntity<Object> getGreetingById(@PathVariable("id")int id){
        for(Greetings g:greetingsList){
            if(g.getId()==id){
                return new ResponseEntity<>(g,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value="/greetings/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Object>deleteGreetingsById(@PathVariable("id")int id){
        for(Iterator<Greetings> iter= greetingsList.iterator();iter.hasNext();){
            Greetings g=iter.next();
            if(g.getId()==id){
                iter.remove();
                return new ResponseEntity<>("Greeting is delete sucess",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value="/greetings/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Object>editGreetingById(@RequestBody Greetings greeting,@PathVariable("id")int id){
        for(Greetings g:greetingsList){
            if(g.getId()==id){
                g.setMessage(greeting.getMessage());
                return new ResponseEntity<>("Greeting is put sucess",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }
}
