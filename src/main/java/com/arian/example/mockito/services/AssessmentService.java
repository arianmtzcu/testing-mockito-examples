package com.arian.example.mockito.services;

import java.util.Optional;

import com.arian.example.mockito.models.Assessment;

public interface AssessmentService {

   /**
    * Find an assessment by its name.
    *
    * @param name the name of the assessment to find.
    * @return an Optional containing the assessment if found, otherwise an empty Optional.
    */
   Optional<Assessment> findAssessmentByName(String name);

   /**
    * Find an assessment by its name along with associated questions.
    *
    * @param name the name of the assessment to find.
    * @return the assessment with its associated questions.
    */
   Assessment findAssessmentByNameWithQuestions(String name);

   /**
    * Update an existing assessment.
    *
    * @param assessment the assessment to be updated.
    * @return the updated assessment.
    */
   Assessment saveAssessment(Assessment assessment);

}
