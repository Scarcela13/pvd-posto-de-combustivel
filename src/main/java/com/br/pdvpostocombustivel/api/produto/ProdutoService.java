package com.br.pdvpostocombustivel.api.produto;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import com.br.pdvpostocombustivel.domain.entity.Produto;

@Service
public class ProdutoService {

    private final ConcurrentHashMap<Long, Produto> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public List<Produto> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Produto> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Produto create(Produto body) {
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

    public Optional<Produto> update(Long id, Produto body) {
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
