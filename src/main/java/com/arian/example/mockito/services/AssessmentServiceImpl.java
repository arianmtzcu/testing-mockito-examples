package com.arian.example.mockito.services;

import java.util.List;
import java.util.Optional;

import com.arian.example.mockito.models.Assessment;
import com.arian.example.mockito.repositories.AssessmentRepository;
import com.arian.example.mockito.repositories.QuestionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

   private AssessmentRepository assessmentRepository;

   private QuestionRepository questionRepository;

   /** Retorna el examen sin sus preguntas **/
   @Override
   public Optional<Assessment> findAssessmentByName(String nombre) {
      return assessmentRepository.findAll().stream().filter(e -> e.getName().contains(nombre)).findFirst();
   }

   /** Retorna el examen con las preguntas **/
   @Override
   public Assessment findAssessmentByNameWithQuestions(String nombre) {
      final Optional<Assessment> optionalExamen = findAssessmentByName(nombre);
      Assessment assessment = null;
      if (optionalExamen.isPresent()) {
         assessment = optionalExamen.orElseThrow();
         final List<String> preguntas = questionRepository.findQuestionsByAssessmentId(assessment.getId());
         assessment.setQuestions(preguntas);
      }
      return assessment;
   }

   @Override
   public Assessment saveAssessment(Assessment assessment) {
      if(!assessment.getQuestions().isEmpty()) {
         questionRepository.saveMultiple(assessment.getQuestions());
      }
      return assessmentRepository.save(assessment);
   }
}
