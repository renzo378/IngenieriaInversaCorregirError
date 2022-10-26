package com.example.demo.entities;


import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Modificador {

    private String visibilidad = "Package";

    private boolean isFinal = false;

    private boolean isStatic = false;


}
