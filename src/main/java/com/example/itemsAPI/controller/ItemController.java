package com.example.itemsAPI.controller;

import com.example.itemsAPI.controller.dto.ItemDto;
import com.example.itemsAPI.repository.ItemRepository;
import com.example.itemsAPI.repository.entity.Item;
import com.example.itemsAPI.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController{
    final ItemService itemService;
    //final ItemRepository itemRepository;


//    public ItemController(@Autowired ItemService itemService,@Autowired ItemRepository itemRepository )
    public ItemController(@Autowired ItemService itemService)
    {
        this.itemService = itemService;
       // this.itemRepository = itemRepository;
    }

    @CrossOrigin
    @GetMapping("/all")
    public Iterable<Item> getItems(){
//        return itemRepository.findAll();
        return itemService.all();
    }

    @CrossOrigin
    @PostMapping("/add")
    public Item save( @RequestBody ItemDto itemDto )
    {
        Item newItem = new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getImageUrl());
//        return itemService.save( new Item(itemDto));
        return itemService.save(newItem);
    }

    @GetMapping("/{id}")
    public Optional<Item> findItemById(@PathVariable Integer id ){
        return itemService.findById( id );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Item> update(@RequestBody ItemDto itemDto, @PathVariable Integer id )
    {
        //Item item = itemService.findById(id);
        Item item = itemService.findById(id)
                        .orElseThrow();


        item.setName( itemDto.getName() );
        item.setDescription( itemDto.getDescription() );
        item.setImageUrl( itemDto.getImageUrl() );
//        return ResponseEntity.ok(ItemService.save( item ));
        itemService.save(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}" )
    public void delete( @PathVariable Integer id )
    {
        itemService.delete( id );
    }

}
