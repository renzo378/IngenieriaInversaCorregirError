package com.example.demo.entities;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Atributo {

    private Modificador modificador;

    private String nombre;

    private String tipo;

}
