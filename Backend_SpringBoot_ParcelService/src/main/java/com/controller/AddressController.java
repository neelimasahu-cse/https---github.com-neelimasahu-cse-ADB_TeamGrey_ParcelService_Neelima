package com.controller;
import com.pojo.AddressDetails;
import com.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class AddressController {

        @Autowired
        private AddressRepository addressRepository;

        @PostMapping("/addresscreate")
        public void createOrder(@RequestBody AddressDetails addressDetails) {
                addressRepository.insert(addressDetails);
        }

        @GetMapping("/addresslist")
        public List<AddressDetails> listAddress(){
                return addressRepository.findAll();
        }

        @PostMapping("/addressdelete/{id}")
        public void deleteOrder(@PathVariable String id){
                addressRepository.deleteById(id);
        }

}
