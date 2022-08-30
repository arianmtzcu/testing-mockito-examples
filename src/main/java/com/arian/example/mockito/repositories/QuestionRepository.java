package com.arian.example.mockito.repositories;

import java.util.List;

public interface QuestionRepository {

   List<String> findQuestionsByAssessmentId(Long id);

   void saveMultiple(List<String> preguntas);

}
