package com.arian.example.mockito.services;

import static com.arian.example.mockito.util.TestConstants.ASSESSMENTS;
import static com.arian.example.mockito.util.TestConstants.ASSESSMENT_2_WP;
import static com.arian.example.mockito.util.TestConstants.ASSESSMENT_3_WP;
import static com.arian.example.mockito.util.TestConstants.MATH_QUESTIONS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.arian.example.mockito.models.Assessment;
import com.arian.example.mockito.repositories.AssessmentRepository;
import com.arian.example.mockito.repositories.QuestionRepository;

/**
 * This class demonstrates unit testing for the AssessmentService implementation
 * using Mockito's JUnit 5 extension for dependency injection. This approach is
 * recommended as it provides a clean and modern way to handle mocks and
 * dependency injection in tests.
 *
 * <p> NOTE: We extend the JUnit engine with Mockito, which enables annotations
 * and allows dependency injection.</p>
 */
@ExtendWith(MockitoExtension.class)
class AssessmentServiceMockitoExtensionTest {

   @Mock
   AssessmentRepository assessmentRepository;

   @Mock
   QuestionRepository questionRepository;

   @InjectMocks
   AssessmentServiceImpl service;

   @Test
   void findAssessmentByNameMock1() {
      when(assessmentRepository.findAll()).thenReturn(ASSESSMENTS);

      Optional<Assessment> assessment = service.findAssessmentByName("History");

      assertTrue(assessment.isPresent(), "The assessment should be present");
      assertEquals(2L, assessment.orElseThrow().getId(), "The assessment ID should be 2");
      assertEquals("History", assessment.get().getName(), "The assessment name should be 'History'");
   }

   @Test
   void findAssessmentByNameMock2() {
      when(assessmentRepository.findAll()).thenReturn(ASSESSMENTS);

      Optional<Assessment> assessment = service.findAssessmentByName("Mathematics");

      assertTrue(assessment.isPresent(), "The assessment should be present");
      assertEquals(1L, assessment.orElseThrow().getId(), "The assessment ID should be 1");
      assertEquals("Mathematics", assessment.get().getName(), "The assessment name should be 'Mathematics'");
   }

   @Test
   void findAssessmentByNameMock3() {
      when(assessmentRepository.findAll()).thenReturn(Collections.emptyList());

      Optional<Assessment> assessment = service.findAssessmentByName("Mathematics");

      assertFalse(assessment.isPresent(), "The assessment should not be present");
   }

   @Test
   void testAssessmentQuestions() {
      when(assessmentRepository.findAll()).thenReturn(ASSESSMENTS);
      // when(preguntaRepository.findPreguntasPorExamenId(1L)).thenReturn(MATH_PREGUNTAS);
      when(questionRepository.findQuestionsByAssessmentId(anyLong())).thenReturn(MATH_QUESTIONS);

      Assessment assessment = service.findAssessmentByNameWithQuestions("Mathematics");

      assertEquals(5, assessment.getQuestions().size(), "The number of questions should be 5");
      assertTrue(assessment.getQuestions().contains("arithmetic"), "The questions should contain 'arithmetic'");
      assertTrue(assessment.getQuestions().contains("derivatives"), "The questions should contain 'derivatives'");
   }

   @Test
   void testAssessmentQuestionsVerify() {
      // -> GIVEN
      when(assessmentRepository.findAll()).thenReturn(ASSESSMENTS);
      when(questionRepository.findQuestionsByAssessmentId(anyLong())).thenReturn(MATH_QUESTIONS);

      // -> WHEN
      Assessment assessment = service.findAssessmentByNameWithQuestions("Mathematics");

      // -> THEN
      assertEquals(5, assessment.getQuestions().size(), "The number of questions should be 5");
      assertTrue(assessment.getQuestions().contains("arithmetic"), "The questions should contain 'arithmetic'");
      assertTrue(assessment.getQuestions().contains("derivatives"), "The questions should contain 'derivatives'");

      // Verifies that the mocked methods are called during the execution of the test
      verify(assessmentRepository).findAll();
      verify(questionRepository).findQuestionsByAssessmentId(anyLong());
   }

   @Test
   void testNonExistentAssessmentVerify() {
      when(assessmentRepository.findAll()).thenReturn(Collections.emptyList());

      Assessment assessment = service.findAssessmentByNameWithQuestions("Mathematics");

      assertNull(assessment, "The assessment should be null since it doesn't exist");

      verify(assessmentRepository).findAll();
      // This line fails because the mocked method is never executed
      // verify(questionRepository).findQuestionsByAssessmentId(anyLong());
   }

   @Test
   void testSaveAssessment1() {
      when(assessmentRepository.save(any(Assessment.class))).thenReturn(ASSESSMENT_2_WP);

      Assessment assessment = service.saveAssessment(ASSESSMENT_2_WP);

      assertNotNull(assessment.getId(), "The saved assessment should have an ID");
      assertEquals(6L, assessment.getId(), "The assessment ID should be 6");
      assertEquals("Physics", assessment.getName(), "The assessment name should be 'Physics'");

      verify(assessmentRepository).save(any(Assessment.class));
      verify(questionRepository).saveMultiple(anyList());
   }

   // Classic example with modification of the object passed in the mock function (auto-generated ID field)
   @Test
   void testSaveAssessment2() {
      // -> GIVEN
      when(assessmentRepository.save(any(Assessment.class))).then(new Answer<Assessment>() {

         // Attribute that starts at 7 and would increment for auto-generated IDs (classic DB)
         Long sequence = 7L;

         @Override
         public Assessment answer(InvocationOnMock invocationOnMock) {
            // getArgument(0) -> returns the assessment object passed in assessmentRepository.save(...)
            Assessment assessment = invocationOnMock.getArgument(0);
            assessment.setId(sequence++); // Assigns the ID field and increments sequence++
            return assessment;
         }
      });

      /**-----------------------------------------------**/
      // -> WHEN
      Assessment assessment = service.saveAssessment(ASSESSMENT_3_WP);

      // -> THEN
      assertNotNull(assessment.getId());
      assertEquals(7L, assessment.getId());
      assertEquals("Physics", assessment.getName());

      verify(assessmentRepository).save(any(Assessment.class));
      verify(questionRepository).saveMultiple(anyList());
      /**-----------------------------------------------**/
   }

}