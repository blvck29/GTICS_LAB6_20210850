package com.example.lab6.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/dispositivos")
    public String listaDispositivos(){
        return "lista_dispositivos";
    }

    @GetMapping("/reservas")
    public String listaReservas(){
        return "lista_reservas";
    }

}
