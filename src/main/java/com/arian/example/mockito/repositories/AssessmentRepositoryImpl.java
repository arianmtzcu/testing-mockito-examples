package com.arian.example.mockito.repositories;

import java.util.Arrays;
import java.util.List;

import com.arian.example.mockito.models.Assessment;

public class AssessmentRepositoryImpl implements AssessmentRepository {

   @Override
   public Assessment save(final Assessment assessment) {
      return null;
   }

   @Override
   public List<Assessment> findAll() {
      return Arrays.asList(new Assessment(1L, "Mathematics"), new Assessment(2L, "Science"), new Assessment(3L, "Finance"));
   }

}
