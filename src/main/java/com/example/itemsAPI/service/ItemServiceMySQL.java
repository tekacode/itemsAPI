package com.example.itemsAPI.service;

import com.example.itemsAPI.repository.ItemRepository;
import com.example.itemsAPI.repository.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceMySQL implements ItemService
{
    private final ItemRepository itemRepository;

    public ItemServiceMySQL(@Autowired ItemRepository itemRepository )
    {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save( Item item )
    {
        return itemRepository.save(item);
    }

    @Override
    public boolean delete( int itemId )
    {
        itemRepository.deleteById(itemId);
        return true;
    }

    @Override
    public List<Item> all()
    {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(int itemId )
    {
        return itemRepository.findById(itemId);
    }
}
