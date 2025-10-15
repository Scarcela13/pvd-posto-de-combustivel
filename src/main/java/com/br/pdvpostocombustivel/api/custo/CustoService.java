package com.br.pdvpostocombustivel.api.custo;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import com.br.pdvpostocombustivel.domain.entity.Custo;

@Service
public class CustoService {

    private final ConcurrentHashMap<Long, Custo> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public List<Custo> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Custo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Custo create(Custo body) {
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

    public Optional<Custo> update(Long id, Custo body) {
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
