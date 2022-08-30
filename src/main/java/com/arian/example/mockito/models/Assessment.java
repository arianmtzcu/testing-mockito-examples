package com.arian.example.mockito.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assessment {

   private Long id;

   private String name;

   private List<String> questions;

   public Assessment(final Long id, final String name) {
      this.id = id;
      this.name = name;
   }

}
