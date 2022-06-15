package com.controller;

import com.pojo.Order;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    //now create methods to be able to create / list / delete different orders
   @PostMapping("/create")
    public void createOrder(@RequestBody Order order) {
        orderRepository.insert(order);

    }

    @GetMapping("/list")
    public List<Order> listOrder(){
       return orderRepository.findAll();
    }

    @PostMapping("/delete/{id}")
    public void deleteOrder(@PathVariable String id){
       orderRepository.deleteById(id);
    }

    @GetMapping("/read/{id}")
    public Optional<Order> readOrder(@PathVariable String id){
        return orderRepository.findById(id);
    }

}
