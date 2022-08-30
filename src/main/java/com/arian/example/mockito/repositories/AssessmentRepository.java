package com.arian.example.mockito.repositories;

import java.util.List;

import com.arian.example.mockito.models.Assessment;

public interface AssessmentRepository {

   Assessment save(Assessment assessment);

   List<Assessment> findAll();

}
