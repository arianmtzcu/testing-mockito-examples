package com.arian.example.mockito.util;

import java.util.Arrays;
import java.util.List;

import com.arian.example.mockito.models.Assessment;

public final class TestConstants {

   public final static List<Assessment> ASSESSMENTS =
         Arrays.asList(
               new Assessment(1L, "Mathematics"),
               new Assessment(2L, "History"),
               new Assessment(3L, "Language"),
               new Assessment(4L, "Science")
         );

   public final static List<String> MATH_QUESTIONS = Arrays.asList("arithmetic", "integrals", "derivatives", "trigonometry", "geometry");

   public final static List<String> PHYSICS_QUESTIONS = Arrays.asList("arithmetic", "integrals", "derivatives", "trigonometry", "geometry");

   public final static Assessment ASSESSMENT_1 = new Assessment(6L, "Physics");

   public final static Assessment ASSESSMENT_2_WP = new Assessment(6L, "Physics", PHYSICS_QUESTIONS);

   public final static Assessment ASSESSMENT_3_WP = new Assessment(null, "Physics", PHYSICS_QUESTIONS);

   private TestConstants() {
   }

}
