package com.arian.example.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.arian.example.mockito.models.Assessment;
import com.arian.example.mockito.repositories.AssessmentRepository;
import com.arian.example.mockito.repositories.AssessmentRepositoryImpl;
import com.arian.example.mockito.repositories.QuestionRepository;
import com.arian.example.mockito.util.TestConstants;

/**
 * This class demonstrates basic unit testing for the AssessmentService implementation
 * without dependency injection. It serves as an example of traditional testing methods.
 */
class AssessmentServiceBasicTest {

   AssessmentRepository assessmentRepository;

   QuestionRepository questionRepository;

   AssessmentService service;

   @BeforeEach
   void setUp() {
      assessmentRepository = mock(AssessmentRepository.class);
      //assessmentRepository = mock(AnotherAssessmentRepository.class);
      questionRepository = mock(QuestionRepository.class);
      service = new AssessmentServiceImpl(assessmentRepository, questionRepository);
   }

   @Disabled
   @Test
   void findAssessmentByName() {
      AssessmentRepository repository1 = new AssessmentRepositoryImpl();
      AssessmentService service1 = new AssessmentServiceImpl(repository1, null);

      Optional<Assessment> assessment = service1.findAssessmentByName("Finance");

      assertTrue(assessment.isPresent(), "The assessment should be present");
      assertEquals(3L, assessment.orElseThrow().getId(), "The assessment ID should be 3");
      assertEquals("Finance", assessment.get().getName(), "The assessment name should be 'Finance'");
   }

   @Test
   void findAssessmentByNameMock1() {
      when(assessmentRepository.findAll()).thenReturn(TestConstants.ASSESSMENTS);

      Optional<Assessment> assessment = service.findAssessmentByName("History");

      assertTrue(assessment.isPresent(), "The assessment should be present");
      assertEquals(2L, assessment.orElseThrow().getId(), "The assessment ID should be 2");
      assertEquals("History", assessment.get().getName(), "The assessment name should be 'History'");
   }

   @Test
   void findAssessmentByNameMock2() {
      when(assessmentRepository.findAll()).thenReturn(TestConstants.ASSESSMENTS);

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
      when(assessmentRepository.findAll()).thenReturn(TestConstants.ASSESSMENTS);
      // when(questionRepository.findQuestionsByAssessmentId(1L)).thenReturn(MATH_QUESTIONS);
      when(questionRepository.findQuestionsByAssessmentId(anyLong())).thenReturn(TestConstants.MATH_QUESTIONS);

      Assessment assessment = service.findAssessmentByNameWithQuestions("Mathematics");

      assertEquals(5, assessment.getQuestions().size(), "The number of questions should be 5");
      assertTrue(assessment.getQuestions().contains("arithmetic"), "The questions should contain 'arithmetic'");
      assertTrue(assessment.getQuestions().contains("derivatives"), "The questions should contain 'derivatives'");
   }

   @Test
   void testAssessmentQuestionsVerify() {
      when(assessmentRepository.findAll()).thenReturn(TestConstants.ASSESSMENTS);
      // when(questionRepository.findQuestionsByAssessmentId(1L)).thenReturn(MATH_QUESTIONS);
      when(questionRepository.findQuestionsByAssessmentId(anyLong())).thenReturn(TestConstants.MATH_QUESTIONS);

      Assessment assessment = service.findAssessmentByNameWithQuestions("Mathematics");

      assertEquals(5, assessment.getQuestions().size(), "The number of questions should be 5");
      assertTrue(assessment.getQuestions().contains("arithmetic"), "The questions should contain 'arithmetic'");
      assertTrue(assessment.getQuestions().contains("derivatives"), "The questions should contain 'derivatives'");

      /** Verify that the mocked methods are called during the test execution **/
      verify(assessmentRepository).findAll();
      verify(questionRepository).findQuestionsByAssessmentId(anyLong());
   }

   @Disabled
   @Test
   void testNonExistentAssessmentVerify1() {
      when(assessmentRepository.findAll()).thenReturn(Collections.emptyList());
      when(questionRepository.findQuestionsByAssessmentId(anyLong())).thenReturn(TestConstants.MATH_QUESTIONS);

      Assessment assessment = service.findAssessmentByNameWithQuestions("Mathematics");

      assertNull(assessment, "The assessment should be null since it doesn't exist");

      verify(assessmentRepository).findAll();
      // This line will fail because the mocked method is never executed.
      verify(questionRepository).findQuestionsByAssessmentId(anyLong());
   }

   @Test
   void testNonExistentAssessmentVerify() {
      when(assessmentRepository.findAll()).thenReturn(Collections.emptyList());
      when(questionRepository.findQuestionsByAssessmentId(anyLong())).thenReturn(TestConstants.MATH_QUESTIONS);

      Assessment assessment = service.findAssessmentByNameWithQuestions("Mathematics");

      assertNull(assessment, "The assessment should be null since it doesn't exist");

      verify(assessmentRepository).findAll();
      // Verify that the method findQuestionsByAssessmentId(anyLong()) is never called
      // because the assessment does not exist and thus no questions should be fetched.
      verify(questionRepository, never()).findQuestionsByAssessmentId(anyLong());
   }
}