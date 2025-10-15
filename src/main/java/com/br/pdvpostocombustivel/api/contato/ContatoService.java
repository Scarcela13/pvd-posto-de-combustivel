package com.br.pdvpostocombustivel.api.contato;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import com.br.pdvpostocombustivel.domain.entity.Contato;

@Service
public class ContatoService {

    private final ConcurrentHashMap<Long, Contato> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public List<Contato> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Contato> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Contato create(Contato body) {
        Long id = seq.getAndIncrement();
        try {
            // try to set id if field exists via reflection
            java.lang.reflect.Field f = body.getClass().getDeclaredField("id");
            f.setAccessible(true);
            f.set(body, id);
        } catch (Exception e) {
            // ignore: entity may not have id field
        }
        store.put(id, body);
        return body;
    }

    public Optional<Contato> update(Long id, Contato body) {
        if (!store.containsKey(id)) return Optional.empty();
        try {
            java.lang.reflect.Field f = body.getClass().getDeclaredField("id");
            f.setAccessible(true);
            f.set(body, id);
        } catch (Exception e) {}
        store.put(id, body);
        return Optional.of(body);
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
