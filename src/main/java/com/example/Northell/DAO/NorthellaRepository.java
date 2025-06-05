package com.example.Northell.DAO;

import com.example.Northell.Model.Dummy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface NorthellaRepository extends CrudRepository<Dummy,Integer> {

    @Query(nativeQuery = true)
    List<Map<String,Object>> getRawJson(@Param("permutationIds") List<UUID> permutationIds);

    @Query(nativeQuery = true)
    List<Map<String,Object>> getConvertedJson(@Param("permutationIds") List<UUID> permutationIds);
}
