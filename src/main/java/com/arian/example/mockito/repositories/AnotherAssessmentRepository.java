package com.arian.example.mockito.repositories;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.arian.example.mockito.models.Assessment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnotherAssessmentRepository implements AssessmentRepository {

   @Override
   public Assessment save(final Assessment assessment) {
      return null;
   }

   @Override
   public List<Assessment> findAll() {
      log.info("AnotherAssessmentRepository :: findAll()");
      try {
         TimeUnit.SECONDS.sleep(4);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      return Collections.emptyList();
   }
}
